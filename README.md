# task-manager-multi-service

------------------------------------------------------------------------------
Для отправки почты необходимо создать файл в 
TaskManager/user-service/src/main/resources/confidentila.yml 
и в нём прописать properties для отправки сообщения, включая логин и пароль
P.S. Для корректной работы необходимо ввести не стандартный пароль, 
а специальный пароль для приложений, который можно получить в своём аккаунте гугл

Необходимые properties в файле confidentila.yml:

spring:
    mail:
        host: smtp.gmail.com
        port: 587
        username: ***@gmail.com
        password: ***
        properties:
            mail:
                encoding: UTF-8
                transport:
                    protocol: smtp
                smtp:
                    auth: true
                    starttls:
                        enable: true
------------------------------------------------------------------------------