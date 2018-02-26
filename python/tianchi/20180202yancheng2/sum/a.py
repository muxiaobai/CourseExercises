#!/usr/bin/python
# -*- coding: UTF-8 -*-
import pandas as pd

train_df = pd.read_csv('../yancheng_train_20171226.csv', low_memory=False)
test_df = pd.read_csv('../yancheng_testA_20171225.csv', low_memory=False)

train_sum10=train_df[(train_df.sale_date==201710)].groupby(['class_id']).sale_quantity.sum().round()

predicted=train_sum10.reset_index()

result=pd.merge(test_df[['predict_date','class_id']],predicted,how='left',on=['class_id'])

result.fillna(0)

result.columns=['predict_date','class_id','predict_quantity']

   

result.to_csv('result_201710.csv',index=False,header=True)  
