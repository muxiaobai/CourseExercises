#!/usr/bin/python
# -*- coding: utf-8 -*- 

import tensorflow as tf
import numpy as np

# 使用numpy生成一个假数据
max1 = tf.constant([[3.,3.]])
max2 = tf.constant([[2.],[2.]])
#tensor   n dimension Array 一个static type  rank 一个shape
product = tf.matmul(max1,max2)
sess = tf.Session()
result = sess.run(product)
print result
sess.close()