import pandas as pd # 판다스 설치

# 연습용 데이터
data = { '이름' : ['김철수','박영희','이지훈','최서연','강민준'],
         '나이' : [20, 22, 21, 20, 23],
         '학과' : ['컴퓨터공학과', '경영학과', '전자공학과', '디자인', '컴퓨터공학'],
         '학점' : [3.8, 4.0, 3.5, 3.9, 3.7]

}
print(type(data))
print(type(data.get('학점')))
df = pd.DataFrame(data)
print(type(df))
# 출력
print(df)
print(df[['이름', '학점', '학과']])
print('=======================')
print(df[df['나이'] > 21])

# mean() ::: 데이터 평균값 계산
print(df['나이'].mean())
#학점 평균
print(df['학점'].mean())   # 3.7800000000000002
print(df[df['학점'] > df['학점'].mean()])
avg = df['학점'].mean()
print(df[df['학점'] > avg])

print(df['학점'].max())
print(df['학점'].min())

print(df.info())

# print(df.rank(ascending=bool, method=''))
print(df.head())
print(df.tail())

# 학점 순위
df['순위'] = df['학점'].rank(method='min', ascending=False)
print(df)
# 컬럼 추가
df['거주지'] = '서울'
print(df)

#순위 정렬
print(df.sort_values(by='나이', ascending=False)) # 나이 내림차순 정렬
print(df.sort_values(by='나이', ascending=True)) # 나이 올림차순 정렬
print(df.sort_values(by='순위'))

print('=========================')
# index 사용하기
indexEx = df.set_index('이름')
print(df)
print(indexEx)
df.reset_index()

