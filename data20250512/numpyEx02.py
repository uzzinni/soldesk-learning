import pandas as pd
import numpy as np

data = {
    '고객ID': [101, 102, 103, 104, 105, 106, 107, 108],
    '성별': ['남성', '여성', '남성', '여성', '남성', '여성', '남성', np.nan],  # 누락
    '나이': [25, 30, 35, 28, 40, np.nan, 32, 26], # 한 명의 나이 정보가 누락됨
    '구매금액': [150000, 250000, 80000, 300000, 120000, 180000, 90000, 220000],
    '가입일': ['2023-01-15', '2022-11-20', '2024-02-10', '2023-05-01', '2022-08-30', '2024-01-05', '2023-07-18', '2024-03-25'],
    '탈퇴여부': ['N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N'] # 한 명이 탈퇴함
}
df = pd.DataFrame(data)

print("초기 DataFrame")
print(df)

print("DataFrame 결측치")
df.info()

# 5. 각 컬럼별 결측치 개수 확인
print("컬럼별 결측치 개수")
print(df.isnull().sum())
df_dropna_age = df.dropna(subset=['나이']).copy()
print("나이 결측치 행 제거 후 DataFrame (df_dropna_age)")
print(df_dropna_age)

df['성별'] = df['성별'].fillna('기타')
print("원본 df에 '성별' 결측치를 '기타'로 채운 후 DataFrame")
print(df)

df_dropped_column = df.drop('탈퇴여부', axis=1) # axis=1은 컬럼을 삭제
print("탈퇴여부 컬럼 삭제 후 DataFrame")
print(df_dropped_column)

df['적립예정포인트'] = df['구매금액'] * 0.1
print(df)

print("구매금액 컬럼 통계 요약")
print(df['구매금액'].describe())