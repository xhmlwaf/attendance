一、包结构说明

1.aspect 通用切面
2.config 通用配置
3.constants 常量定义
4.enums 枚举定义

TODO
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

 mvn install:install-file -DgroupId=com.jdbc -DartifactId=oracle -Dversion=11g -Dpackaging=jar -Dfile=ojdbc6.jar

 nohup java -jar -Dserver.port=10011 attendance-0.0.1-SNAPSHOT.jar



 {
   "timestamp": "2018-08-16T13:59:46.246+0000",
   "status": 500,
   "error": "Internal Server Error",
   "message": "\n### Error querying database.  Cause: java.sql.SQLException: sql injection violation, syntax error: ERROR. token : RPAREN, pos : 181 : SELECT  CLASS_ID,CLASS_CODE,GRADE_ID,CLASS_MANAGER_ID,CLASS_LEADER_ID,INSTRUCTOR_ID,MAJOR_ID,CAMPUS_ID,CREATED_DATE,STATE,STATE_DATE  FROM B_CLASS_INFO  WHERE       (  CLASS_ID in )\n### The error may exist in com/yunhuakeji/attendance/dao/basedao/ClassInfoMapper.java (best guess)\n### The error may involve com.yunhuakeji.attendance.dao.basedao.ClassInfoMapper.selectByExample\n### The error occurred while executing a query\n### SQL: SELECT  CLASS_ID,CLASS_CODE,GRADE_ID,CLASS_MANAGER_ID,CLASS_LEADER_ID,INSTRUCTOR_ID,MAJOR_ID,CAMPUS_ID,CREATED_DATE,STATE,STATE_DATE  FROM B_CLASS_INFO  WHERE       (  CLASS_ID in )\n### Cause: java.sql.SQLException: sql injection violation, syntax error: ERROR. token : RPAREN, pos : 181 : SELECT  CLASS_ID,CLASS_CODE,GRADE_ID,CLASS_MANAGER_ID,CLASS_LEADER_ID,INSTRUCTOR_ID,MAJOR_ID,CAMPUS_ID,CREATED_DATE,STATE,STATE_DATE  FROM B_CLASS_INFO  WHERE       (  CLASS_ID in )\n; uncategorized SQLException; SQL state [null]; error code [0]; sql injection violation, syntax error: ERROR. token : RPAREN, pos : 181 : SELECT  CLASS_ID,CLASS_CODE,GRADE_ID,CLASS_MANAGER_ID,CLASS_LEADER_ID,INSTRUCTOR_ID,MAJOR_ID,CAMPUS_ID,CREATED_DATE,STATE,STATE_DATE  FROM B_CLASS_INFO  WHERE       (  CLASS_ID in ); nested exception is java.sql.SQLException: sql injection violation, syntax error: ERROR. token : RPAREN, pos : 181 : SELECT  CLASS_ID,CLASS_CODE,GRADE_ID,CLASS_MANAGER_ID,CLASS_LEADER_ID,INSTRUCTOR_ID,MAJOR_ID,CAMPUS_ID,CREATED_DATE,STATE,STATE_DATE  FROM B_CLASS_INFO  WHERE       (  CLASS_ID in )",
   "path": "/select-data/instructor-info/all"
 }
