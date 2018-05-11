
# coding: utf-8

# In[25]:


import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from pandas import Series,DataFrame
import seaborn as sns 


# In[26]:


input_data = pd.read_csv("winemag-data_first150k.csv")


# In[27]:


input_data.head()


# ## group

# In[28]:


input_data.groupby(["country"])["points","price"].sum()


# In[30]:


input_data.groupby(["country"])["points"].min()


# In[61]:


input_data.groupby(["country","province"])["points"].min()


# In[31]:


input_data.head()


# In[32]:



input_data.groupby(["country"])["points","price"].apply(lambda df: df.loc[df.points.argmax()])


# In[33]:


input_data.groupby(["country"])["points"].max()


# In[34]:


input_data.groupby(["country"])["points"].agg([len, min, max])


# In[37]:


input_data.loc[input_data["country"] =="Albania"]


# In[52]:


input_data.loc[input_data["country"] =="Slovakia"]


# ## 重复

# In[58]:


#重复为True，不重复为False
input_data.duplicated(subset=["description","points","price","country","province","variety"], keep='first').value_counts()


# In[55]:



input_data.duplicated().value_counts()

out_data = input_data.drop_duplicates(subset=["description","points","price","country","province","variety"],keep='last',inplace=False)


# In[56]:


out_data.duplicated(subset=["description","points","price","country","province","variety"], keep='first').value_counts()


# In[59]:


out_data.describe()


# In[42]:


input_data.describe()


# ## sort

# In[22]:


input_data.sort_values("points",ascending = False)


# In[24]:


input_data.sort_values(by = ["points","price"],ascending = False)


# ## type missing

# In[62]:


input_data.head()


# In[64]:


input_data.country.dtype


# In[65]:


input_data.price.dtype


# In[67]:


input_data.points.dtype


# In[68]:


input_data.points.astype("float64")


# In[69]:


input_data.points.dtype


# In[70]:


input_data.points.astype("int64")


# In[71]:


input_data.points.dtype


# In[72]:


input_data[input_data.country.isnull()]


# In[78]:


input_data["nonull_country"] = input_data.country.fillna("Unknown")


# In[82]:


input_data[input_data.nonull_country.isnull()]


# In[85]:


input_data.loc[input_data["nonull_country"] =="Unknown"]


# In[ ]:


#input_data.description.replace("@nose ", "@kerino")

