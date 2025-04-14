## 문자열 길이 구하기 len
name = "Hello \t hello\n"
print(len(name))

print("apple".upper())
print("apple".lower())

print("apple".islower())
print("apple".isupper())

print("aPPle".islower())    #False
print("aPPle".capitalize()) #앞 글자만 대문자로 변경

print("A,B,C,D".split(','))
print("A,B,C,D".split())

# 파이썬 이름 만들기
# 식별자의 첫 문자는 영어로 시작하거나 언더바 '_'로 시작합니다.
# 사용할 수 있는 문자는 : 영어 대소문자, 언더바, 숫자 0-9
# 대소문자 구분합니다 : Apple apple aPple
# apple, _apple 같이 사용합니다.
# 3apple, @apple, a pple 은 사용할 수 없습니다.