#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/random-forests
import numpy as np
import pandas as pd
from sklearn.preprocessing import Imputer
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score
from sklearn.metrics import mean_squared_error
from sklearn.metrics import mean_absolute_error
from sklearn.ensemble import GradientBoostingRegressor
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.ensemble import RandomForestRegressor
from xgboost import XGBRegressor
from sklearn.externals import joblib
import matplotlib.pyplot as plt


# input data
train_data = pd.read_csv('train.csv')#train.csv
test_data = pd.read_csv('test.csv')#train.csv


train_data.dropna(axis=0, subset=['SalePrice'], inplace=True)
y = train_data.SalePrice
X = train_data.drop(['SalePrice'], axis=1).select_dtypes(exclude=['object'])


# split data into train and validation
# how to know test_size and random_state?
train_X, test_X, train_y, test_y  = train_test_split(X,y,test_size=0.25,random_state = 0)

my_imputer = Imputer()
train_X = my_imputer.fit_transform(train_X)
test_X = my_imputer.transform(test_X)

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
# clf = XGBRegressor() 17165
# XGBRegressor(n_estimators=400)  16330
'''
params = [.02,.03,.04,.05,.06,.07,.08,.09,.10]#[1:1001:50][100,200,300,400,500]
test_scores = []
for param in params:
    clf = XGBRegressor(n_estimators=400,learning_rate=param)
    test_score = np.sqrt(-cross_val_score(clf, train_X, train_y, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))
plt.plot(params, test_scores)
plt.title("n_estimator vs CV Error" + str(params));
# 一定要加上这句才能让画好的图显示在屏幕上
plt.show()
'''

my_model = XGBRegressor(n_estimators=400)
my_model.fit(train_X, train_y,verbose=False)
predictions = my_model.predict(test_X)
print("Mean Absolute Error : " + str(mean_absolute_error(predictions, test_y)))

#save model
#joblib.dump(melbourne_model,'model.pickle')

#load model
#model = joblib.load('model.pickle')

