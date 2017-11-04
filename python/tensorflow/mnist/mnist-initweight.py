#!/usr/bin/python
# -*- coding:utf-8 -*-
import tensorflow as tf
import input_data
mnist = input_data.read_data_sets("MNIST_data/" , one_hot = True)
tf.set_random_seed(777)  # reproducibility
X = tf.placeholder(tf.float32, shape= [None , 784])
Y = tf.placeholder(tf.float32, shape= [None , 10])


'''
W = tf.Variable(tf.random_normal([784 , 10]))
b = tf.Variable(tf.random_normal([10]))
hypothesis = tf.nn.softmax(tf.matmul(X,W) + b)
'''

#''' 
# weights & bias for nn layers  
W1 = tf.get_variable("W1", shape=[784, 256],initializer=tf.contrib.layers.xavier_initializer())
#W1 = tf.Variable(tf.random_normal([784, 256]))
b1 = tf.Variable(tf.random_normal([256]))
L1 = tf.nn.relu(tf.matmul(X, W1) + b1)

W2 = tf.get_variable("W2", shape=[256, 256],initializer=tf.contrib.layers.xavier_initializer())
#W2 = tf.Variable(tf.random_normal([256, 256]))
b2 = tf.Variable(tf.random_normal([256]))
L2 = tf.nn.relu(tf.matmul(L1, W2) + b2)

#W3 = tf.Variable(tf.random_normal([256, 10]))
W3 = tf.get_variable("W3", shape=[256, 10],initializer=tf.contrib.layers.xavier_initializer())
b3 = tf.Variable(tf.random_normal([10]))
hypothesis = tf.matmul(L2, W3) + b3
#'''

# 成本函数 : 利用交叉熵来描述损失函数 define cost/loss & optimizer
loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=hypothesis, labels=Y))
optimizer = tf.train.AdamOptimizer(learning_rate=0.001)
train =optimizer.minimize(loss)

# 初始化变量
init = tf.global_variables_initializer()#tf.initialize_all_variables()

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
accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
print ("Accuracy: ", accuracy.eval(session=sess, feed_dict={X: mnist.test.images, Y: mnist.test.labels}))
# softmax   GradientDescentOptimizer 0.888 AdamOptimizer 0.9028  nn-laylers relu 0.9469



