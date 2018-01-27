#!/usr/bin/python
# -*- coding: UTF-8 -*-
from datetime import datetime
now = datetime.now()
print '%s-%s-%s %s:%s:%s' % (now.year,now.month, now.day,  now.hour, now.minute, now.second)

# len() upper() lower()  str() isalpha() [1:2]字符串截取
input = raw_input("input your word:").upper() 
print str(len(input)) + ": " + input.upper() +input.lower()+ ":" + str(input.isalpha())

