# 2025-04-16 파이썬 기초

# while
# dic
# class
# function

# dic
# list []
# tuple ()
# dict {}

a = {} # 'dict'
print(type(a))

a = {"age":20, "name":"홍길동"}
print(a)
# 출력
print(a["age"])
print(a['name'])
# 입력
a['school'] = '서당'
print(a)
a['hobby'] = ['도술','축지법','술먹기']
print(a)

print(type(a['hobby'])) # <class 'list'>
# 취미에 잠자기를 추가해주세요.
(a['hobby']).insert(0,"잠자기")
(a['hobby']).append("잠자기")
print(a)

# 삭제 del a[key]
del a['school']
print(a)

a['name'] = "김길동"
print(a)

#keys
aKey = a.keys()
print(type(aKey))
print(aKey)

for i in a.keys():
    print(i, end=" : ")
    print(a[i])   # age : 20

print(a.values())

print(a.items())

# clear
# a.clear()
print(a)

# 값 하나 출력 a[key], a.get(key)
print(a['age'])
print(a.get('age'))

print('없는 키')
#print(a['age1']) # 키가 없으면 에러
print(a.get('age1')) # 키가 없으면 None


print(a.get('age1', '값이 없어요'))

# key가 있는지 검사
# key in 집합   ::: True / False
if 'age1' in a:
    print("age1이라는 키가 있습니다.")
else:
    print("age1이라는 키가 없습니다.")


# list[dict]

l =  []

for i in range(1, 11):
    d = {'bno': i, 'btitle': str(i) + '글제목', 'bdate': '2025-04-16',
         'writer': '홍길동', 'blike': 1*i}
    l.append(d)

print(l)

# 게시판처럼 출력
print('-' * 55)
print(f'{"번호":^3} {"제목":^20} {"날짜":^15} {"글쓴이":^5} {"좋아요":^3}')
print('-' * 55)
for i in l:
    print(f"{i.get('bno'):3} {i.get('btitle'):20}"
          f" {i.get('bdate'):^15} {i.get('writer'):^5} {i.get('blike'):3}")
print('-' * 55)











# 번외 : for문 역순출력 가능?
for i in range(10, 0, -1):
    print(i)

print("다른 방법은")

for i in reversed(range(1, 11)):
    print(i)

print("============================================")

# set  순서 없어요. 중복 없어요.

s = set()
print(type(s)) # <class 'set'>

s = {1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1}  # (리스트)
s = {'A', 'A', 'B'}  # {'A', 'B'}
# s = set("Hello")           # {'H', 'e', 'o', 'l'}
# {값,값}이렇게 저장하면 set으로 봅니다.
d = {1:2, 3:4}
print(type(s))

s1 = set()
s1 = set([1,2,3])

for i in s:
    print(i)

# list로 변환
list1 = list(s)
print(list1)  # ['B', 'A']
print(list1[0])

tuple1 = tuple(s)
print(tuple1)  # ('B', 'A')



s1 = {1, 2, 3, 4, 5, 6}
s2 = {2, 4, 6, 8, 10}
#  교집합 구하기
print(s1 & s2)
print(s1.intersection(s2))

# 합
print(s1 | s2)
print(s1.union(s2)) # {1, 2, 3, 4, 5, 6, 8, 10}

# 차집합
print(s1 - s2)
print(s1.difference(s2)) # {1, 3, 5}
print(s2.difference(s1)) # {8, 10}


s1.add(11)
print(s1)

s1.update([12, 13, 14])
print(s1)

s1.remove(1)
# s1.remove(1) # 없는 값은 에러

for i in s1.intersection(s2):
    s1.remove(i)

print(s1)
# 전체 삭제 clear
# s1.clear() 전체 삭제
# len(s1) 길이 뽑기


# while
count = 0
while count < 10:
    print("count가 10보다 작아요 : %d" % count)
    count += 1



print("숫자를 입력하세요")
user = int(input())
while user > 0:
    print("0이 될때까지 반복합니다.")
    user -= 1
