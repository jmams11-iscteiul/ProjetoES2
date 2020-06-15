FROM wordpress:5.4.1
RUN mkdir -p /usr/share/man/man1
RUN export DEBIAN_FRONTEND=noninteractive && apt-get -y update && apt-get install -y default-jre 
RUN ln -s /etc/apache2/mods-available/cgi.load /etc/apache2/mods-enabled/cgi.load
RUN echo "ServerName localhost" >> /etc/apache2/apache2.conf
RUN service apache2 restart
RUN echo "deb http://deb.debian.org/debian stretch main" >> /etc/apt/sources.list
RUN apt-get -y update
RUN apt-get install -y openjdk-8-jdk
RUN export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre
RUN curl https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz --output /home/apache-maven.tar.gz
RUN mkdir /home/maven
RUN tar zxvf /home/apache-maven.tar.gz -C /home/maven
RUN export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre
RUN apt-get install -y cron
RUN apt-get install -y firefox-esr
RUN echo "cd /usr/lib/cgi-bin/requisito2 && /home/maven/apache-maven-3.6.3/bin/mvn -Dtest=testClass test" >> /home/requisito2.sh
RUN echo "0 */2 * * * /bin/bash /home/requisito2.sh" > /home/crontabRequisito2
RUN crontab /home/crontabRequisito2	