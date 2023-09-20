FROM registry.cn-chengdu.aliyuncs.com/actionblog/jdk:8-alpine
LABEL authors="Damaha"
VOLUME /tmp
ADD hrm-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Xms256m","-Xmx256m","-jar","/app.jar"]