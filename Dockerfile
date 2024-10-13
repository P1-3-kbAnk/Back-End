# Tomcat 기반 이미지 사용
FROM tomcat:10.1.30

# WAR 파일 복사
COPY build/libs/backend.war-0.1.0.war /usr/local/tomcat/webapps/backend.war

## 타임존 설정 (한국 표준시)
#RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime

# 타임존 설정 (한국 표준시)
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && echo "Asia/Seoul" > /etc/timezone

# Tomcat이 기본적으로 사용하는 8080 포트를 외부로 노출
EXPOSE 8080

# Tomcat 실행
CMD ["catalina.sh", "run"]
