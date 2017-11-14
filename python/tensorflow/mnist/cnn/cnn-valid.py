#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import numpy as np
import matplotlib as mpl
mpl.use('Agg')
import matplotlib.pyplot as plt

sess = tf.InteractiveSession()
image =np.array([[[[1],[2],[3]],[[4],[5],[6]],[[7],[8],[9]]]],dtype=np.float32)
#print(image.shape)
#plt.imshow(image.reshape(3,3),cmap='Greys')
#plt.savefig('./image.png')
print("image.shape",image.shape)
weight = tf.constant([[[[1.]],[[1.]]],[[[1.]],[[1.]]]])
print("weight.shape",weight.shape)
conv2d = tf.nn.conv2d(image,weight,strides=[1,1,1,1],padding ='valid')
conv2d_img = conv2d.eval()
print("conv2d_img.shape",conv2d_img.shape)
conv2d_img = np.swapaxes(conv2d_img,0,3)
for i,one_img in enumerable(conv2d_img):
    print(one_img.reshape(3,3))
    plt.subplot(1,2,i+1),plt.imshow(one_img.reshape(3,3),cmap='gray')
    plt.savefig('./valid.png')