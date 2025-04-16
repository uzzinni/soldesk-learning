
import random

# 0.527163189598625
# 0.43107196654090296
print(random.random())

print(random.randrange(1, 11)) # 1~10

com = random.randrange(1, 10) # 1 ~ 9
# print(com)
while True:
    user = int(input("1~9까지 숫자 중에 하나만 입력하세요"))
    if com > user:
        print('더 큰 숫자입니다')
    elif com < user:
        print('더 작은 숫자입니다')
    else:
        print('정답입니다.')
        break




