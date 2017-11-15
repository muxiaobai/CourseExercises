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
#plt.imshow(image.reshape(2,2),cmap='Greys')
#plt.savefig('./cnn-pooling1.png')
pool = tf.nn.max_pool(image,ksize =[1,2,2,1],strides =[1,1,1,1],padding ='SAME')
print("pool.shape",pool.shape)
pool_img = pool.eval()
print("pool_img.shape",pool_img.shape)
pool_img = np.swapaxes(pool_img,0,2)
for i,one_img in enumerate(pool_img):
	print("one_img",one_img.reshape(2,1))
    plt.subplot(1,2,i+1),plt.imshow(one_img.reshape(1,1),cmap='gray')
    plt.savefig('./cnn-pooling2.png')
