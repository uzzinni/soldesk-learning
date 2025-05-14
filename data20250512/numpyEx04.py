import numpy as np

data_2d = np.array([
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
])

print(data_2d)
print(data_2d > 5)
indices_2d_tuple = np.where(data_2d > 5)
print(indices_2d_tuple)
# (array([1, 2, 2, 2]), array([2, 0, 1, 2])
# 행                         열
# [1,2], [2,0], [2,1], [2,2]

row = indices_2d_tuple[0]
col = indices_2d_tuple[1]
print(row)
print(col)
print('-'*30)
# 선택된 데이터 > 5
select_elements_2d = data_2d[row, col]
print(select_elements_2d)
