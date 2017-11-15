#!/usr/bin/python
# -*- coding: utf-8 -*- 
# liner regression 线性回归
import tensorflow as tf
import numpy as np
import matplotlib as mpl
mpl.use('Agg')
import matplotlib.pyplot as plt

# 使用numpy生成一个假数据
X = [3,6,8,9,10,14,18]
Y = [3,4,5,6,7,8,11]

# 构造一个线性模型
W = tf.Variable(tf.random_normal([1]),name='weight')
b = tf.Variable(tf.random_normal([1]),name ='bias')
hypothesis = X * W + b

# 最小化方差

loss = tf.reduce_mean(tf.square(hypothesis  - Y))
optimizer = tf.train.GradientDescentOptimizer(0.0001)
train =optimizer.minimize(loss)

# 初始化变量
init =tf.initialize_all_variables()

# 启动图

sess = tf.Session()
sess.run(init)

# 拟合平面
#	
for step in xrange(0,40001):
	sess.run(train)
	if step % 20 == 0:
		_, W_val, b_val = sess.run([train, W, b])
		#print step, sess.run(loss),sess.run(W), sess.run(b)
		print (step,W_val, b_val)
	
#draw
plt.plot(X,Y,"+")
plt.plot(X,(Y*W_val)+b_val)
plt.show()
plt.savefig('./regression-data.png')
