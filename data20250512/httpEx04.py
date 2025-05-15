# 홈페이지 데이터 읽어오기
from bs4 import BeautifulSoup
import requests

response = requests.get('https://www.fdic.gov/bank-failures/failed-bank-list')
# print(response.status_code)
# print(response.text)
if response.status_code == 200:
    soup = BeautifulSoup(response.text, 'html.parser')
    titles = soup.select('td.views-field.views-field-title')
    # print(titles)
    for i in titles:
        print(i.text)
else:
    print('문제 발생')
