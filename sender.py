import requests

url = "http://localhost:8080/api/send"

data = {
    "itemName": "smoke",
    "value": 1,
    "areaName": "Zone B"   # 구역 이름 추가
}

response = requests.post(url, json=data)
print(response.text)
