#!/usr/bin/python
# -*- coding: UTF-8 -*-

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import mean_absolute_error
from sklearn.model_selection import cross_val_score
from sklearn.model_selection import train_test_split
from sklearn.ensemble import GradientBoostingRegressor,GradientBoostingClassifier,RandomForestRegressor,RandomForestClassifier
from sklearn.ensemble.partial_dependence import partial_dependence, plot_partial_dependence
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import Imputer
from sklearn.externals import joblib

#描述数据
'''
print (melbourne_data.describe())
print (melbourne_data.columns)
print (melbourne_price_data.head())
print (melbourne_data.isnull().sum())
print (X.head())

y = data.card
X = data.drop(['card'],axis =1)

'''

#根据条件选取列
'''
不包含object
X = X.select_dtypes(exclude=['object'])
SalePrice列有空的数据丢掉
melbourne_data.dropna(axis=0, subset=['SalePrice'], inplace=True)
只要有列为空的数据，改列就丢掉
melbourne_data.dropna(axis=1)

有空数据的列
cols_with_missing = [col for col in melbourne_data.columns 
                                 if melbourne_data[col].isnull().any()] 
数据种类小于10而且是object类型的列
low_cardinality_cols = [cname for cname in X.columns if 
                                X[cname].nunique() < 10 and
                                X[cname].dtype == "object"]
数据是数字的列
numeric_cols = [cname for cname in X.columns if 
                                X[cname].dtype in ['int64', 'float64']]
my_cols = low_cardinality_cols + numeric_cols
train_predictors = X[my_cols]

'''


#处理数据 缺失值和一键hot类别
# 使用交叉验证    cross_val_score
'''
from sklearn.preprocessing import Imputer
from sklearn.model_selection import cross_val_score

# handle data ,Processing data and comparison of raw data

def score_dataset(X_train, X_test, y_train, y_test):
    model = RandomForestRegressor()
    model.fit(X_train, y_train)
    preds = model.predict(X_test)
    return mean_absolute_error(y_test, preds)

# A Drop Columns with Missing Values
#data_without_missing_values = melbourne_data.dropna(axis=1)

cols_with_missing = [c for c in X_train.columns if X_train[c].isnull().any()]
reduced_X_train = X_train.drop(cols_with_missing, axis=1)
reduced_X_test  = X_test.drop(cols_with_missing, axis=1)
print("Mean Absolute Error from dropping columns with Missing Values:")
print(score_dataset(reduced_X_train, reduced_X_test, y_train, y_test))

# B  Imputation
my_imputer = Imputer()
imputed_X_train = my_imputer.fit_transform(X_train)
imputed_X_test = my_imputer.transform(X_test)
print("Mean Absolute Error from Imputation:")
print(score_dataset(imputed_X_train, imputed_X_test, y_train, y_test))

# C  An Extension To Imputation
imputed_X_train_plus = X_train.copy()
imputed_X_test_plus = X_test.copy()
cols_with_missing = (col for col in X_train.columns 
                                 if X_train[col].isnull().any())
for col in cols_with_missing:
    imputed_X_train_plus[col + '_was_missing'] = imputed_X_train_plus[col].isnull()
    imputed_X_test_plus[col + '_was_missing'] = imputed_X_test_plus[col].isnull()

# Imputation
my_imputer = Imputer()
imputed_X_train_plus = my_imputer.fit_transform(imputed_X_train_plus)
imputed_X_test_plus = my_imputer.transform(imputed_X_test_plus)

print("Mean Absolute Error from Imputation while Track What Was Imputed:")
print(score_dataset(imputed_X_train_plus, imputed_X_test_plus, y_train, y_test))

# D One-Hot Categories

def get_mae(X_mea, y_mea):
    # multiple by -1 to make positive MAE score instead of neg value returned as sklearn convention   
	return -1 * cross_val_score(RandomForestRegressor(50), X_mea, y_mea, scoring = 'neg_mean_absolute_error').mean()


predictors_without_categoricals = X_train.select_dtypes(exclude=['object'])
mae_without_categoricals = get_mae(predictors_without_categoricals, y_train)
print('Mean Absolute Error when Dropping Categoricals: ' + str(int(mae_without_categoricals)))

#get_dummies一键hot

one_hot_encoded_training_predictors = pd.get_dummies(X_train)
mae_one_hot_encoded = get_mae(one_hot_encoded_training_predictors, y_train)
print('Mean Abslute Error with One-Hot Encoding: ' + str(int(mae_one_hot_encoded)))
'''

#Data Leakage 

#在处理电信用户流失的时候，用原有的数据集轻轻松松就可以把AUC达到0.99以上。这让人非常警惕。于是Chris老师仔细查看了一下模型和数据，原来数据中有一个权重极高的feature是“3个月内的缴费纪录”。很多流失用户的账户内，这个feature的值是0。再进一步，他跟会计核实了一下，在会计记账中，这个feature 代表的是用户已经流失后的三个月的缴费纪录，那肯定就是0了。这是典型的因果关系颠倒。

