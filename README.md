# task-manager-multi-service

------------------------------------------------------------------------------
 Реализована отправка почты при помощи gmail.com или mail.ru
 Для отправки почты необходимо создать файл в 
 TaskManager/user-service/src/main/resources/confidentila.yml 
 и в нём прописать properties для отправки сообщения, включая логин и пароль
 P.S. - Для корректной работы необходимо ввести не стандартный пароль, 
 а специальный пароль для приложений, 
 который можно получить в своём аккаунте гугл или mail.ru

 Необходимые properties в файле confidentila.yml
###                                             для gmail.com:

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

###                                        или для mail.ru:

spring:
  mail:
    host: smtp.mail.ru
    port: 465
    username: ***@mail.ru
    password: ***
    properties:
      "mail.smtp.ssl.enable": true
      mail:
        encoding: UTF-8
        transport:
          protocol: smtp
        smtp:
          auth: true
          starttls:
            enable: true
------------------------------------------------------------------------------
