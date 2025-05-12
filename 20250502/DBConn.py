import pymysql
# 파이선 데이터 분석

class DBConn:
    @staticmethod
    def conn():
        conn = pymysql.connect(
            host='localhost',
            user='j25a18',
            password='j25a18',
            database='j25a18',
            port=3300
        )
        return conn

class ScoreDAO:
    def __init__(self):
        self.conn = DBConn.conn()

    def scoreList(self):
        cursor = self.conn.cursor()
        sql = 'SELECT name, kor, eng, mat, tot, ave FROM score'
        cursor.execute(sql)
        result = cursor.fetchall()
        return result

    def dbClose(self):
        self.conn.close()

    def scoreSave(self, data):
        cursor = self.conn.cursor()
        sql = ('INSERT INTO score (name, kor, eng, mat, tot, ave) '
               'VALUES(%s, %s, %s, %s, %s, %s)')
        # print(data[4])
        cursor.execute(sql, data)
        self.conn.commit()
        cursor.close()
        return '저장되었습니다'

    def scoreEdit(self, data):
        cursor = self.conn.cursor()
        sql = ('UPDATE score SET kor=%s, eng=%s, mat=%s, tot=%s, ave=%s '
               'WHERE name=%s')
        cursor.execute(sql, data)
        self.conn.commit()
        cursor.close()

    def scoreDelete(self, names):
        cursor = self.conn.cursor()
        sql = 'DELETE FROM score WHERE name=%s'
        try: # 예외가 발생할 것 같은 문장
            for i in names:
                cursor.execute(sql, i)
            self.conn.commit()
        except: # 모든 예외 발생시 원복하기
            self.conn.rollback()
        finally: # 예외 발생과 상관없이 반드시 실행할 문장
            cursor.close()
        return '삭제 완료'
        return "수정되었습니다"

