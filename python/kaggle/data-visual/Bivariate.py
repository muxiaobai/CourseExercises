
# coding: utf-8

# In[1]:


import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from pandas import Series,DataFrame
import seaborn as sns 


# In[2]:


#https://www.kaggle.com/residentmario/bivariate-plotting-with-pandas/data
reviews = pd.read_csv("winemag-data_first150k.csv", index_col=0)
reviews.head()


# ## Bivariate plotting with pandas
# #### df.plot.scatter()	df.plot.hex()	df.plot.bar(stacked=True)	df.plot.line()

# In[3]:


# 价格和得分的关系 price  points  100个样本
reviews[reviews['price'] < 100].sample(100).plot.scatter(x='price', y='points')
plt.show()


# In[4]:


reviews[reviews['price'] < 100].plot.scatter(x='price', y='points')
plt.show()


# In[5]:


reviews[reviews['price'] < 100].plot.hexbin(x='price', y='points', gridsize=15)
plt.show()


# In[39]:



count_data = reviews.groupby(['points']).mean()
#[['Williams Selyem', 'Testarossa', 'DFJ Vinhos', 'Chateau Ste. Michelle', 'Columbia Crest', 'Concha y Toro', 'Kendall-Jackson', 'Trapiche', 'Bouchard Père & Fils', 'Kenwood']]
count_data.plot.bar()
plt.show()


# In[17]:


reviews.columns


# In[36]:


cate = reviews['winery'].value_counts().head(10)
cate.plot.line()
plt.show()


# In[27]:



count_data = reviews.groupby(['points']).mean()
#[cate]
count_data
#count_data.plot.bar(stacked=True)
#plt.show()


# In[42]:


pokemon = pd.read_csv("Pokemon.csv", index_col=0)
pokemon.head()


# In[43]:


pokemon_stats_legendary = pokemon.groupby(['Legendary', 'Generation']).mean()[['Attack', 'Defense']]
pokemon_stats_legendary.plot.bar(stacked=True)
plt.show()


# In[44]:


pokemon_stats_by_generation = pokemon.groupby('Generation').mean()[['HP', 'Attack', 'Defense', 'Sp. Atk', 'Sp. Def', 'Speed']]
pokemon_stats_by_generation.plot.line()
plt.show()

