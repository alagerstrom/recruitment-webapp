FROM selenium/standalone-chrome

WORKDIR /app

ADD . /app

EXPOSE 5000

ENV NAME World

CMD mkdir /var/log/boblaghei
CMD chmod 777 -R /var/log/boblaghei
CMD java -jar target/web-0.0.1-SNAPSHOT.jar