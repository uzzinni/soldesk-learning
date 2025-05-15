# 홈페이지 데이터 읽어오기
# http://wisejia.iptime.org/rest/
from bs4 import BeautifulSoup
import requests

response = requests.get('http://wisejia.iptime.org/rest/')
print(response.status_code)
# print(response.text)
soup = BeautifulSoup(response.text, 'html.parser')
div = soup.select_one(
        'body > section > div.mt-4.p-2.shadow.rounded > div > div > div')
# print(div)
titles = div.select(
    'div > div.divTableCell.cell2')
# body > section > div.mt-4.p-2.shadow.rounded > div > div > div > div:nth-child(1) > div.divTableCell.code.overflow-hidden
# body > section > div.mt-4.p-2.shadow.rounded > div > div > div > div:nth-child(1) > div.divTableCell.cell2
for i in titles:
    print(i.text)
