# logback-mongodb：

### 功能：将json格式的日志输出到mongodb中

```xml

<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE xml>
<configuration>
	<contextName>logback-mongodb-print</contextName>
	<property name="LOG_PATH" value="d://logback-mongodb-print-logs" />
	<!--设置系统日志目录 -->
	<property name="APPDIR" value="logs" />
	<appender name="mongoAppender" class="com.hongda.logback.mongodb.MongoDBLoggingEventAppender">
		<uri>mongodb://localhost:27017/test.logs</uri>
	</appender>
	<root level="INFO">
		<appender-ref ref="mongoAppender" />
	</root>
</configuration>

```
