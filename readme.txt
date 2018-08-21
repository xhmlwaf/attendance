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

//TODO
打卡，如果要校验常用设备，就吧设备ID保存起来

根据周数打卡记录 写反了，验证一下。
/student-clock-status
根据学生ID查询当前考勤状态 1未打卡，2到勤，3晚归，4未归  统计时间错了，改一下。  ok

/student-clock-history
根据学生ID和日期查询全部历史 appName考虑下，修改状态接口入手。  ok

/student-clock
更新学生打卡记录 需要重新校验下哦    ok

还有打卡也是坑

StudentClockBizImpl 147行时间的坑

实时统计的两个接口都需要重新测试  ok

/dormitory-building/query-by-user  查询出错
根据用户查询宿舍楼             xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx


/dormitory-check  无效的列类型
结束查寝                               ok

/dormitory-check/day-stat
查寝日统计                        sql错误


/dormitory-check/day-stat/student
日统计学生列表    sql错误


/dormitory-check/query-by-name-code
根据姓名和学号查询学生列表   sql错误，没有明确定义列

/dormitory-check/week-stat
查寝周统计                              和之前同样的问题

/dormitory-check/week-stat/student
周统计学生列表

/dormitory-list-query
根据条件查询宿舍列表   莫名其妙的错误

/dormitory/{dormitoryId}/detail/app
根据用户查询宿舍楼下的宿舍  时间问题没测试

/dormitory/query-by-user
根据用户查询宿舍楼下的宿舍  一样的错

analysis/care/can-start
分页获取可发起关怀列表  可发起关怀列表查询有毛病，listUtil

/analysis/student-care
分页获取已发起/已处理关怀列表 各种缺数据


/analysis/exeception-clock-by-day
每日异常数据分页查询   空指针异常

/analysis/exeception-clock-by-week
每周异常数据分页查询   long 和time的区别

/analysis/exeception-stat-by-day
每日异常数据统计   时间参数接受问题

/analysis/exeception-stat-by-day-of-week
每周异常数据列表统计 一样的坑


/analysis/exeception-stat-by-week
每周异常数据统计  多了个and

、、--------------------------------------------------
/real-time-stat/clock-stat-by-bulding
按宿舍实时统计 只区分打卡未打卡  没分组 ，


