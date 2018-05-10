
# coding: utf-8

# In[2]:


import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from pandas import Series,DataFrame
import seaborn as sns 



# In[3]:


input_data = pd.read_csv("winemag-data_first150k.csv")


# In[7]:


input_data.head()


# ### 获取，查询等

# In[17]:


input_data["description"]
input_data["description"][0]
input_data["description"][0:10]
input_data[0:10]

input_data.iloc[:1,2]



# In[6]:


# 第一行
input_data.iloc[0]


# In[12]:



# 第一行第二列
input_data.iloc[0,1]


# In[14]:


# 最后一行，
input_data.iloc[-1:]
# 最后五行
input_data.iloc[-5:]


# In[9]:



# 第二列
input_data.iloc[:,1]


# In[10]:


#第1,3,5行的第四列
input_data.iloc[[0,2,4],3]


# In[11]:


input_data.head()


# In[15]:


#  1到9行"country","points"两列
input_data.loc[0:8,["country","points"]]


# In[21]:


#  "points"列
input_data.loc[:,"points"]


# In[25]:


input_data.loc[(input_data["country"]=="US")&(input_data.points>95)]


# In[43]:


input_data.query('country == ["US"]')


# In[26]:


input_data.loc[(input_data["country"]=="US")|(input_data.points>95)]


# ### isin()  isnull() notnull() sum() count()

# In[36]:


input_data.isnull().sum()


# In[37]:



input_data.isnull().sum().sort_values(ascending=False).head(10)


# In[42]:



input_data.loc[input_data.country.isnull()|input_data.province.isnull()]


# In[41]:


input_data.count()

#input_data["country"].isnull()
#.isnull().sum().sort_values(ascending=False).head(10)


# In[33]:



input_data["country"].value_counts()


# In[44]:


input_data.groupby('country').count()


# In[30]:



input_data.loc[input_data.country.isin(["Spain","France"])]

