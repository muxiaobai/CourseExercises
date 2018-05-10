
# coding: utf-8

# # 房价预测案例（进阶版）
# 
# 这是进阶版的notebook。主要是为了比较几种模型框架。所以前面的特征工程部分内容，我也并没有做任何改动，重点都在后面的模型建造section
# 
# ## Step 1: 检视源数据集

# In[71]:


import numpy as np
import pandas as pd


# #### 读入数据
# 
# * 一般来说源数据的index那一栏没什么用，我们可以用来作为我们pandas dataframe的index。这样之后要是检索起来也省事儿。
# 
# * 有人的地方就有鄙视链。跟知乎一样。Kaggle的也是个处处呵呵的危险地带。Kaggle上默认把数据放在*input*文件夹下。所以我们没事儿写个教程什么的，也可以依据这个convention来，显得自己很有逼格。。

# In[72]:


train_df = pd.read_csv('../input/train.csv', index_col=0)
test_df = pd.read_csv('../input/test.csv', index_col=0)


# #### 检视源数据

# In[73]:


train_df.head()


# 这时候大概心里可以有数，哪些地方需要人为的处理一下，以做到源数据更加好被process。

# ## Step 2: 合并数据
# 
# 这么做主要是为了用DF进行数据预处理的时候更加方便。等所有的需要的预处理进行完之后，我们再把他们分隔开。
# 
# 首先，SalePrice作为我们的训练目标，只会出现在训练集中，不会在测试集中（要不然你测试什么？）。所以，我们先把*SalePrice*这一列给拿出来，不让它碍事儿。
# 
# 我们先看一下*SalePrice*长什么样纸：

# In[74]:


get_ipython().magic('matplotlib inline')
prices = pd.DataFrame({"price":train_df["SalePrice"], "log(price + 1)":np.log1p(train_df["SalePrice"])})
prices.hist()


# 可见，label本身并不平滑。为了我们分类器的学习更加准确，我们会首先把label给“平滑化”（正态化）
# 
# 这一步大部分同学会miss掉，导致自己的结果总是达不到一定标准。
# 
# 这里我们使用最有逼格的log1p, 也就是 log(x+1)，避免了复值的问题。
# 
# 记住哟，如果我们这里把数据都给平滑化了，那么最后算结果的时候，要记得把预测到的平滑数据给变回去。
# 
# 按照“怎么来的怎么去”原则，log1p()就需要expm1(); 同理，log()就需要exp(), ... etc.

# In[75]:


y_train = np.log1p(train_df.pop('SalePrice'))


# 然后我们把剩下的部分合并起来

# In[76]:


all_df = pd.concat((train_df, test_df), axis=0)


# 此刻，我们可以看到all_df就是我们合在一起的DF

# In[77]:


all_df.shape


# 而*y_train*则是*SalePrice*那一列

# In[78]:


y_train.head()


# ## Step 3: 变量转化
# 
# 类似『特征工程』。就是把不方便处理或者不unify的数据给统一了。
# 
# #### 正确化变量属性
# 
# 首先，我们注意到，*MSSubClass* 的值其实应该是一个category，
# 
# 但是Pandas是不会懂这些事儿的。使用DF的时候，这类数字符号会被默认记成数字。
# 
# 这种东西就很有误导性，我们需要把它变回成*string*

# In[79]:


all_df['MSSubClass'].dtypes


# In[80]:


all_df['MSSubClass'] = all_df['MSSubClass'].astype(str)


# 变成*str*以后，做个统计，就很清楚了

# In[81]:


all_df['MSSubClass'].value_counts()


# #### 把category的变量转变成numerical表达形式
# 
# 当我们用numerical来表达categorical的时候，要注意，数字本身有大小的含义，所以乱用数字会给之后的模型学习带来麻烦。于是我们可以用One-Hot的方法来表达category。
# 
# pandas自带的get_dummies方法，可以帮你一键做到One-Hot。

# In[82]:


pd.get_dummies(all_df['MSSubClass'], prefix='MSSubClass').head()


# 此刻*MSSubClass*被我们分成了12个column，每一个代表一个category。是就是1，不是就是0。

# 同理，我们把所有的category数据，都给One-Hot了

# In[83]:


all_dummy_df = pd.get_dummies(all_df)
all_dummy_df.head()


# #### 处理好numerical变量
# 
# 就算是numerical的变量，也还会有一些小问题。
# 
# 比如，有一些数据是缺失的：

# In[84]:


all_dummy_df.isnull().sum().sort_values(ascending=False).head(10)


# 可以看到，缺失最多的column是LotFrontage

# 处理这些缺失的信息，得靠好好审题。一般来说，数据集的描述里会写的很清楚，这些缺失都代表着什么。当然，如果实在没有的话，也只能靠自己的『想当然』。。
# 
# 在这里，我们用平均值来填满这些空缺。

# In[85]:


mean_cols = all_dummy_df.mean()
mean_cols.head(10)


# In[86]:


all_dummy_df = all_dummy_df.fillna(mean_cols)


# 看看是不是没有空缺了？

# In[87]:


all_dummy_df.isnull().sum().sum()


# #### 标准化numerical数据
# 
# 这一步并不是必要，但是得看你想要用的分类器是什么。一般来说，regression的分类器都比较傲娇，最好是把源数据给放在一个标准分布内。不要让数据间的差距太大。
# 
# 这里，我们当然不需要把One-Hot的那些0/1数据给标准化。我们的目标应该是那些本来就是numerical的数据：
# 
# 先来看看 哪些是numerical的：

