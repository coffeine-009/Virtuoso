#- OS :: CentOS -#
FROM centos:7

# Author
MAINTAINER Coffeine, Inc <vitaliy.tsutsman@musician-virtuoso.com>

#- Dependencies -#
# Install GIT, Java.
RUN yum install -y git wget java-1.8.0-openjdk-devel

# Install tomcat #
RUN cd /opt \
    && wget http://archive.apache.org/dist/tomcat/tomcat-8/v8.0.33/bin/apache-tomcat-8.0.33.tar.gz \
    && tar xvf apache-tomcat-8.0.33.tar.gz \
    && ln -s /opt/apache-tomcat-8.0.33 /usr/share/tomcat \
    && wget http://apache.volia.net/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz \
    && tar -xzf apache-maven-3.3.9-bin.tar.gz && rm apache-maven-3.3.9-bin.tar.gz;

RUN alternatives --install /usr/bin/mvn mvn /opt/apache-maven-3.3.9/bin/mvn 1;

RUN chmod +x /usr/share/tomcat/bin/catalina.sh

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java

# Mount src dir
ADD . /usr/src/virtuoso

# Define working directory.
WORKDIR /usr/src/virtuoso

RUN mvn install

RUN cp target/Virtuoso-1.0-SNAPSHOT.war /usr/share/tomcat/webapps/virtuoso.war

EXPOSE 8080

#- Entry point -#
CMD [ "/usr/share/tomcat/bin/catalina.sh", "run" ]
