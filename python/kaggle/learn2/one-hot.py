#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/handling-missing-values
# one tree
import pandas as pd
from sklearn.model_selection import cross_val_score
from sklearn.model_selection import train_test_split
#from sklearn.model_selection import score_dataset
from sklearn.preprocessing import Imputer
from sklearn.ensemble import RandomForestRegressor
from sklearn.tree import DecisionTreeRegressor
from sklearn.metrics import mean_absolute_error
from sklearn.externals import joblib

# input data
melbourne_data = pd.read_csv('train.csv')#train.csv
test_data = pd.read_csv('test.csv')#train.csv

new_data  =melbourne_data.copy()

# data describe
#print (melbourne_data.describe())
#print (melbourne_data.columns)
#print (melbourne_price_data.head())
#print (melbourne_data.isnull().sum())
#print (X.head())
melbourne_data.dropna(axis=0, subset=['SalePrice'], inplace=True)
y = melbourne_data.SalePrice
cols_with_missing = [col for col in melbourne_data.columns 
                                 if melbourne_data[col].isnull().any()]           
X = melbourne_data.drop(['Id', 'SalePrice'] + cols_with_missing, axis=1)

#X = X.select_dtypes(exclude=['object'])
# For the sake of keeping the example simple, we'll use only numeric predictors. 

low_cardinality_cols = [cname for cname in X.columns if 
                                X[cname].nunique() < 10 and
                                X[cname].dtype == "object"]
numeric_cols = [cname for cname in X.columns if 
                                X[cname].dtype in ['int64', 'float64']]
my_cols = low_cardinality_cols + numeric_cols
train_predictors = X[my_cols]

#print train_predictors.dtypes.sample(10)

# split data into train and validation
# how to know test_size and random_state?
X_train,X_test,y_train,y_test = train_test_split(train_predictors,y,test_size=0.25,random_state = 0)
# handle data ,Processing data and comparison of raw data

def get_mae(X_mea, y_mea):
    # multiple by -1 to make positive MAE score instead of neg value returned as sklearn convention   
	return -1 * cross_val_score(RandomForestRegressor(50), X_mea, y_mea, scoring = 'neg_mean_absolute_error').mean()

# A Drop Columns with Missing Values
#data_without_missing_values = melbourne_data.dropna(axis=1)


predictors_without_categoricals = X_train.select_dtypes(exclude=['object'])
mae_without_categoricals = get_mae(predictors_without_categoricals, y_train)
print('Mean Absolute Error when Dropping Categoricals: ' + str(int(mae_without_categoricals)))


one_hot_encoded_training_predictors = pd.get_dummies(X_train)
mae_one_hot_encoded = get_mae(one_hot_encoded_training_predictors, y_train)
print('Mean Abslute Error with One-Hot Encoding: ' + str(int(mae_one_hot_encoded)))


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
#melbourne_model = DecisionTreeRegressor(max_leaf_nodes = 400,random_state = 0)
#melbourne_model.fit(train_x,train_y)

# predict and save output
'''
#print ("Making predictions for the following 5 houses")
#print (val_x.head())
#print ("The predictions are")
predicted_test_prices = forest_model.predict(test[melbourne_predictors])
#print (predicted_home_prices)
my_submission = pd.DataFrame({'Id':test.Id,'SalePrice':predicted_test_prices})
my_submission.to_csv('submission.csv',index = False,header = False,columns = ['Id','SalePrice'])
my_submission.to_csv('result.txt',index=False,header=False,sep='\t')
'''

# validation 
#predicted_home_prices = melbourne_model.predict(val_x)
#print mean_absolute_error(val_y,predicted_home_prices)

#save model
#joblib.dump(melbourne_model,'model.pickle')
#load model
#model = joblib.load('model.pickle')

