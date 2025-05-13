# 예제 5
import pandas as pd

data = {
    '이름': ['홍길동', '이영희', '박찬호', '김철수', '이영희', '박찬호', '김철수', '이영희'],
    '과목': ['수학', '수학', '수학', '영어', '영어', '영어', '과학', '과학'],
    '점수': [85, 92, 78, 90, 88, 75, 80, 95],
    '학년': [1, 1, 1, 2, 2, 2, 1, 2]
}

df = pd.DataFrame(data)

print("--- DataFrame 처음 5개 행 ---")
print(df.head())

# 5. DataFrame의 기본 정보 확인 (컬럼, 데이터 타입, 결측치 등)
print("--- DataFrame 정보 ---")
df.info()

print("--- '이름'과 '점수' 컬럼만 선택 ---")
print(df[['이름', '점수']])

print("--- '수학' 과목 데이터만 필터링 ---")
print(df[df['과목'] == '수학'])

df['합격여부'] = df['점수'].apply(lambda x: '합격' if x >= 80 else '불합격')
print("--- '합격여부' 컬럼 추가 후 DataFrame ---")
print(df)

print("--- 학년별 평균 점수 ---")
average_score_by_grade = df.groupby('학년')['점수'].mean()
print(average_score_by_grade)

print("--- 학년별, 과목별 평균 점수 ---")
average_score_by_grade_subject = df.groupby(['학년', '과목'])['점수'].mean()
print(average_score_by_grade_subject)

print("--- 점수 기준 내림차순 정렬 ---")
print(df.sort_values(by='점수', ascending=False))