#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/handling-missing-values
# one tree
import pandas as pd
import numpy as np
from sklearn.metrics import mean_absolute_error
from sklearn.model_selection import cross_val_score
from sklearn.model_selection import train_test_split
from sklearn.ensemble import GradientBoostingRegressor, GradientBoostingClassifier,RandomForestRegressor,RandomForestClassifier
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


my_other_model = RandomForestClassifier()
my_other_model.fit(train_X, train_y)
cv_other_scores = cross_val_score(my_other_model, train_X, train_y, scoring='neg_mean_absolute_error')
print(cv_other_scores)

print('Mean Absolute Error %2f' %(-1 * cv_other_scores.mean()))


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

