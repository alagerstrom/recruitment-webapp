FROM library/openjdk

WORKDIR /app

ADD . /app

EXPOSE 5000

ENV NAME World

CMD echo 'Starting nice docker program...'
CMD ls