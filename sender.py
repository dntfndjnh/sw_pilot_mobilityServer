import requests

url = "http://localhost:8080/api/send"

data = {
    "itemName": "cut",
    "value": 1,
    "areaName": "Zone C"   # 구역 이름 추가
}

response = requests.post(url, json=data)
print(response.text)
