# 2025-04-30
# 파이썬 데이터 분석
# pip install pymysql
# mariadb -> mysql 에서 나와서 제어 가능

import pymysql

# db 정보
db_config = {
    'host' : 'localhost',
    'user':'j25a01',
    'password':'j25a01',
    'database':'j25a01',
    'port':3300,
    'charset':'utf8mb4'
}

conn = pymysql.connect(
    host=db_config['host'],
    user=db_config['user'],
    password=db_config['password'],
    database=db_config['database'],
    port=db_config['port'],
    charset=db_config['charset']
)
cursor = conn.cursor()
sql = "SELECT * FROM kma"
# sql = "SELECT COUNT(*) as count FROM kma"
cursor.execute(sql)
count = cursor.fetchall()
# count = cursor.fetchone()
print(len(count))
for i in count:
    print(i[0])