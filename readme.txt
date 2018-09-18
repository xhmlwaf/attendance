
 mvn install:install-file -DgroupId=com.jdbc -DartifactId=oracle -Dversion=11g -Dpackaging=jar -Dfile=ojdbc6.jar

 mvn install:install-file -Dfile=D:\QRCode.jar -DgroupId=QRCode -DartifactId=QRCode -Dversion=3.0 -Dpackaging=jar

 nohup java  -Xms64m -Xmx2000m  -jar -Dserver.port=10011 attendance-0.0.1-SNAPSHOT.jar

mstsc

登录地址：
219.153.12.197

：31002
用户名密码：
administrator/123!@#@Yh68529599

ssh 219.153.12.197 10060
root/123!@#@Yh68529599



http://www.yunhuakeji.com:10070/htsjgl/#/login

 后台数据管理
http://www.yunhuakeji.com:10070/htszgl/#/login

 后台设置管理
http://www.yunhuakeji.com:10070/QRcode/femaleIndex.html#/login

 女生大屏
http://www.yunhuakeji.com:10070/QRcode/maleIndex.html#/login

 男生大屏

初始化管理员帐号
 insert into ACCOUNT (ID, USER_ID, ROLE_TYPE, PASSWORD)
 values (1, 2147483647, 0, '$2a$10$X6N8RhRf7CO2sQ8DeRA6HeF6u2kQP/YaitIYlIKv/myejJdlfoRpW');