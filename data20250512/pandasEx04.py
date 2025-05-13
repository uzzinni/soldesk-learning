# 2025-05-13
# 파이썬 데이터 분석
# 예제 4

import pandas as pd
data_info = {
    '직원ID' : [101, 102, 103, 104, 105],
    '이름' : ['홍길동', '김영철', '이순신', '유관순', '안중근'],
    '부서' : ['영업', '개발', '인사', '영업', '개발']
}
df_info = pd.DataFrame(data_info)
print(df_info)

data_performance = {
    '직원ID' : [101, 103, 104, 106, 107], # 106, 107이 없어요
    '평가점수' : [85, 92, 78, 88, 95],
    '프로젝트수' : [3, 4, 2, 3, 5]
}
df_performance = pd.DataFrame(data_performance)
print(df_performance)

merged_df_inner = pd.merge(df_info, df_performance, on='직원ID', how='inner')
print(merged_df_inner)

merged_df_left = pd.merge(df_info, df_performance, on='직원ID', how='left')
print(merged_df_left)

# 함수
def calculate_bonus(row):
    if row['평가점수'] >= 90 and row['프로젝트수'] > 3:
        return 100
    else:
        return 50

# 결측치 처리 후 apply 함수 사용 (Left Join 결과에 적용)
# 성과 정보가 없는 직원(NaN)의 평가점수, 프로젝트수를 0으로 임시 변경하여 계산
merged_df_left_filled = merged_df_left.fillna(0)
print(merged_df_left_filled
      )
# 결측치를 0으로 채워 계산 가능하게 함
merged_df_left_filled['보너스'] = (merged_df_left_filled.apply(calculate_bonus, axis='columns'))
print(merged_df_left_filled)