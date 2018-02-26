#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/selecting-and-filtering-in-pandas
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeRegressor
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split
from sklearn.externals import joblib
train_data = pd.read_table('../train_20171215.txt')
test_data = pd.read_table('../test_A_20171225.txt')
print train_data.describe() 
print test_data.describe()
predict_cons = ['date','day_of_week','cnt']
train_data = train_data[predict_cons]
y = train_data.pop('cnt')
train_data['day_of_week'] = train_data['day_of_week'].astype(str)
all_dumny_df = pd.get_dummies(train_data)

#all_df = pd.concat((train_data,test_data),axis = 0)
#all_df['day_of_week'] = all_df['day_of_week'].astype(str)
#pandas自带的get_dummies方法，可以帮你一键做到One-Hot。
#pd.get_dummies(all_df['day_of_week'], prefix='day_of_week').head()
#all_dumny_df = pd.get_dummies(all_df)
print all_dumny_df.head()
#print all_df['day_of_week'].value_counts()
print all_dumny_df.columns
X = all_dumny_df
print X.head()
#y = all_dumny_df.cnt
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


yancheng_model = DecisionTreeRegressor(max_leaf_nodes = 400,random_state = 0)
#yancheng_model.fit(train_x,train_y)#115.2038
yancheng_model.fit(X,y)#92.6425
predicted_num = yancheng_model.predict(val_x)
#print mean_absolute_error(val_y,predicted_num)
print mean_squared_error(val_y,predicted_num)


'''
def getmea(max_leaf_nodes,mea_train_x,mea_test_x,mea_train_y,mea_test_y):
	model = RandomForestRegressor(max_leaf_nodes = max_leaf_nodes,random_state = 0)
	model.fit(mea_train_x,mea_train_y)
	predicted_test = model.predict(mea_test_x)
	return mean_absolute_error(mea_test_y,predicted_test)

for max_leaf_nodes in [50,100,200,300,400,600,800,1000]:
	mea = getmea(max_leaf_nodes,train_x,val_x,train_y,val_y)
	print("max_leaf_nodes: %d ,mea: %d" %(max_leaf_nodes,mea))
'''
'''
def getest(max_leaf_nodes,mea_train_x,mea_test_x,mea_train_y,mea_test_y):
	model = RandomForestRegressor(n_estimators = max_leaf_nodes,max_leaf_nodes= 400,random_state = 0)
	model.fit(mea_train_x,mea_train_y)
	predicted_test = model.predict(mea_test_x)
	return mean_absolute_error(mea_test_y,predicted_test)

for max_leaf_nodes in [600,800,1000,1100,1200]:
	mea = getest(max_leaf_nodes,train_x,val_x,train_y,val_y)
	print("estimators: %d ,mea: %d" %(max_leaf_nodes,mea))
'''

# model and train
'''
forest_model = RandomForestRegressor(n_estimators = 1100,max_leaf_nodes = 400)
#forest_model.fit(train_x,train_y)#114.2411
forest_model.fit(X,y)#92.9538
predicted_forest_num = forest_model.predict(val_x)
print mean_absolute_error(val_y,predicted_forest_num)
'''
# predict and save output

test_data['day_of_week'] = test_data['day_of_week'].astype(str)
test_dumny_df = pd.get_dummies(test_data)
#print ("Making predictions for the following 5 houses")
#print (val_x.head())
#print ("The predictions are")
predicted_test_prices = yancheng_model.predict(test_dumny_df)
int_cnt = np.around(predicted_test_prices)


my_submission = pd.DataFrame({'date':test_data.date,'cnt':int_cnt.astype(int)})
my_submission.to_csv('submission20180208.csv',index = False,header = False,columns = ['date','cnt'])
my_submission.to_csv('result20180208.txt',index=False,header=False,columns = ['date','cnt'],sep='\t')

