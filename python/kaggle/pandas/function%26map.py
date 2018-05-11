
# coding: utf-8

# In[1]:


import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from pandas import Series,DataFrame
import seaborn as sns 



# In[2]:


input_data = pd.read_csv("winemag-data_first150k.csv")


# In[32]:


input_data.head()


# ## 数量 中位数 平均值 最大最小四分位数
# ## 类别数，各个类别的数量

# In[33]:


input_data["points"].describe()


# In[34]:


input_data["country"].unique()


# In[35]:


input_data["country"].value_counts()


# ##  查重查空 填充mean，改成相对值

# In[36]:


input_data["price"].describe()


# In[38]:


#去重
(input_data.duplicated()).value_counts()
input_data = input_data.drop_duplicates()
(input_data.duplicated()).value_counts()


# In[41]:


input_data.columns


# In[39]:


# 去空
input_data["price"].isnull().sum()


# In[83]:


#df.fillna(value=0)
mean = input_data["price"].mean()
input_data["price_nonull"] = input_data["price"].fillna(mean)
print ("price",input_data["price"].isnull().sum())
print ("price_nonull",input_data["price_nonull"].isnull().sum())


# ## map apply修改数据,构造新的数据列等

# In[84]:


#map
print (mean)
input_data["price_map_new"] = input_data["price_nonull"].map(lambda p: p-mean )


# In[56]:


mean = input_data["price_nonull"].mean()


# In[85]:


#apply
mean = input_data["price_nonull"].mean()
def remean_points(srs):
    srs["points_apply_new"] = srs.price_nonull - mean
    return srs["points_apply_new"]
input_data["points_apply_new"] = input_data.apply(remean_points, axis='columns')


# In[86]:


input_data.head()


# ## map apply 效率比较低，以为是一个一个的循环操作，相对于直接的“+”“-”等运算符比较慢，但比这些灵活，效果一样，合并，创建新的数据列

# In[61]:


#效果一样
input_data["price_opera"] = input_data["price"]- mean


# In[62]:


input_data.head()


# In[4]:



#input_data["variety"].isnull().sum()
input_data["country"].isnull().sum()


# In[5]:


output = input_data.loc[input_data.country.notnull()]


# In[6]:


output.country.isnull().sum()
#output.variety.isnull().sum()


# In[7]:


output["country_variety"] = output.country + "-" + output.variety


# In[8]:


output.head()


# In[13]:


output["description"].str.contains("blackberry").value_counts()


# In[14]:


output["blackberry"] = output["description"].str.contains("blackberry")
output.head()


# In[ ]:


#从description中拿相同描述的词出现的频率


print (mean)
input_data["price_map_new"] = input_data["price_nonull"].map(lambda p: p-mean )

