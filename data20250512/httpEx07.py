# 우리책 267 예제
# url : https://github.com/Aran00/pandaExamples/blob/master/Performance_MNR.xml
from lxml import objectify
import pandas as pd
path = 'Performance_MNR.xml'

perf2 = pd.read_xml(path)
print(perf2.head())