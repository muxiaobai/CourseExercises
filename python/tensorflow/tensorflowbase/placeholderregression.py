#!/usr/bin/python
# -*- coding: utf-8 -*- 

import tensorflow as tf
import numpy as np

# 使用numpy生成一个假数据

x_data = [3,9,14,18]  
y_data = [3,6,8,11]


X = tf.placeholder(tf.float32,shape=[None])
Y = tf.placeholder(tf.float32,shape=[None])

# 构造一个线性模型
W = tf.Variable(tf.random_normal([1]),name='weight')
b = tf.Variable(tf.random_normal([1]),name ='bias')
hypothesis = X * W + b

# 最小化方差

loss = tf.reduce_mean(tf.square(hypothesis  - Y))
optimizer = tf.train.GradientDescentOptimizer(0.001)
train =optimizer.minimize(loss)

# 初始化变量
init =tf.initialize_all_variables()

# 启动图

sess = tf.Session()
sess.run(init)

# 拟合平面
#	
for step in xrange(0,20001):
	loss_val,W_val,b_val,_ = sess.run([loss,W,b,train],feed_dict= {X : x_data ,Y:y_data})
	if step % 20 == 0:
		print (step, loss_val,W_val, b_val)
