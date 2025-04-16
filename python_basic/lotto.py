import random as r

# lotto 만들어주세요.
# 1~45 숫자 6개
# for i in range(6):
    # print(r.randrange(1, 46))

l = set()
while len(l) < 6:                        # 길이가 6 이하라면 참
    l.add(r.randrange(1, 46))   # 1~45 중 하나를 뽑아서 저장하기

li = list(l) # 리스트로 변환
li.sort()    # 정렬하기
print(li)    # 출력하기