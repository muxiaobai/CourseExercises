#!/usr/bin/python
# -*- coding: UTF-8 -*-
import time
print time.time()

print "hello ,世界"
#===========运算符======================
'''
+ - * / ** //整除
== != <> < > >= <=
and or not 是或非
in not in 成员
is
'''

#============Python数据类型==========================#
'''
以下为
多行注释
Numbers（数字） String（字符串） List（列表） Tuple（元组） Dictionary（字典）
列表有序，字典无序
'''
list1 = ["asaa","asassfs",13]
list2 = [1,34,3]
list2 = list2 + list1
listAll = list1 + list2
print list1
print listAll
print listAll[0]
print listAll[3:5]
listAll[0] = "update"
print listAll
'''
Tuple 是只读列表不能修改
例如 上例中的对listAll第一个元素赋值
'''
tuple1 = ("muxiaobai","github","io")
print tuple1

dict1 = {"k":"v","e":"a","y":"l"}
print dict1
print dict1.keys()
print dict1.values()
print dict1["k"]
# 数据类型转换


if True:
  print "True"
else:
  print "no "
i = 0
while (i<len(list1)) :
  print list1[i]
  i = i + 1
for one in list1 :
  print one
#  break continue

'''
def 定义函数
'''
def myfun ( str ) :
  print str
  return
myfun("zhang")
print "调用函数循环输出:"
def iterList (list) :
  for ite in list :
    print ite
iterList ( list1 )