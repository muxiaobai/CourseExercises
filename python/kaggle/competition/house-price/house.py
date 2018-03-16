
# coding: utf-8

# In[1]:


import numpy as np
import pandas as pd


# In[2]:


train_df = pd.read_csv('./input/train.csv', index_col=0)
test_df = pd.read_csv('./input/test.csv', index_col=0)


# In[4]:


train_df.head()


# In[6]:


#label本身并不平滑。为了我们分类器的学习更加准确，我们会首先把label给“平滑化”（正态化）
import matplotlib.pyplot as plt
prices = pd.DataFrame({"price":train_df["SalePrice"], "log(price + 1)":np.log1p(train_df["SalePrice"])})
prices.hist()
plt.show()


# In[7]:


y_train = np.log1p(train_df.pop('SalePrice'))


# In[8]:


all_df = pd.concat((train_df, test_df), axis=0)


# In[19]:


all_df['MSSubClass'].dtypes
all_df['MSSubClass'].value_counts()
all_df['MSSubClass'] = all_df['MSSubClass'].astype(str)
pd.get_dummies(all_df['MSSubClass'], prefix='MSSubClass').head()


# In[20]:


all_dummy_df = pd.get_dummies(all_df)
all_dummy_df.head()


# In[21]:


all_dummy_df.isnull().sum().sort_values(ascending=False).head(10)


# In[22]:


mean_cols = all_dummy_df.mean()
mean_cols.head(10)
all_dummy_df = all_dummy_df.fillna(mean_cols)
all_dummy_df.isnull().sum().sum()


# In[23]:


dummy_train_df = all_dummy_df.loc[train_df.index]
dummy_test_df = all_dummy_df.loc[test_df.index]


# In[24]:


from sklearn.linear_model import Ridge
from sklearn.model_selection import cross_val_score

X_train = dummy_train_df.values
X_test = dummy_test_df.values


# ### Ridge

# In[25]:


alphas = np.logspace(-3, 2, 50)
test_scores = []
for alpha in alphas:
    clf = Ridge(alpha)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[27]:


plt.plot(alphas, test_scores)
plt.title("Alpha vs CV Error");
plt.show()


# 15最佳
# 
# ### RandomForestRegressor

# In[28]:


from sklearn.ensemble import RandomForestRegressor
max_features = [.1, .3, .5, .7, .9, .99]
test_scores = []
for max_feat in max_features:
    clf = RandomForestRegressor(n_estimators=200, max_features=max_feat)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=5, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[29]:


plt.plot(max_features, test_scores)
plt.title("Max Features vs CV Error");
plt.show()


# ### xgboost

# In[36]:


from xgboost import XGBRegressor
params = [1,2,3,4,5,6]
test_scores = []
for param in params:
    clf = XGBRegressor(max_depth=param)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[ ]:


plt.plot(params, test_scores)
plt.title("max_depth vs CV Error");
plt.show()


# ### bagging   

# In[38]:


from sklearn.ensemble import BaggingRegressor
params = [10, 15, 20, 25, 30, 35, 40, 45, 50]
test_scores = []
for param in params:
    clf = BaggingRegressor(n_estimators=param)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[39]:


plt.plot(params, test_scores)
plt.title("max_depth vs CV Error");
plt.show()


# ### 基本为ridge  ，效果更好一点

# In[40]:


ridge = Ridge(15)
params = [1, 10, 15, 20, 25, 30, 40]
test_scores = []
for param in params:
    clf = BaggingRegressor(n_estimators=param, base_estimator=ridge)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[41]:


plt.plot(params, test_scores)
plt.title("max_depth vs CV Error");
plt.show()


# ### Ensemble
# 

# In[30]:


ridge = Ridge(alpha=15)
rf = RandomForestRegressor(n_estimators=500, max_features=.3)

ridge.fit(X_train, y_train)
rf.fit(X_train, y_train)


# In[31]:


y_ridge = np.expm1(ridge.predict(X_test))
y_rf = np.expm1(rf.predict(X_test))
y_final = (y_ridge + y_rf) / 2


# In[32]:


submission_df = pd.DataFrame(data= {'Id' : test_df.index, 'SalePrice': y_final})


# In[33]:


submission_df.head()


# In[37]:


submission_df.to_csv('submission20180316.csv',index = False,header = True,columns = ['Id','SalePrice'])

