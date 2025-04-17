# url로 html 읽어오기
import requests

url = "http://db.wisejia.com"
r = requests.get(url)
print(r.text)

