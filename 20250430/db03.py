# 아까 복사했던 import 없앴어요. -> DBConn.py 파일로 만들어서 ...
#import DBConn
from sympy import totient

from DBConn import DBConn

# 변수 = input("번호를 입력하세요")
# 1. 목록 보여주는 함수
def list():
    print("score 테이블의 모든 데이터를 보여줍니다.")
    # DB 데이터 전체 출력하는 명령어
    # conn생성 -> 클래스 불러오기
    conn = DBConn.conn()
    cursor = conn.cursor()
    sql = "SELECT * FROM score"
    cursor.execute(sql)
    result = cursor.fetchall()
    if result:
        print('┌──────────┬────────────────────────────────────────┐')
        print('│{0:^9}│\t'.format('이름'))
        print('├──────────┼────────────────────────────────────────┤')
        for i in result:
            print('│{0:^9}│\t{1:3}'
                  .format(i[0], i[1]))

    else:
        print("데이터가 없습니다.")
    cursor.close()
    print('└──────────┴────────────────────────────────────────┘')

# 2. 추가하는 함수
def insert():
    print("데이터를 추가합니다.")
    name = input("이름을 입력하세요.")
    kor = int(input("국어 점수를 입력하세요."))
    eng = int(input("영어 점수를 입력하세요."))
    mat = int(input("수학 점수를 입력하세요."))
    # 나머지 점수는 계산
    tot = kor + eng + mat # 저 위에 input() 정수로 변환해주세요.
    ave = tot / 3.0
    # list만들어서 담기
    data = [name, kor, eng, mat, tot, ave]

    conn = DBConn.conn()
    cursor = conn.cursor()
    sql = ("INSERT INTO score (name, kor, eng, mat, tot, ave) "
           "VALUES (%s, %s, %s, %s, %s, %s)")
    cursor.execute(sql, data)
    conn.commit()
    conn.close()
    print("입력 완료했습니다.")






# 3. 삭제를 실행하는 함수
def delete():
    print("삭제를 진행합니다.")
    list()
    name = input("삭제할 이름을 입력하세요.")
    sql = "DELETE FROM score WHERE name=%s"
    conn = DBConn.conn()
    cursor = conn.cursor()
    cursor.execute(sql, name)
    conn.commit() # 추가
    print(name, "삭제했습니다.")

select = 0
while select != 4:
    select = int(input("번호를 선택하세요. 1. 목록, 2. 추가, 3. 삭제, 4. 종료"))
    if select == 1:
        # print("목록을 보여줍니다.")
        list()
    elif select == 2:
        #print("데이터를 추가합니다")
        insert()
    elif select == 3:
        #print("삭제합니다.")
        delete()
    else:
        print("종료합니다")

print("프로그램 종료")