# numpy 예제 1
# pip install numpy
import numpy as np

array_1d = np.array([10, 20, 30, 40, 50])
print(array_1d)

scalar_value = 5
print(array_1d + scalar_value)
print(array_1d - scalar_value)
print(array_1d * scalar_value)
print(array_1d / scalar_value)

array_2 = np.array([1, 2, 3, 4, 5])
print(array_1d + array_2)
print(array_1d - array_2)
print(array_1d * array_2)
print(array_1d / array_2)

# 비교연산
print(array_1d > 30)
