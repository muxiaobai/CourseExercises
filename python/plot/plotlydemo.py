#!/usr/bin/python
# -*- coding: utf-8 -*- 

import plotly.plotly as py
from plotly.graph_objs import *

trace0 = Scatter(
    x=[3, 4, 9, 14,18,21],
    y=[3, 10, 6, 8,11,7]
)

data = Data([trace0])

py.plot(data, filename = 'basic-line')
py = plotly.plotly('muxiaobai','syMSf9RheDfvCWLV63A6')
