FROM alage/recruitment

WORKDIR /app

ADD . /app

EXPOSE 8000

ENV NAME WebappEnv

USER root
RUN mvn clean
RUN mvn package
RUN mkdir -p /var/log/boblaghei
RUN chmod -R 777 /var/log/boblaghei
USER seluser
CMD java -jar target/web-0.0.1-SNAPSHOT.jar