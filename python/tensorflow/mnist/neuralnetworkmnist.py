#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import input_data
mnist = input_data.read_data_sets("MNIST_data/" , one_hot = True)
batch_xs,batch_ys = mnist.train.next_batch(100)
#  
X = tf.placeholder(tf.float32, shape= [None , 784])
Y = tf.placeholder(tf.float32, shape= [None , 10])
'''
W = tf.Variable(tf.zeros([784 , 10]))
b = tf.Variable(tf.zeros([10]))
hypothesis = tf.nn.softmax(tf.matmul(X,W) + b)
'''
# weights & bias for nn layers
# 两层

W1 = tf.Variable(tf.random_normal([784, 256]))
b1 = tf.Variable(tf.random_normal([256]))
L1 = tf.nn.relu(tf.matmul(X, W1) + b1)

W2 = tf.Variable(tf.random_normal([256, 256]))
b2 = tf.Variable(tf.random_normal([256]))
L2 = tf.nn.relu(tf.matmul(L1, W2) + b2)

W3 = tf.Variable(tf.random_normal([256, 10]))
b3 = tf.Variable(tf.random_normal([10]))
hypothesis = tf.matmul(L2, W3) + b3



# 成本函数 : 利用交叉熵来描述损失函数
#cross_entropy = -tf.reduce_sum(hypothesis * tf.log(Y))
# define cost/loss & optimizer
loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=hypothesis, labels=Y))
optimizer = tf.train.AdamOptimizer(learning_rate=0.1)
train =optimizer.minimize(loss)
#loss = tf.reduce_mean(- tf.reduce_sum(Y * tf.log(hypothesis),1))
#train_step = tf.train.GradientDescentOptimizer(0.01).minimize(loss)

# 初始化变量
init = tf.initialize_all_variables()

sess = tf.Session()
sess.run(init)
# 拟合平面
for step in xrange(0,10001):
    loss_val,_  = sess.run([loss,train], feed_dict = {X : batch_xs, Y : batch_ys})
    if step % 200 == 0:
        print (step, loss_val)
# 评估模型
correct_prediction = tf.equal(tf.argmax(hypothesis,1), tf.argmax(Y,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))
print ("Accuracy: ", accuracy.eval(session=sess, feed_dict={X: mnist.test.images, Y: mnist.test.labels}))
