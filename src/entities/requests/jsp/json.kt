package com.alext.serv.entities.requests.jsp

const val qrJson1 = """
{
  "mainHeader": {
    "title": "Priority Pass",
      "subtitle": "Бесплатные визиты"
  },
  "qrHeader": {
    "title": "Не более 2 визитов в июле",
      "subtitle": "Не более 12 в год"
  },
  "qr": {
    "qrBase64": "https://www.kaspersky.ru/content/ru-ru/images/repository/isc/2020/9910/a-guide-to-qr-codes-and-how-to-scan-qr-codes-2.png",
    "membershipNumber": {
      "title": "Membership number:",
      "value": 14014700146397
    },
    "cardholderName": {
      "title": "Держатель:",
      "value": "MR JOHN SMITH"
    }
  },
  "expire": {
    "expiryText": "Действует до 25.12.2021",
    "isDate": true
  },    
  "qrImageUrl": "https://www.imgonline.com.ua/examples/qr-code-blue.png",
  "qrButtonText": "Я в бизнес зале",
  "infoButton": {
      "buttonText": "В каких залах отдохнуть?",
      "buttonUrl": "String"
    },
  "accountData": {
    "accountNumber": "40817840704220002523",
    "description": "Текущий счет"
  },
  "notification": {
    "description": "Вы использовали все бесплатные визиты в июле",
    "additionalData": {
        "header": "В августе визитов будет больше, если в июле",
            "items": [
            { 
          "imageUrl": "String",
          "value": "Держать на любых счетах от 6 млн ₽"
          },
          { 
          "imageUrl": "String",
          "value": "Или держать на счетах от 3 млн ₽ и тратить по любым картам более 200 000 ₽ в месяц"
          }
        ],
        "buttonText": "Хорошо"    
      }
  }
}
"""