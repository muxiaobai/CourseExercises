#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import numpy as np
import matplotlib as mpl
mpl.use('Agg')
import matplotlib.pyplot as plt
import input_data
mnist = input_data.read_data_sets("MNIST_data/" , one_hot = True)
tf.set_random_seed(777)  # reproducibility

img = mnist.train.images[0].reshape[28,28]
plt.imshow(img,cmap='gray')
plt.savefig('./cnn-mnistimg.png')

