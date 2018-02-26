#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/selecting-and-filtering-in-pandas
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.metrics import mean_absolute_error
from sklearn.model_selection import train_test_split
from sklearn import cross_validation
from sklearn import svm
from sklearn.learning_curve import learning_curve
from sklearn.grid_search import GridSearchCV
from sklearn.metrics import explained_variance_score
from sklearn.metrics import mean_squared_error
from sklearn.ensemble import GradientBoostingRegressor
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.externals import joblib
train = pd.read_table('../train_20171215.txt')
test= pd.read_table('../test_A_20171225.txt')
#test= pd.read_table('../answer_top_A_20180225.txt')


#print train_data.describe() 
actions1 = train.groupby(['date','day_of_week'], as_index=False)['cnt'].agg({'count1':np.sum})
df_train_target = actions1['count1'].values
df_train_data = actions1.drop(['count1'],axis = 1).values

# 切分数据（训练集和测试集）
cv = cross_validation.ShuffleSplit(len(df_train_data), n_iter=5,test_size=0.2,random_state=0)
'''
print "GradientBoostingRegressor"    
for train, test in cv:    
    gbdt = GradientBoostingRegressor().fit(df_train_data[train], df_train_target[train])
    result1 = gbdt.predict(df_train_data[test])
    print(mean_squared_error(result1,df_train_target[test]))
    print '......'
'''
predict_cons = ['date','day_of_week']
X = train[predict_cons]
y = train.cnt
train_x,val_x,train_y,val_y = train_test_split(X,y,test_size = 0.2,random_state= 0)

print "GradientBoostingRegressor"    
gbdt = GradientBoostingRegressor(n_estimators = 1000,max_leaf_nodes = 400)
gbdt.fit(X, y)#17083
#RandomForestRegressor 93  16938
#GradientBoostingRegressor 90 16866
print mean_absolute_error(val_y,gbdt.predict(val_x))
print(mean_squared_error(val_y,gbdt.predict(val_x)))

# predict and save output
#print ("The predictions are")
predicted_test_prices = gbdt.predict(test[predict_cons])
int_cnt = np.around(predicted_test_prices)


my_submission = pd.DataFrame({'date':test.date,'cnt':int_cnt.astype(int)})
my_submission.to_csv('submission20180223.csv',index = False,header = False,columns = ['date','cnt'])
my_submission.to_csv('result20180223.txt',index=False,header=False,columns = ['date','cnt'],sep='\t')

