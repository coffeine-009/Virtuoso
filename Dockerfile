#- Base iamge -#
FROM java:8

# Author
MAINTAINER Coffeine, Inc <vitaliy.tsutsman@musician-virtuoso.com>

# Define dafault value of DB host
ENV DB_HOST localhost

# Mount src dir
RUN mkdir /opt/virtuoso

# Define working directory.
WORKDIR /opt/virtuoso

EXPOSE 8080

#- Entry point -#
CMD [ "java", "-jar", "/opt/virtuoso/virtuoso.jar" ]

ADD build/libs/virtuoso-*.jar /opt/virtuoso/virtuoso.jar
