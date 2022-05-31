@echo off

rem 程序的根目录
SET basedir=C:\Program Files\notify
rem 日志的根目录
SET logdir=%basedir%\logs
rem JVM参数
SET jvm_option=-Xmx100m

rem 打开目录，执行程序
cd "%basedir%"
java -classpath "lib\*;libext\*" ^
%jvm_option% ^
-Dlog.dir="%logdir%" ^
-Dlog.consoleEncoding=GBK ^
-Dlog.fileEncoding=UTF-8 ^
${mainClass}

@pause
