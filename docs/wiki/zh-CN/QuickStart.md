# Quick Start - 快速开始

## 确认系统需求

- CPU：2 核以上。
- 内存：4G 以上。
- 硬盘：100G 以上。
- CentOS 7。
- JRE 1.8。
- MySQL 8.0.19。
- Redis 5.0.7。
- Zookeeper 3.5.5。
- snowflake-distributed-service 1.8.3.a。

## 获取软件包

从 Github 上获取软件包，软件包可以从 Github 的 Release 页面下载。

## 解压软件包

软件包的名称格式为 `notify-node-${version}-release.tar.gz`，其中 `${version}` 为软件包的版本号。

使用工具软件，将软件包上传至服务器 `/usr/local` 目录下，解压软件包。

```shell
cd /usr/local
tar -zxvf notify-node-${version}-release.tar.gz
mv notify-node-${version}-release/notify-node-${version} notify
```

## 数据库初始化

连接到 MySQL 数据库，执行如下 SQL 语句：

```sql
create
database if not exists notify;
```

## 最小化配置

下文列出了启动程序需要改动的最少的配置文件，每个配置文件中仅展示需要改动的配置项。

`conf/curator/connection.properties` 文件中配置 curator 连接信息。

```properties
curator.connect.connect_string=your-host-here:2181
```

`conf/database/connection.properties` 文件中配置数据库连接信息。

```properties
jdbc.url=jdbc:mysql://your-host-here:3306/notify?serverTimezone=Asia/Shanghai&autoReconnect=true
jdbc.username=root
jdbc.password=your-password-here
```

`conf/dubbo/connection.properties` 文件中配置 dubbo 连接信息。

```properties
dubbo.registry.zookeeper.address=zookeeper://your-host-here:2181
dubbo.protocol.dubbo.host=your-host-here
```

`conf/redis/connection.properties` 文件中配置 redis 连接信息。

```properties
redis.hostName=your-host-here
redis.port=6379
redis.password=your-password-here
```

## 修改可选配置

下文列出了启动程序需要改动的可选的配置文件，每个配置文件中仅展示需要改动的配置项。

`opt/opt-dispatcher.xml` 调度器可选配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.notify.impl.handler.dispatcher" use-default-filters="false">
        <!-- 加载 EmptyDispatcher -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.dispatcher.EmptyDispatcherRegistry"
        />

        <!-- 加载 EntireDispatcher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.dispatcher.EntireDispatcherRegistry"
        />
        -->

        <!-- 加载 GroovyDispatcher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.dispatcher.GroovyDispatcherRegistry"
        />
        -->
    </context:component-scan>
</beans>
```

`opt/opt-pusher.xml` 推送器可选配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.notify.impl.handler.pusher" use-default-filters="false">
        <!-- 加载 DrainPusher -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.pusher.DrainPusher"
        />

        <!-- 加载 LogPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.pusher.LogPusher"
        />
        -->

        <!-- 加载 MultiPusher -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.pusher.MultiPusher"
        />
        -->
    </context:component-scan>
</beans>
```

`opt/opt-resetter.xml` 重置器可选配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.notify.impl.handler.resetter" use-default-filters="false">
        <!-- 加载 NeverResetter -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.resetter.NeverResetter"
        />

        <!-- 加载 FixedDelayResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.resetter.FixedDelayResetter"
        />
        -->

        <!-- 加载 FixedRateResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.resetter.FixedRateResetter"
        />
        -->

        <!-- 加载 CronResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.resetter.CronResetter"
        />
        -->

        <!-- 加载 KafkaResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.resetter.KafkaResetter"
        />
        -->

        <!-- 加载 DubboResetter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.resetter.DubboResetter"
        />
        -->
    </context:component-scan>
</beans>
```

`opt/opt-router.xml` 路由器可选配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.notify.impl.handler.router" use-default-filters="false">
        <!-- 加载 EmptyRouter -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.router.EmptyRouterRegistry"
        />

        <!-- 加载 EntireRouter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.router.EntireRouterRegistry"
        />
        -->

        <!-- 加载 StaticRouter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.router.StaticRouterRegistry"
        />
        -->

        <!-- 加载 IdentityRouter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.router.IdentityRouterRegistry"
        />
        -->

        <!-- 加载 GroovyRouter -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.router.GroovyRouterRegistry"
        />
        -->
    </context:component-scan>
</beans>
```

`opt/opt-sender.xml` 发送器可选配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.notify.impl.handler.sender" use-default-filters="false">
        <!-- 加载 LogSender -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.sender.LogSenderRegistry"
        />
        -->

        <!-- 加载 GroovySender -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.sender.GroovySenderRegistry"
        />
        -->

        <!-- 加载 DrainSender -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.sender.DrainSenderRegistry"
        />
    </context:component-scan>
</beans>
```

## 启动程序

在 `/usr/local/notify` 目录下执行如下命令：

```shell
sh  bin/notify-start.sh
```

1. 观察数据库，数据库将会自动生成 `tbl_router_support`、`tbl_sender_support`、`tbl_dispatcher_support` 表，表中有支持数据。
2. 观察 Redis，Redis 将会自动生成 `entity.router_support.`、`entity.sender_support.`、`entity.dispatcher_support.`
   前缀的缓存键，键中有支持数据。

## 停止程序

在 `/usr/local/notify` 目录下执行如下命令：

```shell
sh  bin/notify-stop.sh
```
