# http 웹 스크래핑
# 사이트 정보 가져오기
# 2025-05-15 파이선 데이터 분석 & 파이썬 웹 스크랩핑

# pip install requests 설치 후 import
import requests

response = requests.get('https://daum.net') #http : 80 https 443
print(response.status_code) # 응답 = 200
print(response.text) # html code