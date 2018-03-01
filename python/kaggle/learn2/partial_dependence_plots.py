#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/handling-missing-values
# one tree
import pandas as pd
import numpy as np
from sklearn.metrics import mean_absolute_error
from sklearn.model_selection import train_test_split
from sklearn.ensemble import GradientBoostingRegressor, GradientBoostingClassifier
from sklearn.ensemble.partial_dependence import partial_dependence, plot_partial_dependence
from sklearn.preprocessing import Imputer
from sklearn.externals import joblib
import matplotlib.pyplot as plt
# input data
melbourne_file_path ='melb_data.csv'
melbourne_data = pd.read_csv(melbourne_file_path)
melbourne_data = melbourne_data.dropna()
# handle data 

cols_to_use = ['Distance', 'Landsize', 'BuildingArea']

def get_some_data():
    data = melbourne_data;
    y = data.Price
    X = data[cols_to_use]
    my_imputer = Imputer()
    imputed_X = my_imputer.fit_transform(X)
    return imputed_X, y
    

X, y = get_some_data()
'''
train_x,val_x,train_y,val_y = train_test_split(X,y,test_size=0.25,random_state = 0)

def getmea(max_leaf_nodes,mea_train_x,mea_test_x,mea_train_y,mea_test_y):
	model = GradientBoostingRegressor(n_estimators = max_leaf_nodes,random_state = 0)
	model.fit(mea_train_x,mea_train_y)
	predicted_test = model.predict(mea_test_x)
	return mean_absolute_error(mea_test_y,predicted_test)
test_scores = []
#params = [300,350,400,450,500,550,600,650,700,750]
params = [100,500,1000,1500,650,700,750]

for max_leaf_nodes in params:
	mea = getmea(max_leaf_nodes,train_x,val_x,train_y,val_y)
    #test_scores.append(np.mean(mea)))
	print("Max_leaf_nodes: %d ,mea: %d" %(max_leaf_nodes,mea))
plt.plot(params, test_scores)
plt.title("max_leaf_nodes Error" + str(params));
plt.show()
'''

my_model = GradientBoostingRegressor(n_estimators=10)
my_model.fit(X, y)
my_plots = plot_partial_dependence(my_model, 
                                   features=[0,1,2], 
                                   X=X, 
                                   feature_names=cols_to_use, 
                                   grid_resolution=20)
plt.show()
#melbourne_predictors = ['Rooms','Bathroom','Landsize','BuildingArea','YearBuilt','Lattitude','Longtitude']
#X = melbourne_data[melbourne_predictors]

# split data into train and validation
# how to know test_size and random_state?
#train_x,val_x,train_y,val_y = train_test_split(X,y,test_size=0.25,random_state = 0)

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

