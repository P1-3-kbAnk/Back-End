# Tomcat 기반 이미지 사용
FROM tomcat:10.1.30

# WAR 파일 복사
ARG WAR_FILE=build/libs/exploded/backend-1.0-SNAPSHOT.war
COPY ${WAR_FILE} /usr/local/tomcat/webapps/backend.war

# 타임존 설정 (한국 표준시)
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime

