#### scala_fp 

build Scala use `sbt build`, from project directory, scala_fp

```
$ touch build.sbt
$ sbt 
$ sbt compile 
$ sbt reload 
$ sbt run 
```
configure build.sbt and add dependencies. 

#### set up Spark cluster using Docker 
go to command, 
```
$ docker build -t chloeji/spark:latest .
$ docker run --rm -it chloeji/spark:latest /bin/bash
$ docker run --rm -it --name spark-master --hostname spark-master -p 7077:7077 -p 8080:8080 chloeji/spark:latest /bin/sh
$ shell-> /opt/spark/

run in spark-shell; 
$ spark/bin/spark-class org.apache.spark.deploy.master.Master --ip `hostname` --port 7077 --webui-port 8080

$ docker run --rm -it --name spark-master --hostname spark-master -p 7077:7077 -p 8080:8080 --network spark_network chloeji/spark:latest bin/sh

$ docker ps 
go to http://localhost:8080 //check UI page;

$ docker network create spark_network
86d4bf84ffbf3a68542a8bf91dd169278e2310490412c0110afb5fdd6ee8c538
$ docker network ls 
spark-network 

$ docker stop spark-master
$ docker rm spark-master //remove current instance of running master. 
```

#### create a new instance of the same docker image; 
run below cmd to start worker,
```
$ docker run --rm -it --name spark-worker --hostname spark-worker --network spark_network chloeji/spark:latest /bin/sh

run in spark-shell; 
$ spark/bin/spark-class org.apache.spark.deploy.worker.Worker --webui-port 8080 spark://spark-master:7077
```

###### master; worker runs in spark shell 
<img src='/spark_scala_docker/imgs/master.png'> 
<img src='/spark_scala_docker/imgs/worker.png'>

#### practice with Scala Spark
