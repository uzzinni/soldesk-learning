import pymysql

class DBConn:
    @staticmethod
    def conn():
        conn = pymysql.connect(
            host='localhost',
            user='j25a01',
            password='j25a01',
            database='j25a01',
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
        print(data[4])
        cursor.execute(sql, data)
        self.conn.commit()
        cursor.close()

        return '저장되었습니다'