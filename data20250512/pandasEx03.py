# 예제 3
import pandas as pd
import io
'''
문자열 :: 주석으로 :: 임시
'''

csv_data='''주문번호,제품,수량,가격,주문일자
1001,노트북,1,1200000,2025-05-10
1002,마우스,5,15000,2025-05-10
1003,키보드,2,50000,2025-05-11
1004,모니터,1,300000,2025-05-11
1005,노트북,2,1200000,2025-05-12
1006,마우스,3,15000,2025-05-12
1007,키보드,1,50000,2025-05-13
'''
csv_file_like_object = io.StringIO(csv_data)
df_orders = pd.read_csv(csv_file_like_object)
print(df_orders)
print(df_orders.describe())
'''
        주문번호        수량            가격
count     7.000000  7.000000  7.000000e+00 비어있지 않은 값
mean   1004.000000  2.142857  4.042857e+05 평균
std       2.160247  1.463850  5.524674e+05 표준편차
min    1001.000000  1.000000  1.500000e+04 최소
25%    1002.500000  1.000000  3.250000e+04 
50%    1004.000000  2.000000  5.000000e+04 중앙값
75%    1005.500000  2.500000  7.500000e+05
max    1007.000000  5.000000  1.200000e+06 최대값

'''

print(df_orders.describe(include='all'))

# 수량이 2개 이상인 주문은?
count_orders = df_orders[df_orders['수량'] >= 2]
print(count_orders)

count_orders = df_orders[df_orders['제품'] =='노트북']
print(count_orders)
print("-----------------------------")
df_orders['총금액'] = df_orders['수량'] * df_orders['가격']
print(df_orders)

# 파일 출력하기
#output_csv_file_like_object = io.StringIO()
#df_orders.to_csv(output_csv_file_like_object, index=False)
df_orders.to_csv('out.csv', index=False)