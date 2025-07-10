import requests

url = "http://localhost:8080/api/send"

data = {
    "itemName": "smoke",
    "value": 1
}

response = requests.post(url, json=data)

#print(response.status_code)
print(response.text)
