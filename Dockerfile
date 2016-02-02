#- OS :: Debian -#
FROM debian:jessie

# Author
MAINTAINER Coffeine Inc

#- Dependencies -#
# Install Java.
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list && \
  echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list && \
  apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886 && \
  apt-get update && \
  apt-get install -y oracle-java8-installer

# Install tomcat8
RUN apt-get install -y tomcat8

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

# Mount src dir
ADD . /usr/src/virtuoso

# Define working directory.
WORKDIR /usr/src/virtuoso

# Install related services
#RUN pip install -r requirements.txt

# Define default command.
CMD ["bash"]
