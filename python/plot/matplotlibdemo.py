#!/usr/bin/python
# -*- coding: utf-8 -*- 
import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt  

plt.bar(left = 0,height = 1)
plt.show()
plt.savefig('./img.jpg')