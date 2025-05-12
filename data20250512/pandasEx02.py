# 예제 2
import pandas as pd

data = {
    '제품' : ['노트북','마우스','노트북','키보드','마우스','노트북','키보드','노트북','마우스'],
    '지역' : ['서울','부산','서울','인천','부산','광주','대전','서울','제주'],
    '판매액' : [10000,15000,12000,8000,16000,11000,9000,13000,14000],
    '수량' : [10,5,12,4,6,11,5,13,7]
}
df_sales = pd.DataFrame(data)
print(df_sales)

# 지역별 판매금액
groupby_sales = df_sales.groupby('지역')['판매액'].sum()
print(groupby_sales)

# 제품별로 판매 금액
groupby_sales2 = df_sales.groupby('제품')['판매액'].sum()
print(groupby_sales2)

# 제품별로 판매 대수?
groupby_sales3 = df_sales.groupby('제품')['수량'].sum()
print(groupby_sales3)
print('------------------------------')

groupby_sales4 = df_sales.groupby(['지역', '제품'])[['판매액','수량']].mean()
print(groupby_sales4)
