#- OS :: CentOS -#
FROM centos:7

# Author
MAINTAINER Coffeine, Inc <support@musician-virtuoso.com>

#- Dependencies -#
# Install GIT, Java.
RUN yum install -y git wget java-1.8.0-openjdk

# Install tomcat #
RUN cd /opt \
    && wget http://archive.apache.org/dist/tomcat/tomcat-8/v8.0.33/bin/apache-tomcat-8.0.33.tar.gz \
    && tar xvf apache-tomcat-8.0.33.tar.gz \
    && ln -s /opt/apache-tomcat-8.0.33 /usr/share/tomcat;

RUN chmod +x /usr/share/tomcat/bin/catalina.sh

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/jre

# Mount src dir
ADD . /usr/src/virtuoso

# Define working directory.
WORKDIR /usr/src/virtuoso

EXPOSE 8080

#- Entry point -#
CMD [ "/usr/share/tomcat/bin/catalina.sh", "run" ]
