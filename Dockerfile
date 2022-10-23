FROM openjdk:8
#
EXPOSE 8090
#
ADD target/commerce-app-back-1.jar commerce-app-back-1.jar
#
ENTRYPOINT ["java","-jar","/commerce-app-back-1.jar"]