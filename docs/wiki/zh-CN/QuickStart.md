# Quick Start - 快速开始

## 确认系统需求

- CPU：2 核以上。
- 内存：4G 以上。
- 硬盘：100G 以上。
- CentOS 7。
- JDK 25。
- MySQL 8.0.19。
- Redis 8.8.1。
- Zookeeper 3.9.5。
- snowflake-distributed-service 3.0.0.a。

## 获取软件包

从 Github 上获取软件包，软件包可以从 Github 的 Release 页面下载。

## 解压软件包

软件包的名称格式为 `notify-all-he-${version}-release.tar.gz`，其中 `${version}` 为软件包的版本号。

使用工具软件，将软件包上传至服务器 `/usr/local` 目录下，解压软件包。

```shell
cd /usr/local
tar -zxvf notify-all-he-${version}-release.tar.gz
mv notify-all-he-${version} notify
```

## 数据库初始化

连接到 MySQL 数据库，执行如下 SQL 语句：

```sql
# noinspection SpellCheckingInspectionForFile
-- QuickStart 最小链路初始化脚本（Entire Router -> Entire Dispatcher -> Log Sender）
-- 适用数据库：MySQL 8+
-- 说明：
-- 1. 本脚本用于在空库或新库中构建最小可跑通链路。
-- 2. 若与现有数据并存，请先评估主键冲突风险。

CREATE DATABASE IF NOT EXISTS `notify` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
USE `notify`;

-- -----------------------------------------------------
-- 基础表结构（最小子集）
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tbl_notify_setting`
(
    `id`                bigint NOT NULL,
    `enabled`           bit(1)       DEFAULT NULL,
    `label`             varchar(50)  DEFAULT NULL,
    `remark`            varchar(100) DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_topic`
(
    `id`                varchar(150) NOT NULL,
    `enabled`           bit(1)       DEFAULT NULL,
    `label`             varchar(50)  DEFAULT NULL,
    `priority`          int          DEFAULT NULL,
    `remark`            varchar(100) DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_user`
(
    `id`                varchar(150) NOT NULL,
    `enabled`           bit(1)       NOT NULL,
    `remark`            varchar(100) DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_router_info`
(
    `id`                bigint NOT NULL,
    `label`             varchar(50)  DEFAULT NULL,
    `param`             text,
    `remark`            varchar(100) DEFAULT NULL,
    `type`              varchar(50)  DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_dispatcher_info`
(
    `id`                varchar(150) NOT NULL,
    `label`             varchar(50)  DEFAULT NULL,
    `param`             text,
    `remark`            varchar(100) DEFAULT NULL,
    `type`              varchar(50)  DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_sender_info`
(
    `notify_setting_id` bigint       NOT NULL,
    `topic_id`          varchar(150) NOT NULL,
    `label`             varchar(50)  DEFAULT NULL,
    `param`             text,
    `remark`            varchar(100) DEFAULT NULL,
    `type`              varchar(50)  DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`notify_setting_id`, `topic_id`),
    KEY `FKkgvwc26l9vilgjk5eavqoilbq` (`topic_id`),
    CONSTRAINT `FKkgvwc26l9vilgjk5eavqoilbq` FOREIGN KEY (`topic_id`) REFERENCES `tbl_topic` (`id`),
    CONSTRAINT `FKn2kxrbbbcxoe4d80kux8983y5` FOREIGN KEY (`notify_setting_id`) REFERENCES `tbl_notify_setting` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_meta_indicator`
(
    `meta_id`           varchar(150) NOT NULL,
    `topic_id`          varchar(150) NOT NULL,
    `default_value`     text,
    `label`             varchar(50)  DEFAULT NULL,
    `remark`            varchar(100) DEFAULT NULL,
    `created_datamark`  varchar(100) DEFAULT NULL,
    `modified_datamark` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`meta_id`, `topic_id`),
    KEY `FKs7yatx8q4e3cwjik19vtu2gd5` (`topic_id`),
    CONSTRAINT `FKs7yatx8q4e3cwjik19vtu2gd5` FOREIGN KEY (`topic_id`) REFERENCES `tbl_topic` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_meta`
(
    `meta_id`           varchar(150) NOT NULL,
    `notify_setting_id` bigint       NOT NULL,
    `topic_id`          varchar(150) NOT NULL,
    `user_id`           varchar(150) NOT NULL,
    `remark`            varchar(100) DEFAULT NULL,
    `value`             text,
    PRIMARY KEY (`meta_id`, `notify_setting_id`, `topic_id`, `user_id`),
    KEY `FKjvf4ieq8hhotwkm9htt1q5s9n` (`notify_setting_id`),
    KEY `FKct569hmq555986rjc05m58011` (`topic_id`),
    KEY `FKjgaanxswe67cr2h0hfxuki29t` (`user_id`),
    CONSTRAINT `FKct569hmq555986rjc05m58011` FOREIGN KEY (`topic_id`) REFERENCES `tbl_topic` (`id`),
    CONSTRAINT `FKjgaanxswe67cr2h0hfxuki29t` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`),
    CONSTRAINT `FKjvf4ieq8hhotwkm9htt1q5s9n` FOREIGN KEY (`notify_setting_id`) REFERENCES `tbl_notify_setting` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_notify_history`
(
    `id`                bigint NOT NULL,
    `happened_date`     datetime(6)  DEFAULT NULL,
    `notify_setting_id` bigint       DEFAULT NULL,
    `remark`            varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKfs2gbkags80ak2y3xxmxkoh9v` (`notify_setting_id`),
    CONSTRAINT `FKfs2gbkags80ak2y3xxmxkoh9v` FOREIGN KEY (`notify_setting_id`) REFERENCES `tbl_notify_setting` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_notify_info_record`
(
    `notify_history_id` bigint       NOT NULL,
    `record_id`         varchar(150) NOT NULL,
    `type`              int          NOT NULL,
    `value`             text,
    PRIMARY KEY (`notify_history_id`, `record_id`, `type`),
    CONSTRAINT `FK9vwfy3pmxif75rktoqw543t1r` FOREIGN KEY (`notify_history_id`) REFERENCES `tbl_notify_history` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_notify_send_record`
(
    `notify_history_id` bigint       NOT NULL,
    `topic_Id`          varchar(150) NOT NULL,
    `user_Id`           varchar(150) NOT NULL,
    `sender_message`    varchar(100) DEFAULT NULL,
    `succeed_flag`      bit(1)       DEFAULT NULL,
    PRIMARY KEY (`notify_history_id`, `topic_Id`, `user_Id`),
    KEY `FK25tgdj8dqehd2mql0wky0k8vy` (`topic_Id`),
    KEY `FKh61840ayl8iek08yyuph8n12q` (`user_Id`),
    CONSTRAINT `FK25tgdj8dqehd2mql0wky0k8vy` FOREIGN KEY (`topic_Id`) REFERENCES `tbl_topic` (`id`),
    CONSTRAINT `FKh61840ayl8iek08yyuph8n12q` FOREIGN KEY (`user_Id`) REFERENCES `tbl_user` (`id`),
    CONSTRAINT `FKnyq6our3urls33wuxp03wl6u9` FOREIGN KEY (`notify_history_id`) REFERENCES `tbl_notify_history` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_router_support`
(
    `id`            varchar(50) NOT NULL,
    `description`   text,
    `example_param` text,
    `label`         varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_dispatcher_support`
(
    `id`            varchar(50) NOT NULL,
    `description`   text,
    `example_param` text,
    `label`         varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

CREATE TABLE IF NOT EXISTS `tbl_sender_support`
(
    `id`            varchar(50) NOT NULL,
    `description`   text,
    `example_param` text,
    `label`         varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

-- -----------------------------------------------------
-- 演示数据（通知设置 -> 主题 -> 用户 -> 路由/调度/发送）
-- -----------------------------------------------------
INSERT INTO `tbl_notify_setting` (`id`, `enabled`, `label`, `remark`, `created_datamark`, `modified_datamark`)
VALUES (12450, b'1', 'QuickStart.基础通知演示', 'QuickStart 最小链路通知设置', 'quick-start', 'quick-start')
ON DUPLICATE KEY UPDATE `enabled` = VALUES(`enabled`),
                        `label` = VALUES(`label`),
                        `remark` = VALUES(`remark`),
                        `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_topic` (`id`, `enabled`, `label`, `priority`, `remark`, `created_datamark`, `modified_datamark`)
VALUES ('quickstart.log', b'1', 'QuickStart 日志通知主题', 10, '将通知内容输出到日志的演示主题', 'quick-start', 'quick-start')
ON DUPLICATE KEY UPDATE `enabled` = VALUES(`enabled`),
                        `label` = VALUES(`label`),
                        `priority` = VALUES(`priority`),
                        `remark` = VALUES(`remark`),
                        `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_user` (`id`, `enabled`, `remark`, `created_datamark`, `modified_datamark`)
VALUES ('quickstart.user', b'1', 'QuickStart 演示用户', 'quick-start', 'quick-start')
ON DUPLICATE KEY UPDATE `enabled` = VALUES(`enabled`),
                        `remark` = VALUES(`remark`),
                        `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_router_info` (`id`, `label`, `param`, `remark`, `type`, `created_datamark`, `modified_datamark`)
VALUES (12450, 'QuickStart 全体路由器', '', '将所有有效用户作为路由结果', 'entire_router', 'quick-start', 'quick-start')
ON DUPLICATE KEY UPDATE `label` = VALUES(`label`),
                        `param` = VALUES(`param`),
                        `remark` = VALUES(`remark`),
                        `type` = VALUES(`type`),
                        `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_dispatcher_info` (`id`, `label`, `param`, `remark`, `type`, `created_datamark`, `modified_datamark`)
VALUES ('quickstart.log', 'QuickStart 全体调度器', '', '接受路由过程中产生的全部用户', 'entire_dispatcher', 'quick-start', 'quick-start')
ON DUPLICATE KEY UPDATE `label` = VALUES(`label`),
                        `param` = VALUES(`param`),
                        `remark` = VALUES(`remark`),
                        `type` = VALUES(`type`),
                        `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_sender_info` (`notify_setting_id`, `topic_id`, `label`, `param`, `remark`, `type`, `created_datamark`, `modified_datamark`)
VALUES (12450, 'quickstart.log', 'QuickStart 日志发送器', 'info', '将发送内容输出到日志', 'log_sender', 'quick-start', 'quick-start')
ON DUPLICATE KEY UPDATE `label` = VALUES(`label`),
                        `param` = VALUES(`param`),
                        `remark` = VALUES(`remark`),
                        `type` = VALUES(`type`),
                        `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_meta_indicator` (`meta_id`, `topic_id`, `default_value`, `label`, `remark`, `created_datamark`, `modified_datamark`)
VALUES ('quickstart.enabled', 'quickstart.log', 'true', 'QuickStart 主题偏好', '用于演示主题元数据维护', 'quick-start', 'quick-start')
ON DUPLICATE KEY UPDATE `default_value` = VALUES(`default_value`),
                        `label` = VALUES(`label`),
                        `remark` = VALUES(`remark`),
                        `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_meta` (`meta_id`, `notify_setting_id`, `topic_id`, `user_id`, `remark`, `value`)
VALUES ('quickstart.enabled', 12450, 'quickstart.log', 'quickstart.user', 'QuickStart 演示用户主题偏好', 'true')
ON DUPLICATE KEY UPDATE `remark` = VALUES(`remark`),
                        `value` = VALUES(`value`);
```

## 最小化配置

下文列出了启动程序需要改动的最少的配置文件，每个配置文件中仅展示需要改动的配置项。

`conf/curator/connection.properties` 文件中配置 curator 连接信息。

```properties
com.dwarfeng.notify.curator.connect.connect_string=your-host-here:2181
```

`conf/database/connection.properties` 文件中配置数据库连接信息。

```properties
com.dwarfeng.notify.jdbc.url=jdbc:mysql://your-host-here:3306/notify?serverTimezone=Asia/Shanghai&autoReconnect=true
com.dwarfeng.notify.jdbc.username=root
com.dwarfeng.notify.jdbc.password=your-password-here
```

`conf/dubbo/connection.properties` 文件中配置 dubbo 连接信息。

```properties
com.dwarfeng.notify.dubbo.registry.zookeeper.address=zookeeper://your-host-here:2181
com.dwarfeng.notify.dubbo.protocol.dubbo.host=your-host-here
```

`conf/redis/connection.properties` 文件中配置 redis 连接信息。

```properties
com.dwarfeng.notify.redis.hostName=your-host-here
com.dwarfeng.notify.redis.port=6379
com.dwarfeng.notify.redis.password=your-password-here
```

## 修改可选配置

下文列出了启动程序需要改动的可选的配置文件，每个配置文件中仅展示需要改动的配置项。

`opt/opt-dispatcher.xml` 调度器可选配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- ↓ 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<!-- ↑ 以上注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
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
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.dispatcher.EmptyDispatcherRegistry"
        />
        -->

        <!-- 加载 EntireDispatcher -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.dispatcher.EntireDispatcherRegistry"
        />

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
<!-- ↓ 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<!-- ↑ 以上注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
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
<!-- ↓ 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<!-- ↑ 以上注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
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
<!-- ↓ 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<!-- ↑ 以上注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
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
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.router.EmptyRouterRegistry"
        />
        -->

        <!-- 加载 EntireRouter -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.router.EntireRouterRegistry"
        />

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
<!-- ↓ 以下注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringXmlModelInspection -->
<!-- ↑ 以上注释用于抑制 idea 中 .md 的警告，实际并无错误，在使用时可以连同本注释一起删除。 -->
<!--suppress SpringFacetInspection, XmlUnusedNamespaceDeclaration -->
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
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.sender.LogSenderRegistry"
        />

        <!-- 加载 GroovySender -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.sender.GroovySenderRegistry"
        />
        -->

        <!-- 加载 DrainSender -->
        <!--
        <context:include-filter
                type="assignable" expression="com.dwarfeng.notify.impl.handler.sender.DrainSenderRegistry"
        />
        -->
    </context:component-scan>
</beans>
```

## 启动程序

在 `/usr/local/notify` 目录下执行如下命令：

```shell
sh  bin/notify-start.sh
```

1. 观察数据库，数据库将会自动生成 `tbl_` 前缀的表，并具有部分数据。
2. 观察 Redis，Redis 将会自动生成 `com.dwarfeng.notify.entity.` 前缀的缓存键，并具有部分数据。

## 停止程序

在 `/usr/local/notify` 目录下执行如下命令：

```shell
sh  bin/notify-stop.sh
```
