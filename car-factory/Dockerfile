#JDK kurulumunu yapıyoruz
FROM adoptopenjdk:8-jdk-hotspot

#Projemizin olduğu yeri gösteriyoruz ve kopyalıyoruz
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} springbootdocker_car_factory.jar

#Çalışacağı portu belirliyoruz
EXPOSE 8080

#Projemizi calistıracak komutu yazıyoruz.
ENTRYPOINT ["java","-jar","/springbootdocker_car_factory.jar"]