#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import input_data
mnist = input_data.read_data_sets("MNIST_data/" , one_hot = True)
tf.set_random_seed(777)  # reproducibility
X = tf.placeholder(tf.float32, shape= [None , 784])
Y = tf.placeholder(tf.float32, shape= [None , 10])



W = tf.Variable(tf.random_normal([784 , 10]))
b = tf.Variable(tf.random_normal([10]))
#hypothesis = tf.nn.softmax(tf.matmul(X,W) + b) #第一个成本函数
hypothesis = tf.matmul(X,W) + b #第二个成本函数

# 成本函数
#loss = tf.reduce_mean(- tf.reduce_sum(Y * tf.log(hypothesis),1))
# 优化算法 ---梯度下降算法  gradient descent algorithm
#train = tf.train.GradientDescentOptimizer(0.1).minimize(loss)

# 成本函数 : 利用交叉熵来描述损失函数 define cost/loss & optimizer
#两种不同的最优化 Optimization
loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=hypothesis, labels=Y))
optimizer = tf.train.AdamOptimizer(learning_rate=0.001)
train =optimizer.minimize(loss)



# 初始化变量
init = tf.initialize_all_variables()
sess = tf.Session()
sess.run(init)
# 拟合平面
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
# softmax  GradientDescentOptimizer(0.1) 0.888 AdamOptimizer(0.001) 0.9028  