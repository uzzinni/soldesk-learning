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