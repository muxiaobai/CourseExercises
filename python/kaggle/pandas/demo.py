#!/usr/bin/python
# -*- coding: UTF-8 -*-
#https://www.kaggle.com/learn/pandas
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from pandas import Series,DataFrame
import seaborn as sns 

# 常用


# 基本信息
input_data.columns
input_data["country"].unique()
input_data["points"].describe()
input_data.head()
input_data.info()
input_data["country"].value_counts()

# 类型
input_data.points.dtype
input_data.points.astype("int64")

# 查空
input_data.isnull().sum()
input_data.isnull().sum().sort_values(ascending=False).head(10)
#df.fillna(value=0)
mean = input_data["price"].mean()
input_data["price_nonull"] = input_data["price"].fillna(mean)
print ("price",input_data["price"].isnull().sum())
print ("price_nonull",input_data["price_nonull"].isnull().sum())


#查重
#重复为True，不重复为False
input_data.duplicated(subset=["description","points","price","country","province","variety"], keep='first').value_counts()
input_data.duplicated().value_counts()
out_data = input_data.drop_duplicates(subset=["description","points","price","country","province","variety"],keep='last',inplace=False)


# 获取

input_data.loc[input_data.country.isin(["Spain","France"])]
input_data.loc[input_data.country.isnull()|input_data.province.isnull()]
input_data.loc[(input_data["country"]=="US")|(input_data.points>95)]