
#描述数据
'''
print (melbourne_data.describe())
print (melbourne_data.columns)
print (melbourne_price_data.head())
print (melbourne_data.isnull().sum())
print (X.head())
'''

#怎样选取列
'''
不包含object
X = X.select_dtypes(exclude=['object'])
SalePrice列有空的数据丢掉
melbourne_data.dropna(axis=0, subset=['SalePrice'], inplace=True)
只要有列为空的数据，改列就丢掉
melbourne_data.dropna(axis=1)

有空数据的列
cols_with_missing = [col for col in melbourne_data.columns 
                                 if melbourne_data[col].isnull().any()] 
数据种类小于10而且是object类型的列
low_cardinality_cols = [cname for cname in X.columns if 
                                X[cname].nunique() < 10 and
                                X[cname].dtype == "object"]
数据是数字的列
numeric_cols = [cname for cname in X.columns if 
                                X[cname].dtype in ['int64', 'float64']]
my_cols = low_cardinality_cols + numeric_cols
train_predictors = X[my_cols]

'''

#处理数据 缺失值和一键hot类别
'''
from sklearn.preprocessing import Imputer

# handle data ,Processing data and comparison of raw data

def score_dataset(X_train, X_test, y_train, y_test):
    model = RandomForestRegressor()
    model.fit(X_train, y_train)
    preds = model.predict(X_test)
    return mean_absolute_error(y_test, preds)

# A Drop Columns with Missing Values
#data_without_missing_values = melbourne_data.dropna(axis=1)

cols_with_missing = [c for c in X_train.columns if X_train[c].isnull().any()]
reduced_X_train = X_train.drop(cols_with_missing, axis=1)
reduced_X_test  = X_test.drop(cols_with_missing, axis=1)
print("Mean Absolute Error from dropping columns with Missing Values:")
print(score_dataset(reduced_X_train, reduced_X_test, y_train, y_test))

# B  Imputation
my_imputer = Imputer()
imputed_X_train = my_imputer.fit_transform(X_train)
imputed_X_test = my_imputer.transform(X_test)
print("Mean Absolute Error from Imputation:")
print(score_dataset(imputed_X_train, imputed_X_test, y_train, y_test))

# C  An Extension To Imputation
imputed_X_train_plus = X_train.copy()
imputed_X_test_plus = X_test.copy()
cols_with_missing = (col for col in X_train.columns 
                                 if X_train[col].isnull().any())
for col in cols_with_missing:
    imputed_X_train_plus[col + '_was_missing'] = imputed_X_train_plus[col].isnull()
    imputed_X_test_plus[col + '_was_missing'] = imputed_X_test_plus[col].isnull()

# Imputation
my_imputer = Imputer()
imputed_X_train_plus = my_imputer.fit_transform(imputed_X_train_plus)
imputed_X_test_plus = my_imputer.transform(imputed_X_test_plus)

print("Mean Absolute Error from Imputation while Track What Was Imputed:")
print(score_dataset(imputed_X_train_plus, imputed_X_test_plus, y_train, y_test))

# D One-Hot Categories

def get_mae(X_mea, y_mea):
    # multiple by -1 to make positive MAE score instead of neg value returned as sklearn convention   
	return -1 * cross_val_score(RandomForestRegressor(50), X_mea, y_mea, scoring = 'neg_mean_absolute_error').mean()


predictors_without_categoricals = X_train.select_dtypes(exclude=['object'])
mae_without_categoricals = get_mae(predictors_without_categoricals, y_train)
print('Mean Absolute Error when Dropping Categoricals: ' + str(int(mae_without_categoricals)))

#get_dummies一键hot

one_hot_encoded_training_predictors = pd.get_dummies(X_train)
mae_one_hot_encoded = get_mae(one_hot_encoded_training_predictors, y_train)
print('Mean Abslute Error with One-Hot Encoding: ' + str(int(mae_one_hot_encoded)))
'''

#拆分数据
# split data into train and validation
# how to know test_size and random_state?
'''
from sklearn.model_selection import train_test_split
X_train,X_test,train_y,val_y = train_test_split(X,y,test_size=0.25,random_state = 0)
'''

#调参画图
'''
import matplotlib.pyplot as plt

params = [1,2,3,4,5,6,7,8,10]
test_scores = []
for param in params:
    clf = XGBRegressor(max_depth=param)
    test_score = np.sqrt(-cross_val_score(clf, train_x, train_y, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))
print test_scores
plt.plot(params, test_scores)
plt.title("max_depth vs CV Error");
# 一定要加上这句才能让画好的图显示在屏幕上
plt.show()
# 将当前figure的图保存到文件result.png
#plt.savefig('./xgboostparams.png')


params = [100,200,300,400,500]
test_scores = []
for param in params:
    clf = XGBRegressor(n_estimators=param)
    test_score = np.sqrt(-cross_val_score(clf, train_X, train_y, cv=10, scoring='neg_mean_squared_error'))
    test_scores.append(np.mean(test_score))
plt.plot(params, test_scores)
plt.title("n_estimator vs CV Error" + str(params));
# 一定要加上这句才能让画好的图显示在屏幕上
plt.show()

'''

#训练之后，保存模型
'''
from sklearn.externals import joblib

#save model
joblib.dump(melbourne_model,'model.pickle')
#load model
model = joblib.load('model.pickle')
'''

#预测保存数据
'''
# predict and save output
predict_cons = ['date','day_of_week']
#print ("The predictions are")
predicted_test_prices = gbdt.predict(test[predict_cons])
int_cnt = np.around(predicted_test_prices)


my_submission = pd.DataFrame({'date':test.date,'cnt':int_cnt.astype(int)})
my_submission.to_csv('submission20180223.csv',index = False,header = False,columns = ['date','cnt'])
my_submission.to_csv('result20180223.txt',index=False,header=False,columns = ['date','cnt'],sep='\t')
'''