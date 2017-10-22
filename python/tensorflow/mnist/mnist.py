#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import input_data
mnist = input_data.read_data_sets("MNIST_data/" , one_hot = True)
#batch_xs,batch_ys = mnist.train.next_batch(100)
#  
X = tf.placeholder(tf.float32, shape= [None , 784])
Y = tf.placeholder(tf.float32, shape= [None , 10])

W = tf.Variable(tf.random_normal([784 , 10]))
b = tf.Variable(tf.random_normal([10]))
hypothesis = tf.nn.softmax(tf.matmul(X,W) + b)

# 成本函数
loss = tf.reduce_mean(- tf.reduce_sum(Y * tf.log(hypothesis),1))
# 优化算法 ---梯度下降算法  gradient descent algorithm
train = tf.train.GradientDescentOptimizer(0.1).minimize(loss)

# 初始化变量
init = tf.initialize_all_variables()
 
sess = tf.Session()
sess.run(init)
# 拟合平面
# for step in xrange(0,1001):
#     loss_val,_  = sess.run([loss,train], feed_dict = {X : batch_xs, Y : batch_ys})
#     if step % 200 == 0:
#         print (step, loss_val)
# parameters
training_epochs = 15
batch_size = 100

for epoch in range(training_epochs):
    avg_cost = 0
    total_batch = int(mnist.train.num_examples / batch_size)

    for i in range(total_batch):
        batch_xs, batch_ys = mnist.train.next_batch(batch_size)
        c, _ = sess.run([loss, train], feed_dict={
                        X: batch_xs, Y: batch_ys})
        avg_cost += c / total_batch

    print('Epoch:', '%04d' % (epoch + 1),
          'loss =', '{:.9f}'.format(avg_cost))        

# 评估模型
correct_prediction = tf.equal(tf.argmax(hypothesis,1), tf.argmax(Y,1))
accuracy = tf.reduce_mean(tf.cast(correct_prediction, "float"))
print ("Accuracy: ", accuracy.eval(session=sess, feed_dict={X: mnist.test.images, Y: mnist.test.labels}))
# 0.88