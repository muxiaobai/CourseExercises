#!/usr/bin/python
# -*- coding: utf-8 -*- 
# 判定这个是哪一个对象 多个标签[0,0,0,0,1]等softmax sigmoid是一个标签
import tensorflow as tf
import numpy as np
tf.set_random_seed(777)  # for reproducibility
x_data = [[1, 2, 1, 1], [2, 1, 3, 2], [3, 1, 3, 4], [4, 1, 5, 5], [1, 7, 5, 5],[1, 2, 5, 6], [1, 6, 6, 6], [1, 7, 7, 7]]
y_data = [[0, 0, 1], [0, 0, 1], [0, 0, 1], [0, 1, 0], [0, 1, 0], [0, 1, 0], [1, 0, 0], [1, 0, 0]]


X = tf.placeholder(tf.float32,shape=[None,4])
Y = tf.placeholder(tf.float32,shape=[None,3])

# 构造一个sigmoid模型
W = tf.Variable(tf.random_normal([4,3]),name='weight')
b = tf.Variable(tf.random_normal([3]),name ='bias')

hypothesis = tf.nn.softmax(tf.matmul(X, W)+b)


# cost函数 loss
loss = tf.reduce_mean(- tf.reduce_sum(Y * tf.log(hypothesis),1))

#cost = tf.reduce_mean(-tf.reduce_sum(Y * tf.log(hypothesis), axis=1))

optimizer = tf.train.GradientDescentOptimizer(0.1)
train =optimizer.minimize(loss)

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
a = sess.run(hypothesis, feed_dict={X: [[1, 11, 7, 9]]})
print(a, sess.run(tf.arg_max(a, 1)))
arr = sess.run(hypothesis, feed_dict={X: [[1, 11, 7, 9],[1, 3, 4, 3],[1, 1, 0, 1],[1, 2, 1, 1]]})
print(arr, sess.run(tf.arg_max(arr, 1)))
