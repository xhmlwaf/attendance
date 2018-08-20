一、包结构说明

1.aspect 通用切面
2.config 通用配置
3.constants 常量定义
4.enums 枚举定义


1、数据库更新成最新的。
2、提交自己的数据库设计
3、写接口，测试功能

从基础数据开始测试。选择数据控制器开始。
1.让帮忙建库
2.周数实现，连续晚归天数，连续未归天数等实现，排序相关
3.二维码保存实现。
4.加日志


全部都用内存排序实现

注意更新时间的数据库配置

打卡表加一个ClassId
关怀表加一个classId



 mvn install:install-file -DgroupId=com.jdbc -DartifactId=oracle -Dversion=11g -Dpackaging=jar -Dfile=ojdbc6.jar

 mvn install:install-file -Dfile=D:\QRCode.jar -DgroupId=QRCode -DartifactId=QRCode -Dversion=3.0 -Dpackaging=jar

 nohup java  -Xms64m -Xmx2000m  -jar -Dserver.port=10011 attendance-0.0.1-SNAPSHOT.jar

学生打卡、晚归查寝、平台后台

201760230413


mstsc

登录地址：
219.153.12.197

：31002
用户名密码：
administrator/123!@#@Yh68529599

ssh 219.153.12.197 10060
root/123!@#@Yh68529599