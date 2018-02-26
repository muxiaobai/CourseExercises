#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/selecting-and-filtering-in-pandas
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error
from sklearn.model_selection import train_test_split
from sklearn.externals import joblib
train_data = pd.read_table('train_20171215.txt')
test_data = pd.read_table('test_A_20171225.txt')
#print train_data.describe() 
#print test_data.describe()
predict_cons = ['date','day_of_week']
X = train_data[predict_cons]
y = train_data.cnt
train_x,val_x,train_y,val_y = train_test_split(X,y,test_size = 0.2,random_state= 0)
# find max_leaf_nodes, then get 400||800 
'''
def getmea(max_leaf_nodes,mea_train_x,mea_test_x,mea_train_y,mea_test_y):
	model = DecisionTreeRegressor(max_leaf_nodes = max_leaf_nodes,random_state = 0)
	model.fit(mea_train_x,mea_train_y)
	predicted_test = model.predict(mea_test_x)
	return mean_absolute_error(mea_test_y,predicted_test)

for max_leaf_nodes in [5,400,800,1000,2000,5000]:
	mea = getmea(max_leaf_nodes,train_x,val_x,train_y,val_y)
	print("Max_leaf_nodes: %d ,mea: %d" %(max_leaf_nodes,mea))
'''

'''
yancheng_model = DecisionTreeRegressor(max_leaf_nodes = 400,random_state = 0)
yancheng_model.fit(train_x,train_y)
predicted_num = yancheng_model.predict(val_x)
print mean_absolute_error(val_y,predicted_num)#115.2038
'''

'''
def getest(max_leaf_nodes,mea_train_x,mea_test_x,mea_train_y,mea_test_y):
	model = RandomForestRegressor(n_estimators = max_leaf_nodes,random_state = 0)
	model.fit(mea_train_x,mea_train_y)
	predicted_test = model.predict(mea_test_x)
	return mean_absolute_error(mea_test_y,predicted_test)

for max_leaf_nodes in [5,400,800,1000,2000,5000]:
	mea = getest(max_leaf_nodes,train_x,val_x,train_y,val_y)
	print("estimators: %d ,mea: %d" %(max_leaf_nodes,mea))
'''

# model and train
forest_model = RandomForestRegressor(n_estimators = 1000,max_leaf_nodes = 400)
forest_model.fit(train_x,train_y)#114.2411
#forest_model.fit(X,y)#93.166
predicted_forest_num = forest_model.predict(val_x)
print mean_absolute_error(val_y,predicted_forest_num)

# predict and save output
#print ("Making predictions for the following 5 houses")
#print (val_x.head())
#print ("The predictions are")
predicted_test_prices = forest_model.predict(test_data[predict_cons])
#print (predicted_home_prices)
int_cnt = np.around(predicted_test_prices)
#int_cnt.dtype = 'int64'
#print int_cnt

#print int_cnt.shape

my_submission = pd.DataFrame({'date':test_data.date,'cnt':int_cnt})
my_submission.to_csv('submission20180207.csv',index = False,header = False,columns = ['date','cnt'])
my_submission.to_csv('result20180207.txt',index=False,header=False,columns = ['date','cnt'],sep='\t')

