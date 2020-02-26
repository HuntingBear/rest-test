### RESTful service управления пользователями

_Получить список всех пользователей:_ GET localhost:8080/clients

_Получить информацию о конкретном пользователе:_ GET localhost:8080/clients/{id}

_Удалить пользователя:_ DELETE localhost:8080/clients/{id}

_Добавить нового пользователя:_ POST localhost:8080/clients
body: json
{
  "login": "me",
  "firstName": "Михаил",
  "secondName": "Михайлов",
  "birthday": "1994-01-15",
  "about": "a few words about myself",
  "address": "СПб",
  "password": "123"
}

_Отредактировать существующего пользователя:_ PATCH localhost:8080/clients/{id}
body: json
{
  "address": "СПб, Морская наб. д.70 кв. 11",
  "password": "000"
}