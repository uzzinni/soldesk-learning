# 예제 3
# 파이썬 데이터 분석
# 2025-05-14
import numpy as np

zeros_array = np.zeros()
print(zeros_array)

ones_array = np.ones((2, 3))
print(ones_array)

full_array = np.full((2, 3), 7)
print(full_array)

empty_array = np.empty((2, 3))
print(empty_array)

# np.arange()
# np.linspace()
arange_array = np.arange(1, 11, 2)
print(arange_array)

linspace_array = np.linspace(1, 11, 3)
print(linspace_array)

# 형태 변경
ori_array = np.arange(12)
print(ori_array)

reshaped_2d_array = ori_array.reshape(3, 4)
print(reshaped_2d_array)

reshaped_2d_array2 = ori_array.reshape(4, 3)
print(reshaped_2d_array2)
print('-'*30)

reshaped_2d_array3 = ori_array.reshape(2, 2, 3)
print(reshaped_2d_array3)