# In[88]:


numeric_cols = all_df.columns[all_df.dtypes != 'object']
numeric_cols


# 计算标准分布：(X-X')/s
# 
# 让我们的数据点更平滑，更便于计算。
# 
# 注意：我们这里也是可以继续使用Log的，我只是给大家展示一下多种“使数据平滑”的办法。

# In[89]:


numeric_col_means = all_dummy_df.loc[:, numeric_cols].mean()
numeric_col_std = all_dummy_df.loc[:, numeric_cols].std()
all_dummy_df.loc[:, numeric_cols] = (all_dummy_df.loc[:, numeric_cols] - numeric_col_means) / numeric_col_std


# ## Step 4: 建立模型
# 
# #### 把数据集分回 训练/测试集

# In[90]:


dummy_train_df = all_dummy_df.loc[train_df.index]
dummy_test_df = all_dummy_df.loc[test_df.index]


# In[91]:


dummy_train_df.shape, dummy_test_df.shape


# In[92]:


X_train = dummy_train_df.values
X_test = dummy_test_df.values


# #### 做一点高级的Ensemble
# 
# 一般来说，单个分类器的效果真的是很有限。我们会倾向于把N多的分类器合在一起，做一个“综合分类器”以达到最好的效果。
# 
# 我们从刚刚的试验中得知，Ridge(alpha=15)给了我们最好的结果

# In[93]:


from sklearn.linear_model import Ridge
ridge = Ridge(15)


# #### Bagging
# 
# Bagging把很多的小分类器放在一起，每个train随机的一部分数据，然后把它们的最终结果综合起来（多数投票制）。
# 
# Sklearn已经直接提供了这套构架，我们直接调用就行：

# In[94]:


from sklearn.ensemble import BaggingRegressor
from sklearn.model_selection import cross_val_score


# 在这里，我们用CV结果来测试不同的分类器个数对最后结果的影响。
# 
# 注意，我们在部署Bagging的时候，要把它的函数base_estimator里填上你的小分类器（ridge）

# In[95]:


params = [1, 10, 15, 20, 25, 30, 40]
test_scores = []
for param in params:
    clf = BaggingRegressor(n_estimators=param, base_estimator=ridge)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[96]:


import matplotlib.pyplot as plt
get_ipython().magic('matplotlib inline')
plt.plot(params, test_scores)
plt.title("n_estimator vs CV Error");


# 可见，前一个版本中，ridge最优结果也就是0.135；而这里，我们使用25个小ridge分类器的bagging，达到了低于0.132的结果。

# 当然了，你如果并没有提前测试过ridge模型，你也可以用Bagging自带的DecisionTree模型：
# 
# 代码是一样的，把base_estimator给删去即可

# In[106]:


params = [10, 15, 20, 25, 30, 40, 50, 60, 70, 100]
test_scores = []
for param in params:
    clf = BaggingRegressor(n_estimators=param)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[107]:


import matplotlib.pyplot as plt
get_ipython().magic('matplotlib inline')
plt.plot(params, test_scores)
plt.title("n_estimator vs CV Error");


# 咦，看来单纯用DT不太灵光的。最好的结果也就0.140

# #### Boosting
# 
# Boosting比Bagging理论上更高级点，它也是揽来一把的分类器。但是把他们线性排列。下一个分类器把上一个分类器分类得不好的地方加上更高的权重，这样下一个分类器就能在这个部分学得更加“深刻”。

# In[97]:


from sklearn.ensemble import AdaBoostRegressor


# In[98]:


params = [10, 15, 20, 25, 30, 35, 40, 45, 50]
test_scores = []
for param in params:
    clf = BaggingRegressor(n_estimators=param, base_estimator=ridge)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[99]:


plt.plot(params, test_scores)
plt.title("n_estimator vs CV Error");


# Adaboost+Ridge在这里，25个小分类器的情况下，也是达到了接近0.132的效果。

# 同理，这里，你也可以不必输入Base_estimator，使用Adaboost自带的DT。

# In[108]:


params = [10, 15, 20, 25, 30, 35, 40, 45, 50]
test_scores = []
for param in params:
    clf = BaggingRegressor(n_estimators=param)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# In[109]:


plt.plot(params, test_scores)
plt.title("n_estimator vs CV Error");


# 看来我们也许要先tune一下我们的DT模型，再做这个实验。。:P

# #### XGBoost
# 
# 最后，我们来看看巨牛逼的XGBoost，外号：Kaggle神器
# 
# 这依旧是一款Boosting框架的模型，但是却做了很多的改进。

# In[100]:


from xgboost import XGBRegressor


# 用Sklearn自带的cross validation方法来测试模型

# In[101]:


params = [1,2,3,4,5,6]
test_scores = []
for param in params:
    clf = XGBRegressor(max_depth=param)
    test_score = np.sqrt(-cross_val_score(clf, X_train, y_train, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))


# 存下所有的CV值，看看哪个alpha值更好（也就是『调参数』）

# In[102]:


import matplotlib.pyplot as plt
get_ipython().magic('matplotlib inline')
plt.plot(params, test_scores)
plt.title("max_depth vs CV Error");


# 惊了，深度为5的时候，错误率缩小到0.127

# 这就是为什么，浮躁的竞赛圈，人人都在用XGBoost :)
