
# requests -> text
# 텍스트를 html tag로 변경해주는 beautifulsoup(bs)

# pip install beautifulsoup4
# pip install requests 설치 후 import
from bs4 import BeautifulSoup
import requests

response = requests.get('https://www.clien.net/service/board/park')
if response.status_code == 200:
    html = response.text
    soup = BeautifulSoup(html, 'html.parser')
    w = soup.select_one('#menu-scroll > div > div.menu_somoim')
    e = w.select('a > .menu_over')
    # #menu-scroll > div > div.menu_somoim
    # #menu-scroll > div > div.menu_somoim > a:nth-child(3) > span.menu_over
    for i in e:
        print(i.text)
else:
    print('문제가 발생했습니다')
# #right-content-area > div > div:nth-child(5) > div > div.DailyBoardView-module__info_group___f8OSu


# http://wisejia.iptime.org/rest/

