a = []
b = [1, 2, 3]
c = ['Life', 'is', 'too', 'short']
d = [1, 2, 'Life', 'is']
e = [1, 2, ['Life', 'is']]

a = [1, 2, 3]
print(a[0])

a = [1, 2, 3, 4, 5]
print(a[0:2])

a = "12345"
print(a[0:2])

a = [1, 2, 3]
a.append(4)

a.append([5, 6])

a = [1, 4, 3, 2]
a.sort()

a = ['a', 'c', 'b']
a.sort()

a = ['a', 'c', 'b']
a.reverse()

a = [1, 2, 3]
a.index(3)
a.index(1)

a = [1, 2, 3]
a.insert(0, 4)

a.insert(3, 5)

a = [1, 2, 3, 1, 2, 3]
a.remove(3)
a.remove(3)

a = [1, 2, 3]
a.pop()

a = [1, 2, 3]
a.pop(1)

a = [1, 2, 3, 1]
a.count(1)

a = [1, 2, 3]
a.extend([4, 5])
b = [6, 7]
a.extend(b)

# 삭제 del, remove
# a.remove(value) : 같은 value가 여러 개
# [1, 1, 1, 1]
# a.remove(0)
# a.remove(5)

a = [1, 2, 3, 4, 5]

# 정렬하기 sort, reverse
a.reverse()
print(a)

# 리스트 확장
a.extend([9, 10])
print(a)

# if
if True :
    # 조건이 참일 때 실행합니다.
    print("참입니다.")
    print("!")

num = 5
if num < 4:
    if num > 0:
        pass
    elif num < 0:
        if num == -1:
            pass
    else :
        pass
else :
    print("4보다 큽니다.")

# for
# print(a)
for i in a:
    print(i)

# 1부터 10까지 출력하고 싶다면
# range(10) : 0부터 9까지 출력합니다.
# range(1, 11) : 1부터 10까지 출력합니다.
# range (시작 숫자, 끝 숫자)
# range (시작 숫자, 끝 숫자, 진행)
for i in range(10):
    print(i)

# 함수 만들기
def exam(num1, num2=2) :
    print('a=', num1, 'b=', num2)

exam(20)

print(a)    # [4, 3, 2, 1, 0, 9, 10]
for i in a:
    print(i, end=", ")

print("분리")
for i in range(len(a)):
    print(a[i], end=", ")

# marks2.py
marks = [90, 25, 67, 45, 80]

number = 0
for mark in marks:
    number = number + 1
    if mark < 60:
        continue
    print("%d번 학생 축하합니다. 합격입니다." % number)

# 구구단
for i in range(2,10):        # 1번 for문
    for j in range(1, 10):   # 2번 for문
        print(i*j, end=" ")
    print('')