#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import numpy as np
import matplotlib as mpl
mpl.use('Agg')
import matplotlib.pyplot as plt

sess = tf.InteractiveSession()
image =np.array([[[[4],[3]],[[2],[1]]]],dtype=np.float32)
print("image.shape",image.shape)
plt.imshow(image.reshape(3,3),cmap='Greys')
plt.savefig('./cnn-pooling1.png')
pool = tf.nn.max_pool(image,ksize =[1,2,2,1],strides =[1,1,1,1],padding ='same')
print("pool.shape",pool.shape)
print (pool.eval())
plt.imshow(pool.reshape(3,3),cmap='Greys')
plt.savefig('./cnn-pooling2.png')
