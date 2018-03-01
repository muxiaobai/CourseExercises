#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/handling-missing-values
# one tree
import pandas as pd
import numpy as np
from sklearn.metrics import mean_absolute_error
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import cross_val_score
from sklearn.externals import joblib
import matplotlib.pyplot as plt
# input data
melbourne_file_path ='AER_credit_card_data.csv'
melbourne_data = pd.read_csv(melbourne_file_path,true_values = ['yes'],
                   false_values = ['no'])
#melbourne_data = melbourne_data.dropna()
# handle data 

data = melbourne_data;
print data.head(30)#describe()

y = data.card
X = data.drop(['card'],axis =1)

my_model = RandomForestClassifier()
my_model.fit(X, y)

cv_scores = cross_val_score(my_model, X, y, scoring='accuracy')
print("Cross-val accuracy: %f" %cv_scores.mean())# 0.9810

#支出是否意味着这张卡片或申请前使用的卡片上的支出？
# card == False 的很可能expenditure == 0.0000
expenditures_cardholders = melbourne_data.expenditure[melbourne_data.card]
expenditures_noncardholders = melbourne_data.expenditure[~melbourne_data.card]

# 有卡的有2%在这张卡上没有消费支出
# 没有卡的全都是没有在这张卡上消费支出的
# 逻辑上的因果关系倒置了
print('Fraction of those who received a card with no expenditures: %.2f' \
      %(( expenditures_cardholders == 0).mean()))# 0.02
print('Fraction of those who received a card with no expenditures: %.2f' \
      %((expenditures_noncardholders == 0).mean()))# 1.00

no_expenditure = data.drop(['card','expenditure','share', 'active', 'majorcards'],axis =1)

my_other_model = RandomForestClassifier()
my_other_model.fit(no_expenditure, y)
cv_other_scores = cross_val_score(my_other_model, no_expenditure, y, scoring='accuracy')
print("Cross-val accuracy: %f" %cv_other_scores.mean())#0.809


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

