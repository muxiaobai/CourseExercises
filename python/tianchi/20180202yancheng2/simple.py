#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/dansbecker/selecting-and-filtering-in-pandas

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
#from sklearn import cross_validation
#from sklearn.learning_curve import learning_curve
#from sklearn.grid_search import GridSearchCV
from sklearn.metrics import explained_variance_score
from sklearn.metrics import mean_absolute_error
from sklearn.metrics import mean_squared_error
from sklearn.ensemble import GradientBoostingRegressor
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.externals import joblib

train = pd.read_table('yancheng_train_20171226.csv')
test= pd.read_table('yancheng_testA_20171225.csv')
train.set_index('index',drop=False,append=True,inplace=False,verify_integrity=False) 

predict_cons = ['index','sale_date','class_id','brand_id','compartment','type_id','level_id','department_id','TR','gearbox_type','displacement','if_charging','price_level','price','driven_type_id','fuel_type_id','newenergy_type_id','emission_standards_id','if_MPV_id','if_luxurious_id','power','cylinder_number','engine_torque','car_length','car_width','car_height','total_quality','equipment_quality','rated_passenger','wheelbase','front_track','rear_track']
#['index','sale_date','class_id']
X = train[predict_cons]
print X.index
y = train.sale_quantity
#result=pd.merge(test[['predict_date','class_id']],X,how='left',on=['class_id'])
#print result.columns
#print train.columns #head() #describe()
'''
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


my_submission = pd.DataFrame({'predict_date':test.predict_date,'class_id':test.class_id,'predict_quantity':int_cnt.astype(int)})
my_submission.to_csv('submission20180223.csv',index = False,header = False,columns = ['date','cnt'])
my_submission.to_csv('result20180223.txt',index=False,header=False,columns = ['date','cnt'],sep='\t')
'''
