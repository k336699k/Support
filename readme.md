# Support Project

## Build
Для сохранения настроек и логов приложения необходимо задать переменную окружения SUPPORT_HOME
Файл настроек $SUPPORT_HOME/support.properties (support.properties_example - пример скопировать)
Если переменная не будет найдена будут использоваться значения по умолчанию, для сборки на сервере.

В проекте реализована валидация полей для методов post и put

Примет json объекта
{
  
  "description": "1234467",
  "createDate": "01-05-2014",
  "rezalt": "completed",
  "topic": "dfg"
  }
 Также в проекте реализована авторизация пользователя (базовая)
 password 123456
 username  aaa
 
 Деплой проекта на сервер
deploy_remote_user=admin
deploy_remote_password=abc123

