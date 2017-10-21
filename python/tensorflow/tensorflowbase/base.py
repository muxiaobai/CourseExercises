#!/usr/bin/python
# -*- coding: utf-8 -*- 

import tensorflow as tf
import numpy as np

# 使用numpy生成一个假数据
max1 = tf.constant([[3.,4.],[5.,6]])
max2 = tf.constant([[2.],[2.]])
#tensor   n dimension Array 一个static type  rank 一个shape
a =tf.placeholder(tf.float32)
add_node = a + max1
cons = tf.constant([[3.,4.],[5.,6.]])
mean = tf.reduce_mean(cons,1)
product = tf.matmul(max1,max2)
sess = tf.Session()     
result = sess.run(product)
print sess.run(max1)
print result
print sess.run(add_node,feed_dict={a:3})
print sess.run(mean)
sess.close()