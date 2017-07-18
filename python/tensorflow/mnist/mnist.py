#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import input_data
mnist = input_data.read_data_sets("MNIST_data/" , one_hot = True)

#  
x = tf.placeholder("float" , [None , 784])

W = tf.Variable(tf.zeros([784 , 10]))
b = tf.Variable(tf.zeros([10]))
y = tf.nn.softmax(tf.matmul(x,W) + b)

#  成本函数 : 利用交叉熵来描述一个模型是坏的

y_ = tf.placeholder("float", [None , 10])

cross_entropy = -tf.reduce_sum(y_ * tf.log(y))

# 优化算法 ---梯度下降算法  gradient descent algorithm
train_step = tf.train.GradientDescentOptimizer(0.01).minimize(cross_entropy)

# 初始化变量
init = tf.initialize_all_variables()
 
sess = tf.Session()
sess.run(init)

for i in range(1000) :
    batch_xs,batch_ys = mnist.train.next_batch(100)
    sess.run(train_step, feed_dict = {x : batch_xs, y : batch_ys})