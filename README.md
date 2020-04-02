docker cp /Users/wangxc/Code/Github/quick-udfs/target/quick-udfs-1.0-SNAPSHOT.jar 645bfacfb4f5:/opt/udfs

add jar /opt/udfs/quick-udfs-1.0-SNAPSHOT.jar;

create temporary function udf_test as 'com.quick.udf.SampleUdf';
select udf_test(id) from t_split limit 10;
DROP TEMPORARY FUNCTION IF EXIST hello;