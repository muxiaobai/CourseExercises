#!/usr/bin/python
# -*- coding: utf-8 -*- 
#logisticClassification 分类  Logistic Regression (Binary classification)
import tensorflow as tf
import numpy as np

# 训练数据
x_data = np.array([[0, 0], [0, 1], [1, 0], [1, 1]], dtype=np.float32)
y_data = np.array([[0],    [1],    [1],    [0]], dtype=np.float32)


X = tf.placeholder(tf.float32,shape=[None,2])
Y = tf.placeholder(tf.float32,shape=[None,1])

''' 
#正常一个，不能解决异或问题
# 构造一个sigmoid模型
W = tf.Variable(tf.random_normal([2,1]),name='weight')
b = tf.Variable(tf.random_normal([1]),name ='bias')
hypothesis = tf.sigmoid(tf.matmul(X, W) + b)
'''

#'''
# 2laylers 2个y                   0.021
W1 = tf.Variable(tf.random_normal([2, 2]), name='weight1')
b1 = tf.Variable(tf.random_normal([2]), name='bias1')
layer1 = tf.sigmoid(tf.matmul(X, W1) + b1)

W2 = tf.Variable(tf.random_normal([2, 1]), name='weight2')
b2 = tf.Variable(tf.random_normal([1]), name='bias2')
hypothesis = tf.sigmoid(tf.matmul(layer1, W2) + b2)
#'''

'''
#Wide 2 laylers 10个y   矮 胖 0.007268
W1 = tf.Variable(tf.random_normal([2, 10]), name='weight1')
b1 = tf.Variable(tf.random_normal([10]), name='bias1')
layer1 = tf.sigmoid(tf.matmul(X, W1) + b1)

W2 = tf.Variable(tf.random_normal([10, 1]), name='weight2')
b2 = tf.Variable(tf.random_normal([1]), name='bias2')
hypothesis = tf.sigmoid(tf.matmul(layer1, W2) + b2)
'''

''' 
#Deep 4 laylers 2个y  高 瘦 0.014556
W1 = tf.Variable(tf.random_normal([2, 2]), name='weight1')
b1 = tf.Variable(tf.random_normal([2]), name='bias1')
layer1 = tf.sigmoid(tf.matmul(X, W1) + b1)

W2 = tf.Variable(tf.random_normal([2, 2]), name='weight2')
b2 = tf.Variable(tf.random_normal([2]), name='bias2')
layer2 = tf.sigmoid(tf.matmul(layer1, W2) + b2)

W3 = tf.Variable(tf.random_normal([2, 2]), name='weight3')
b3 = tf.Variable(tf.random_normal([2]), name='bias3')
layer3 = tf.sigmoid(tf.matmul(layer2, W3) + b3)

W4 = tf.Variable(tf.random_normal([2, 1]), name='weight4')
b4 = tf.Variable(tf.random_normal([1]), name='bias4')
hypothesis = tf.sigmoid(tf.matmul(layer3, W4) + b4)
'''

'''  
#Deep 4 laylers 10个y 高 胖 0.00191
W1 = tf.Variable(tf.random_normal([2, 10]), name='weight1')
b1 = tf.Variable(tf.random_normal([10]), name='bias1')
layer1 = tf.sigmoid(tf.matmul(X, W1) + b1)

W2 = tf.Variable(tf.random_normal([10, 10]), name='weight2')
b2 = tf.Variable(tf.random_normal([10]), name='bias2')
layer2 = tf.sigmoid(tf.matmul(layer1, W2) + b2)

W3 = tf.Variable(tf.random_normal([10, 10]), name='weight3')
b3 = tf.Variable(tf.random_normal([10]), name='bias3')
layer3 = tf.sigmoid(tf.matmul(layer2, W3) + b3)

W4 = tf.Variable(tf.random_normal([10, 1]), name='weight4')
b4 = tf.Variable(tf.random_normal([1]), name='bias4')
hypothesis = tf.sigmoid(tf.matmul(layer3, W4) + b4)
'''


# cost函数 loss
loss = -tf.reduce_mean(Y * tf.log(hypothesis)+(1-Y)* tf.log(1-hypothesis))
optimizer = tf.train.GradientDescentOptimizer(0.1)
train =optimizer.minimize(loss)

# predicted精确度 accuracy 准确度  http://blog.sina.com.cn/s/blog_4e213adc01010l2o.html
predicted = tf.cast(hypothesis >0.5,dtype =tf.float32)
accuracy = tf.reduce_mean(tf.cast(tf.equal(predicted,Y),dtype =tf.float32))

# 初始化变量
init =tf.initialize_all_variables()

# 启动图
sess = tf.Session()
sess.run(init)

# 拟合平面
for step in xrange(0,20001):
	loss_val,_ = sess.run([loss,train],feed_dict= {X:x_data,Y:y_data})
	if step % 2000 == 0:
		print (step, loss_val)

#测试结果
h, c, a =sess.run([hypothesis,predicted,accuracy],feed_dict= { X : x_data ,Y:y_data})# X : x_test ,Y:y_test
print ("hypothesis:",h,"predicted :",c,"accuracy :",a)
