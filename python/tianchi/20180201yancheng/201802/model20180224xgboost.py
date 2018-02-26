#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/selecting-and-filtering-in-pandas
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score
from sklearn import cross_validation
from sklearn import svm
from sklearn.learning_curve import learning_curve
from sklearn.grid_search import GridSearchCV
from sklearn.metrics import explained_variance_score
from sklearn.metrics import mean_squared_error
from sklearn.metrics import mean_absolute_error
from sklearn.ensemble import GradientBoostingRegressor
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.externals import joblib
from xgboost import XGBRegressor
train = pd.read_table('../train_20171215.txt')
test= pd.read_table('../test_A_20171225.txt')
#print train_data.describe() 
actions1 = train.groupby(['date','day_of_week'], as_index=False)['cnt'].agg({'count1':np.sum})
df_train_target = actions1['count1'].values
df_train_data = actions1.drop(['count1'],axis = 1).values

# 切分数据（训练集和测试集）
cv = cross_validation.ShuffleSplit(len(df_train_data), n_iter=5,test_size=0.2,random_state=0)


predict_cons = ['date','day_of_week']
X = train[predict_cons]
y = train.cnt
train_x,val_x,train_y,val_y = train_test_split(X,y,test_size = 0.2,random_state= 0)

#xgboost

params = [100,200,300,400,500,600,700,800,1000]
test_scores = []
for param in params:
    clf = XGBRegressor(n_estimators=param)
    test_score = np.sqrt(-cross_val_score(clf, train_x, train_y, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))
print test_scores
plt.plot(params, test_scores)
plt.title("n_estimators vs CV Error");
# 一定要加上这句才能让画好的图显示在屏幕上
plt.show()
# 将当前figure的图保存到文件result.png
#plt.savefig('./xgboostparams.png')

# 91 16889
xgb = XGBRegressor(max_depth=6,n_estimators=400)
xgb.fit(X, y)
print mean_absolute_error(val_y,xgb.predict(val_x))
print(mean_squared_error(val_y,xgb.predict(val_x)))

#gbdt
'''
print "GradientBoostingRegressor"    
gbdt = GradientBoostingRegressor(n_estimators = 1000,max_leaf_nodes = 400)
gbdt.fit(X, y)#17083
#RandomForestRegressor 93  16938
#GradientBoostingRegressor 90 16866
#XGBRegressor 100 19939 
print mean_absolute_error(val_y,gbdt.predict(val_x))
print(mean_squared_error(val_y,gbdt.predict(val_x)))

# predict and save output
#print ("The predictions are")
predicted_test_prices = gbdt.predict(test[predict_cons])
int_cnt = np.around(predicted_test_prices)


my_submission = pd.DataFrame({'date':test.date,'cnt':int_cnt.astype(int)})
my_submission.to_csv('submission20180223.csv',index = False,header = False,columns = ['date','cnt'])
my_submission.to_csv('result20180223.txt',index=False,header=False,columns = ['date','cnt'],sep='\t')
'''
