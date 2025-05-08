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
        cursor.execute(sql, (data[1], data[2], data[3], data[4], data[5], data[0]))
        self.conn.commit()
        cursor.close()
        return "수정되었습니다"