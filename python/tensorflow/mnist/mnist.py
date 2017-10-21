#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import input_data
mnist = input_data.read_data_sets("MNIST_data/" , one_hot = True)

#  
X = tf.placeholder(tf.float32, shape= [None , 784])
Y = tf.placeholder(tf.float32, shape= [None , 10])

W = tf.Variable(tf.zeros([784 , 10]))
b = tf.Variable(tf.zeros([10]))
hypothesis = tf.nn.softmax(tf.matmul(X,W) + b)

#  成本函数 : 利用交叉熵来描述一个模型是坏的

cross_entropy = -tf.reduce_sum(hypothesis * tf.log(Y))

# 优化算法 ---梯度下降算法  gradient descent algorithm
train_step = tf.train.GradientDescentOptimizer(0.01).minimize(cross_entropy)

# 初始化变量
init = tf.initialize_all_variables()
 
sess = tf.Session()
sess.run(init)

for i in range(1000) :
    batch_xs,batch_ys = mnist.train.next_batch(100)
    sess.run(train_step, feed_dict = {X : batch_xs, Y : batch_ys})

# 评估模型
correct_prediction = tf.equal(tf.argmax(hypothesis,1), tf.argmax(Y,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))
print accuracy.eval(feed_dict={X: mnist.test.images, Y: mnist.test.labels})