FROM swaggerapi/swagger-ui:latest
MAINTAINER ITAcademy

ADD /user-service/spec/user-service.yml /usr/share/nginx/html/doc/user-service.yaml
ADD /audit-service/spec/audit-service.yml /usr/share/nginx/html/doc/audit-service.yaml
ADD /task-service/spec/task-service.yml /usr/share/nginx/html/doc/task-service.yaml

ENV URLS="[{ url: 'doc/user-service.yaml', name: 'User'},{ url: 'doc/audit-service.yaml', name: 'Audit'},{ url: 'doc/task-service.yaml', name: 'Task'},]"
ENV URLS_PRIMARY_NAME="User"