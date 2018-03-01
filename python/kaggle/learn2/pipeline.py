#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/handling-missing-values
# one tree
import pandas as pd
import numpy as np
from sklearn.metrics import mean_absolute_error
from sklearn.model_selection import train_test_split
from sklearn.ensemble import GradientBoostingRegressor, GradientBoostingClassifier,RandomForestRegressor
from sklearn.ensemble.partial_dependence import partial_dependence, plot_partial_dependence
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import Imputer
from sklearn.externals import joblib
import matplotlib.pyplot as plt
# input data
melbourne_file_path ='melb_data.csv'
melbourne_data = pd.read_csv(melbourne_file_path)
melbourne_data = melbourne_data.dropna()
# handle data 

cols_to_use = ['Rooms', 'Distance', 'Landsize', 'BuildingArea', 'YearBuilt']
data = melbourne_data;
y = data.Price
X = data[cols_to_use]

train_X,test_X,train_y,test_y = train_test_split(X,y,test_size=0.25,random_state = 0)

#管道机制更像是编程技巧的创新，而非算法的创新
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

