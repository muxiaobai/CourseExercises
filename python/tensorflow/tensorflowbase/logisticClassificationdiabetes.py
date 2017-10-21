#!/usr/bin/python
# -*- coding: utf-8 -*- 

import tensorflow as tf
import numpy as np

# 训练数据
x_data = [[1, 2], [2, 3], [3, 1], [4, 3], [5, 3], [6, 2]]
y_data = [[0], [0], [0], [1], [1], [1]]

x_test = [[6,2]]
y_test = [[1]]

X = tf.placeholder(tf.float32,shape=[None,2])
Y = tf.placeholder(tf.float32,shape=[None,1])

# 构造一个sigmoid模型
W = tf.Variable(tf.random_normal([2,1]),name='weight')
b = tf.Variable(tf.random_normal([1]),name ='bias')
hypothesis = tf.sigmoid(tf.matmul(X, W) + b)


# cost函数 loss

loss = -tf.reduce_mean(Y * tf.log(hypothesis)+(1-Y)* tf.log(1-hypothesis))
optimizer = tf.train.GradientDescentOptimizer(0.01)
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
for step in xrange(0,10001):
	loss_val,_ = sess.run([loss,train],feed_dict= {X:x_data,Y:y_data})
	if step % 2000 == 0:
		print (step, loss_val)

#测试结果
h, c, a =sess.run([hypothesis,predicted,accuracy],feed_dict= { X : x_test ,Y:y_test})# X : x_test ,Y:y_test
print ("hypothesis:",h,"predicted :",c,"accuracy :",a)
#hypothesis计算的结果 predicted精确度 和0.5 比较  accuracy 准确度
# 如果学习率为0.001 6中5 predicted 是预测结果 accuracy 为5/6  0.833
# 如果学习率为0.01  6中6 predicted 是预测结果 accuracy 为1
