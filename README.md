
使用下面仓库使用docker搭建一个hive服务

https://github.com/big-data-europe/docker-hive

将jar包上传到容器内
```bash
docker cp /quick-udfs-1.0-SNAPSHOT.jar contain-id:/opt/udfs
```

使用udf的步骤
```bash
DROP TEMPORARY FUNCTION IF EXIST hello;
delete jar /opt/udfs/quick-udfs-1.0-SNAPSHOT.jar;
add jar /opt/udfs/quick-udfs-1.0-SNAPSHOT.jar;
create temporary function udf_test as 'com.quick.udf.SampleUdf';
select udf_test(id) from t_split limit 10;
```