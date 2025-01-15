# 1. 그레블 기반으로 설정
FROM gradle:7.5-jdk17

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 로컬의 .jar 파일을 Docker 이미지의 /app 디렉토리로 복사
COPY *.jar app.jar

# 4. 애플리케이션 실행 커맨드
ENTRYPOINT ["java", \
            "-Xms768m", \
            "-Xmx768m", \
            "-Xminf0.4", \
            "-Xmaxf0.7", \
            "-jar", "app.jar"]

# 5. 컨테이너가 사용하는 포트 설정
EXPOSE 8080