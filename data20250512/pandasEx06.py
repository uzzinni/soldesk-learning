# 예제 6
import pandas as pd

data = {
'직원ID': [101, 102, 103, 104, 105, 106, 107, 108],
'이름': ['홍길동', '김영철', '이순신', '유관순', '안중근', '강감찬', '세종', '장영실'],
'부서': ['영업', '개발', '인사', '영업', '개발', '기획', '인사', '개발'],
'급여': [5000, 6000, 5500, 5200, 6500, 5800, 7000, 6300],
'입사년도': [2018, 2019, 2020, 2018, 2021, 2019, 2015, 2020]
}
df_employees = pd.DataFrame(data)
print(df_employees)
print("-" * 50)

sorted_employees = df_employees.sort_values(by=['부서', '급여'], ascending=[True, False])

print("'부서' 오름차순, '급여' 내림차순 정렬 결과")
print(sorted_employees)