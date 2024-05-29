# Vk-bot
Простой vk-bot который цитрует поступабщие сообщения)
## Стэк технологий
- Java 17
- Spring Boot
- VK API
  
## Пример работы

![Снимок экрана 2024-05-29 133803](https://github.com/Discovery19/VK-bot/assets/112725051/4833cba2-d763-424d-a0eb-a762ea367fca)

## Как запустить

* Для начала необходимо зайти в настройки паблика Сообщения > Настройки для бота и включить возможности ботов. После чего перейти в Настройки > Работа с API и создать ключ доступа.

![Снимок экрана 2024-05-29 133803](https://github.com/Discovery19/VK-bot/assets/112725051/e3003eba-c5d6-4dd1-8bf1-885a43562aaa)

* Далее перейти в Callback API и добавить сервер (можно использовать ngrok).
Пример работы ngrok сервера:

![image](https://github.com/Discovery19/VK-bot/assets/112725051/5bc70e39-5e64-42a1-88fa-d274b974892e)

* Затем в application.yaml нужно добавить переменный окружения или вставить свои данные:
```
token:
  confirmation: ${CONFIRM}
  access: ${TOKEN}

secret:
  key: ${KEY}

server:
  port: 8080
```
