FROM ubuntu:14.04.5

EXPOSE 8080

RUN apt-get update
RUN apt-get install -y vim openjdk-7-jre tomcat7

COPY webapps/ /var/lib/tomcat7/webapps/

ENV JAVA_HOME=/usr/lib/jvm/java-1.7.0-openjdk-amd64
ENV CATALINA_BASE=/var/lib/tomcat7

CMD /usr/share/tomcat7/bin/catalina.sh run
