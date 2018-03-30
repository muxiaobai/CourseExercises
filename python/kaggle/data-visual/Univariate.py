
# coding: utf-8

# In[4]:


import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from pandas import Series,DataFrame
import seaborn as sns 


# In[5]:


reviews = pd.read_csv("winemag-data_first150k.csv", index_col=0)
reviews.head(3)


# ### Univariate plotting with pandas 单一变量

# In[7]:


reviews['province'].value_counts().head(10).plot.bar()
plt.show()


# In[8]:


(reviews['province'].value_counts().head(10) / len(reviews)).plot.bar()
plt.show()


# ###  三分之一的酒来自加尼福尼亚

# 得分['points']分数越高越好

# In[9]:


reviews['points'].value_counts().sort_index().plot.bar()
plt.show()


# In[10]:


reviews['points'].value_counts().sort_index().plot.line()
plt.show()


# In[11]:


reviews['points'].value_counts().sort_index().plot.area()
plt.show()


# In[14]:


reviews['points'].plot.hist()# 直方图  Histograms
plt.show()


# In[12]:


reviews[reviews['price'] < 200]['price'].plot.hist()
plt.show()


# In[13]:


reviews['price'].plot.hist()
plt.show()


# In[15]:


reviews[reviews['price'] > 1500]

