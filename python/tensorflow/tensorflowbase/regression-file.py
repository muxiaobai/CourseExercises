#!/usr/bin/python
# -*- coding: utf-8 -*- 

import tensorflow as tf
import numpy as np
tf.set_random_seed(777)  # for reproducibility
xy = np.loadtxt('data-fileregression-score.csv', delimiter=',', dtype=np.float32)
x_data = xy[:, 0:-1]
y_data = xy[:, [-1]]

X = tf.placeholder(tf.float32,shape=[None,3])
Y = tf.placeholder(tf.float32,shape=[None,1])

# 构造一个线性模型
W = tf.Variable(tf.random_normal([3,1]),name='weight')
b = tf.Variable(tf.random_normal([1]),name ='bias')
hypothesis = tf.matmul(X, W) + b

# 最小化方差

loss = tf.reduce_mean(tf.square(hypothesis - Y))
optimizer = tf.train.GradientDescentOptimizer(0.000001)
train =optimizer.minimize(loss)

# 初始化变量
init =tf.initialize_all_variables()

# 启动图

sess = tf.Session()
sess.run(init)

# 拟合平面
#	
for step in xrange(0,10001):
	loss_val,W_val,b_val,_ = sess.run([loss,W,b,train],feed_dict= {X : x_data ,Y:y_data})
	if step % 200 == 0:
		print (step, loss_val,W_val,b_val)

# Ask my score
print("Your score will be ", sess.run( hypothesis, feed_dict={X: [[100, 70, 101]]}))

print("Other scores will be ", sess.run(hypothesis,
                                        feed_dict={X: [[60, 70, 110], [90, 100, 80]]}))
