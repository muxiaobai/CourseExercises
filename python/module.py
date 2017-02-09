#!/usr/bin/python
# -*- coding: UTF-8 -*-
import time
import calendar
print time.time()
'''

这里的时间是从1970年1月1日起经历的时间，截止到2038年

'''
local = time.localtime()
print local
strf = time. strftime("%Y-%m-%d %H:%M:%S",local)
print strf
print calendar.month(2017,2)
