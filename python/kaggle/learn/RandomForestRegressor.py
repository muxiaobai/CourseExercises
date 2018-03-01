#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/random-forests
# vary trees
import pandas as pd
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split
from sklearn.externals import joblib
# input data
melbourne_file_path ='train.csv'
test = pd.read_csv('test.csv')
melbourne_data = pd.read_csv(melbourne_file_path)
#melbourne_data = melbourne_data.dropna()

#  data describe
'''
print (melbourne_data.describe())
print (melbourne_data.columns)
melbourne_price_data = melbourne_data.Price
print (melbourne_price_data.head())
'''
y = melbourne_data.SalePrice
melbourne_predictors = ['LotArea', 'OverallQual', 'YearBuilt', 'TotRmsAbvGrd']
X = melbourne_data[melbourne_predictors]
#print (X.head())
#print X.isnull()

# split data into train and validation
# how to know test_size and random_state?
train_x,val_x,train_y,val_y = train_test_split(X,y,test_size=0.25,random_state = 0)

# find max_leaf_nodes, then get 400
'''
def getmea(max_leaf_nodes,mea_train_x,mea_test_x,mea_train_y,mea_test_y):
	model = DecisionTreeRegressor(max_leaf_nodes = max_leaf_nodes,random_state = 0)
	model.fit(mea_train_x,mea_train_y)
	predicted_test = model.predict(mea_test_x)
	return mean_absolute_error(mea_test_y,predicted_test)

for max_leaf_nodes in [300,350,400,450,500,550,600,650,700,750]:
	mea = getmea(max_leaf_nodes,train_x,val_x,train_y,val_y)
	print("Max_leaf_nodes: %d ,mea: %d" %(max_leaf_nodes,mea))

'''
# model and train
forest_model = RandomForestRegressor()
forest_model.fit(train_x,train_y)


# validation 
predicted_home_prices = forest_model.predict(val_x)
print ("The  mean_absolute_error are")
print mean_absolute_error(val_y,predicted_home_prices)

# predict and save output
#print ("Making predictions for the following 5 houses")
#print (val_x.head())
#print ("The predictions are")
predicted_test_prices = forest_model.predict(test[melbourne_predictors])
#print (predicted_home_prices)
my_submission = pd.DataFrame({'Id':test.Id,'SalePrice':predicted_test_prices})
my_submission.to_csv('submission.csv',index = False,header = True)
my_submission.to_csv('result.txt',index=False,header=False,sep='\t')

#save model
#joblib.dump(melbourne_model,'model.pickle')

#load model
#model = joblib.load('model.pickle')

