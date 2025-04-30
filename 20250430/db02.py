import DBConn

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
        for i in result:
            print(i)
        else:
            print("데이터가 없습니다.")
    cursor.close()



# 2. 추가하는 함수
def insert():
    print("데이터를 추가합니다.")
    name = input("이름을 입력하세요.")
    kor = input("국어 점수를 입력하세요.")
    eng = input("영어 점수를 입력하세요.")
    mat = input("수학 점수를 입력하세요.")
    # 나머지 점수는 계산

# 3. 삭제를 실행하는 함수
def delete():
    print("삭제를 진행합니다.")
    list()
    name = input("삭제할 이름을 입력하세요.")
    #

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