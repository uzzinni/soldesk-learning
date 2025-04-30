
# 2025-04-17 파이썬 기초
# 함수, 클래스, 파일 입출력



def test1():
    pass

def test(a = "Hi"):
    print(a + '테스트 실행입니다')
    return 0


test("!")
# alt + shift + F10

def add(a, b):
    return a + b

c = add(10, 20)
print(c)

d = add("h", 'i')
print(d)


def test2(a, b, c):
    pass

def test3():
    pass

# loop라는 함수를 만들어주세요. 파라미터로 숫자 두개가 들어옵니다.
# loop(a, b) a부터 시작해서  b까지 출력하는 함수를 만들어주세요
def loop(a, b):
    for i in range(a, b+1):
        print(i)

loop(1, 10)

#  시작 숫자, 끝 숫자 까지 나열되는 숫자를 list로 출력해주세요.
def loop2(a, b):
    pass

c = loop2(10, 20)
print(type(c)) # list
print(c)

import random as r
# 실제 1~45 숫자 중 6개 뽑는 함수
def lotto():
    l = set()
    while len(l) < 6:  # 길이가 6 이하라면 참
        l.add(r.randrange(1, 46))
    return l

# 사용자가 지정한 number만큼 게임을 돌려서 리스트에 저장하기
def lottoGame(number):
    l = list()
    for i in range(number):
        l.append(lotto())
    return l

e = lottoGame(5)
print(type(e))
print(e)

# 여섯자리 숫자 무작위로 만들어서 출력
# A123B4
def randomSixCharString():
    first_char = chr(r.randint(65, 90))  # Random uppercase letter
    last_char = chr(r.randint(65, 90))  # Random uppercase letter
    middle_digits = "".join(str(r.randint(0, 9)) for _ in range(4))  # Random 4 digits
    return f"{first_char}{middle_digits}{last_char}"

# Example usage
random_string = randomSixCharString()
print(random_string)

# 문자열에 특수문자 또는 숫자가 있는지 확인하는 함수
def contains_special_characters_or_numbers(input_string):
    for char in input_string:
        if not char.isalpha():
            return True
        return False

# 파라미터가 많이 들어온다면
def add(*a):
    result = 0
    for i in a:
        result += i
    return result

print(add(1,2,3,4,5,6,7,8,9,10,11,12,13,14))






