#因为用户流失才导致缴费记录为空，而不是因为没有缴费记录才导致用户流失

# 因果倒置
'''
y = data.card
X = data.drop(['card'],axis =1)

my_model = RandomForestClassifier()
my_model.fit(X, y)

cv_scores = cross_val_score(my_model, X, y, scoring='accuracy')
print("Cross-val accuracy: %f" %cv_scores.mean())# 0.9810

#支出是否意味着这张卡片或申请前使用的卡片上的支出？
# card == False 的很可能expenditure == 0.0000
expenditures_cardholders = melbourne_data.expenditure[melbourne_data.card]
expenditures_noncardholders = melbourne_data.expenditure[~melbourne_data.card]

# 有卡的有2%在这张卡上没有消费支出
# 没有卡的全都是没有在这张卡上消费支出的
# 逻辑上的因果关系倒置了
print('Fraction of those who received a card with no expenditures: %.2f' \
      %(( expenditures_cardholders == 0).mean()))# 0.02
print('Fraction of those who received a card with no expenditures: %.2f' \
      %((expenditures_noncardholders == 0).mean()))# 1.00

no_expenditure = data.drop(['card','expenditure','share', 'active', 'majorcards'],axis =1)

my_other_model = RandomForestClassifier()
my_other_model.fit(no_expenditure, y)
cv_other_scores = cross_val_score(my_other_model, no_expenditure, y, scoring='accuracy')
print("Cross-val accuracy: %f" %cv_other_scores.mean())#0.809
'''



#拆分数据
# split data into train and validation
# how to know test_size and random_state?
'''
from sklearn.model_selection import train_test_split
X_train,X_test,train_y,val_y = train_test_split(X,y,test_size=0.25,random_state = 0)
'''

#调参画图
'''
import matplotlib.pyplot as plt

params = [1,2,3,4,5,6,7,8,10]
test_scores = []
for param in params:
    clf = XGBRegressor(max_depth=param)
    test_score = np.sqrt(-cross_val_score(clf, train_x, train_y, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))
print test_scores
plt.plot(params, test_scores)
plt.title("max_depth vs CV Error");
# 一定要加上这句才能让画好的图显示在屏幕上
plt.show()
# 将当前figure的图保存到文件result.png
#plt.savefig('./xgboostparams.png')


params = [100,200,300,400,500]
test_scores = []
for param in params:
    clf = XGBRegressor(n_estimators=param)
    test_score = np.sqrt(-cross_val_score(clf, train_X, train_y, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))
plt.plot(params, test_scores)
plt.title("n_estimator vs CV Error" + str(params));
# 一定要加上这句才能让画好的图显示在屏幕上
plt.show()
'''


#Pipeline 管道机制更像是编程技巧的创新，而非算法的创新
'''
train_X,test_X,train_y,test_y = train_test_split(X,y,test_size=0.25,random_state = 0)

my_pipeline = make_pipeline(Imputer(), RandomForestRegressor())
my_pipeline.fit(train_X, train_y)
predicted_home_prices = my_pipeline.predict(test_X)
print mean_absolute_error(test_y,predicted_home_prices)

# 等价于以下

my_imputer = Imputer()
my_model = RandomForestRegressor()
imputed_train_X = my_imputer.fit_transform(train_X)
imputed_test_X = my_imputer.transform(test_X)
my_model.fit(imputed_train_X, train_y)
predictions = my_model.predict(imputed_test_X)
print mean_absolute_error(test_y,predictions)
'''


#训练之后，生成反映出各列和目标的关系图
'''
from sklearn.ensemble.partial_dependence import partial_dependence, plot_partial_dependence
import matplotlib.pyplot as plt

my_plots = plot_partial_dependence(my_model,       
                                   features=[0, 2], # column numbers of plots we want to show
                                   X=X,            # raw predictors data.
                                   feature_names=['Distance', 'Landsize', 'BuildingArea'], # labels on graphs
                                   grid_resolution=10) # number of values to plot on x axis
plt.show()
'''


#训练之后，保存模型
'''
from sklearn.externals import joblib

#save model
joblib.dump(melbourne_model,'model.pickle')
#load model
model = joblib.load('model.pickle')
'''

#预测保存数据
'''
# predict and save output
predict_cons = ['date','day_of_week']
#print ("The predictions are")
predicted_test_prices = gbdt.predict(test[predict_cons])
int_cnt = np.around(predicted_test_prices)


my_submission = pd.DataFrame({'date':test.date,'cnt':int_cnt.astype(int)})
my_submission.to_csv('submission20180223.csv',index = False,header = False,columns = ['date','cnt'])
my_submission.to_csv('result20180223.txt',index=False,header=False,columns = ['date','cnt'],sep='\t')
'''