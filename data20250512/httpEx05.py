# 홈페이지 데이터 읽어오기
from bs4 import BeautifulSoup
import requests
# https://finance.naver.com/
response = requests.get('https://finance.naver.com/')
print(response.status_code)
soup = BeautifulSoup(response.text, 'html.parser')
tbody = soup.select_one('#container > div.aside > div > div.aside_area.aside_popular > table > tbody')
# #container > div.aside > div > div.aside_area.aside_popular > table > tbody
# > tr:nth-child(1) > th > a
names = tbody.select('tr')
# print(names)
data = []
for i in names:
    # print(i.text)
    name = i.select_one('th > a').text
    # print(name)
    price = i.select_one('td:nth-child(2)').text
    # print(price)
    data.append([name, price])
#  td:nth-child(3) > span
print(data)