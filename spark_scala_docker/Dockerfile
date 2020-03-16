##########################################################
#Dockerfile to build Spark based on Scala 
#Based on alpine image 
##########################################################

# Set base image 
FROM alpine:3.10 AS build 

# Set maintainer 
LABEL maintainer="Chloe Ji <chloejiy@gmail.com>"

ARG SPARK_VERSION=2.4.5
ARG HADOOP_VERSION=2.7

RUN apk add --no-cache curl bash python3 py-pip nss libc6-compat \
      && ln -s /lib64/ld-linux-x86-64.so.2 /lib/ld-linux-x86-64.so.2 \
      && wget https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz \
      && tar -xvzf spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz \
      && mv spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} spark \
      && rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz \
      && cd /

FROM openjdk:8-alpine
RUN apk --update --no-cache add bash

COPY scripts/start-master.sh /usr/local/bin/
COPY scripts/start-worker.sh  /usr/local/bin/
COPY scripts/start.sh  /usr/local/bin/
RUN chmod +x /usr/local/bin/start-master.sh 
RUN chmod +x /usr/local/bin/start-worker.sh
RUN chmod +x /usr/local/bin/start.sh

EXPOSE 8080 8081 8082  7077 

ENV SPARK_MASTER "spark://spark-master:7077"
ENV SPARK_MASTER_PORT 7077
ENV SPARK_MASTER_WEBUI_PORT 8080
ENV SPARK_HOME /opt/spark
ENV SPARK_LOGS_DIR ${SPARK_HOME}/logs
ENV PATH $PATH:${SPARK_HOME}/bin
#COPY --from=builder [file] [dest]
# https://github.com/docker/cli/issues/1559
COPY --from=build /spark ${SPARK_HOME}

ENV PYTHONHASHSEED 1
CMD ["/bin/bash", "/start.sh"]