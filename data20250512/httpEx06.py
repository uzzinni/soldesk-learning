# 우리책 265 예제
# url : https://www.fdic.gov/bank-failures/failed-bank-list
# pandas 사용합니다.
import pandas as pd

table = pd.read_html('https://www.fdic.gov/bank-failures/failed-bank-list')
# print(len(table))
failures = table[0]
# print(failures.head())

close_timestamp = pd.to_datetime(failures['Closing Date'])
# print(close_timestamp)
print(close_timestamp.dt.year.value_counts())

