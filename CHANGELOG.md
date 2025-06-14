# ChangeLog

### Release_1.5.2_20250609_build_A

#### 功能构建

- Wiki 编写。
  - docs/wiki/zh_CN/ShellScripts.md。
  - docs/wiki/zh_CN/BatchScripts.md。

- 依赖升级。
  - 升级 `snowflake` 依赖版本为 `1.6.4.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.5.10.a` 以规避漏洞。
  - 升级 `dwarfeng-datamark` 依赖版本为 `1.0.2.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.5.1_20250525_build_A

#### 功能构建

- Wiki 编写。
  - docs/wiki/zh_CN/VersionBlacklist.md。

- 优化 Hibernate 实体审计字段位置。
  - com.dwarfeng.notify.impl.bean.entity.HibernateDispatcherInfo。
  - com.dwarfeng.notify.impl.bean.entity.HibernateMetaIndicator。
  - com.dwarfeng.notify.impl.bean.entity.HibernateNotifySetting。
  - com.dwarfeng.notify.impl.bean.entity.HibernateRouterInfo。
  - com.dwarfeng.notify.impl.bean.entity.HibernateSenderInfo。
  - com.dwarfeng.notify.impl.bean.entity.HibernateTopic。
  - com.dwarfeng.notify.impl.bean.entity.HibernateUser。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.5.0_20250525_build_A

#### 功能构建

- Wiki 编写。
  - 构建 wiki 目录结构。
  - docs/wiki/en_US/Contents.md。
  - docs/wiki/en_US/Introduction.md。
  - docs/wiki/zh_CN/Contents.md。
  - docs/wiki/zh_CN/Introduction.md。

- 优化配置文件。
  - 优化 `application-context-database.xml`，使得更多属性可以在配置文件中配置。

- 为部分重置器的依赖注入对象增加限定符，以避免潜在的冲突。
  - com.dwarfeng.notify.impl.handler.resetter.DubboResetter。

- 为部分工具类中方法的入口参数增加 `@Nonnull` 注解。
  - com.dwarfeng.notify.impl.service.telqos.CommandUtil。

- 优化实体映射器机制。

- 导入运维指令。
  - com.dwarfeng.datamark.service.telqos.*。

- 增加 Hibernate 实体数据标记字段，并应用相关实体侦听器。
  - com.dwarfeng.notify.impl.bean.entity.HibernateDispatcherInfo。
  - com.dwarfeng.notify.impl.bean.entity.HibernateMetaIndicator。
  - com.dwarfeng.notify.impl.bean.entity.HibernateNotifySetting。
  - com.dwarfeng.notify.impl.bean.entity.HibernateRouterInfo。
  - com.dwarfeng.notify.impl.bean.entity.HibernateSenderInfo。
  - com.dwarfeng.notify.impl.bean.entity.HibernateTopic。
  - com.dwarfeng.notify.impl.bean.entity.HibernateUser。

- 增加依赖。
  - 增加依赖 `dwarfeng-datamark` 以应用其新功能，版本为 `1.0.1.a`。

- 优化部分单元测试代码，以规避潜在的 bug。
  - com.dwarfeng.notify.impl.service.DispatcherInfoMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.DispatcherSupportMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.MetaIndicatorMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.MetaMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.NotifyHistoryMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.NotifyInfoRecordMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.NotifySendRecordMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.NotifySettingMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.RouterInfoMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.RouterSupportMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.SenderInfoMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.SenderSupportMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.TopicMaintainServiceImplTest。
  - com.dwarfeng.notify.impl.service.UserMaintainServiceImplTest。

- SPI 目录结构优化。
  - 将驱动机制的 SPI 接口与抽象类提相关代码文件提升至 `sdk` 模块中。
  - 将执行机制的 SPI 接口与抽象类提相关代码文件提升至 `sdk` 模块中。
  - 将推送机制的 SPI 接口与抽象类提相关代码文件提升至 `sdk` 模块中。
  - 将重置机制的 SPI 抽象类提相关代码文件提升至 `sdk` 模块中。

- 优化部分维护服务实现中的部分方法的性能。
  - com.dwarfeng.notify.impl.service.DispatcherInfoMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.DispatcherSupportMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.MetaIndicatorMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.MetaMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.NotifyHistoryMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.NotifyInfoRecordMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.NotifySendRecordMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.NotifySettingMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.RouterInfoMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.RouterSupportMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.SenderInfoMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.SenderSupportMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.TopicMaintainServiceImpl。
  - com.dwarfeng.notify.impl.service.UserMaintainServiceImpl。

- 优化部分类中部分方法的行为分析行为。
  - com.dwarfeng.notify.impl.cache.NotifyHistoryCacheImpl。
  - com.dwarfeng.notify.impl.cache.NotifySendRecordCacheImpl。
  - com.dwarfeng.notify.impl.dao.NotifyHistoryDaoImpl。
  - com.dwarfeng.notify.impl.dao.NotifySendRecordDaoImpl。

- 优化项目的启停脚本，以规避潜在的路径问题。
  - binres/notify-start.sh。
  - binres/notify-stop.sh。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.5.9.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring` 依赖版本为 `5.3.39` 以规避漏洞。
  - 升级 `kafka` 依赖版本为 `3.9.0` 以规避漏洞。
  - 升级 `protobuf` 依赖版本为 `3.25.5` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.119.Final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.9.3` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.6.3.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.14.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.13.a` 以规避漏洞。
  - 升级 `jackson` 依赖版本为 `2.18.3` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `4.0.26` 以规避漏洞。

#### Bug修复

- 修正 `impl` 模块中错误的 dubbo 应用名称。

- 修复部分功能性实体集合类型的字段在映射时有可能产生空指针异常的问题。
  - com.dwarfeng.notify.sdk.bean.dto.FastJsonNotifyHistoryRecordInfo。
  - com.dwarfeng.notify.sdk.bean.dto.JSFixedFastJsonNotifyHistoryRecordInfo。

#### 功能移除

- 移除 pom.xml 中未使用的版本属性。
  - `dwarfeng-ftp.version`。

---

### Release_1.4.2_20240607_build_A

#### 功能构建

- 优化启停脚本的注释。

- 优化项目启停脚本设置程序的根目录的方式。

- 部分 dubbo 消费者服务注册配置添加 `check="false"` 属性。
  - generateService。

- 增加预设的运维指令。
  - com.dwarfeng.springtelqos.api.integration.log4j2.Log4j2Command。

- 优化 `spring-telqos` 结构。
  - 优化指令注解。
  - 使用 `pack-scan` 扫描 `telqos` 包内所有指令。

- 优化文件格式。
  - 优化 `telqos/connection.properties` 文件的格式。

- 依赖升级。
  - 升级 `snowflake` 依赖版本为 `1.5.1.a` 以应用其新功能。
  - 升级 `subgrade` 依赖版本为 `1.5.3.a` 以应用其新功能。
  - 升级 `spring-telqos` 依赖版本为 `1.1.9.a` 以应用其新功能。
  - 升级 `spring` 依赖版本为 `5.3.31` 以规避漏洞。
  - 升级 `kafka` 依赖版本为 `3.6.1` 以规避漏洞。
  - 升级 `spring-kafka` 依赖版本为 `2.9.11` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.2.0` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.104.final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.7.2` 以规避漏洞。
  - 升级 `slf4j` 依赖版本为 `1.7.36` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.12.a` 以规避漏洞。

- 增加 `PusherAdapter`。
  - 建议任何插件的推送器实现都继承自该适配器。
  - 适配器对所有的事件推送方法都进行了空实现。
  - 解决了增加了新的事件时，旧的推送器实现必须实现新的方法的问题。
  - 从此以后，推送器增加新的事件，将被视作兼容性更新。

- 日志功能优化。
  - 优化默认日志配置，默认配置仅向控制台输出 `INFO` 级别的日志。
  - 优化日志配置结构，提供 `conf/logging/settings.xml` 配置文件及其不同平台的参考配置文件，以供用户自定义日志配置。
  - 优化日志配置结构，提供 `confext/logging-settings.xml` 配置文件，以供外部功能自定义日志配置。
  - 优化启动脚本，使服务支持新的日志配置结构。
  - 优化 `assembly.xml`，使项目打包时输出新的日志配置结构。
  - 优化 `confext/README.md`，添加新的日志配置结构的相关说明。

- 启动器优化。
  - 将入口方法中完整独立的功能封装在子方法中，使入口方法代码结构更加清晰。

- 优化启停脚本的目录结构。

#### Bug修复

- 修复 telqos 工具类中部分注解不正确的 bug。

- 修复部分 CrudOperation 中部分存在性判断方法行为异常的 bug。
  - com.dwarfeng.notify.impl.service.operation.NotifyHistoryCrudOperation。
  - com.dwarfeng.notify.impl.service.operation.NotifySettingCrudOperation。
  - com.dwarfeng.notify.impl.service.operation.TopicCrudOperation。
  - com.dwarfeng.notify.impl.service.operation.UserCrudOperation。

#### 功能移除

- 移除 pom.xml 中未使用的版本属性。
  - `dwarfeng-ftp.version`。

---

### Release_1.4.1_20230618_build_A

#### 功能构建

- 优化了 AbstractResetter 的代码结构，同时对其实现进行了修改。
  - com.dwarfeng.notify.impl.handler.resetter.CronResetter。
  - com.dwarfeng.notify.impl.handler.resetter.DubboResetter。
  - com.dwarfeng.notify.impl.handler.resetter.FixedDelayResetter。
  - com.dwarfeng.notify.impl.handler.resetter.FixedRateResetter。
  - com.dwarfeng.notify.impl.handler.resetter.KafkaResetter。
  - com.dwarfeng.notify.impl.handler.resetter.NeverResetter。

- 依赖升级。
  - 升级 `spring` 依赖版本为 `5.3.27` 以规避漏洞。
  - 升级 `snakeyaml` 依赖版本为 `2.0.0` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.22` 以规避漏洞。
  - 升级 `guava` 依赖版本为 `32.0.1-jre` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.11.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.3.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.5.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.11.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.6.a` 以规避漏洞。

#### Bug修复

- 修复 `DubboResetter` 注册微服务时没有指定 `group` 的问题。

#### 功能移除

- 删除不需要的依赖。
  - 删除 `aopalliance` 依赖。

---

### Release_1.4.0_20230320_build_A

#### 功能构建

- 依赖升级。
  - 升级 `netty` 依赖版本为 `4.1.86.Final` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.21` 以规避漏洞。

- 优化 Windows 系统的启动脚本。

- 优化 Linux 系统的启停脚本。

- 优化接口格式。
  - com.dwarfeng.notify.stack.handler.Router。
  - com.dwarfeng.notify.stack.handler.Sender。
  - com.dwarfeng.notify.stack.handler.Dispatcher。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.3.0_20230219_build_A

#### 功能构建

- 优化 `MapStruct` 实体映射 `Mapper` 接口的路径。

- 重构通知逻辑。

- 优化本地缓存的返回类型。

- 优化通知信息的结构。
  - 将路由、调度、发送信息由与类型强相关的键值实体改为与类型无关的文本映射，提高了信息密度。

- 新增实体及其维护服务。
  - com.dwarfeng.notify.stack.bean.entity.NotifyHistory。
  - com.dwarfeng.notify.stack.bean.entity.NotifyInfoRecord。
  - com.dwarfeng.notify.stack.bean.entity.NotifySendRecord。

- 实体主键包名优化。

#### Bug修复

- (无)

#### 功能移除

- 删除实体。
  - com.dwarfeng.notify.stack.bean.entity.SendHistory。

---

### Release_1.2.4_20230113_build_A

#### 功能构建

- 实现预设发送器。
  - com.dwarfeng.notify.impl.handler.sender.DrainSenderRegistry。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.3_20221231_build_A

#### 功能构建

- 实现重置器。
  - com.dwarfeng.notify.impl.handler.resetter.DubboResetter。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.2_20221222_build_A

#### 功能构建

- 优化可选加载项的文件结构。
  - opt/opt-dispatcher.xml。
  - opt/opt-pusher.xml。
  - opt/opt-resetter.xml。
  - opt/opt-router.xml。
  - opt/opt-sender.xml。

- 增加系统事件。
  - 路由被重置系统事件。
  - 调度被重置系统事件。
  - 发送被重置系统事件。

- 增加重置机制，实现路由、调度、发送本地缓存的动态重置。
  - com.dwarfeng.notify.impl.handler.resetter.CronResetter。
  - com.dwarfeng.notify.impl.handler.resetter.FixedDelayResetter。
  - com.dwarfeng.notify.impl.handler.resetter.FixedRateResetter。
  - com.dwarfeng.notify.impl.handler.resetter.NeverResetter。
  - com.dwarfeng.notify.impl.handler.resetter.KafkaResetter。

- 使用 `subgrade` 预设的 `GeneralLocalCacheHandler` 重写本地缓存。
  - com.dwarfeng.notify.impl.handler.DispatchLocalCacheHandlerImpl。
  - com.dwarfeng.notify.impl.handler.RouteLocalCacheHandlerImpl。
  - com.dwarfeng.notify.impl.handler.SendLocalCacheHandlerImpl。

#### Bug修复

- 修复 `opt/opt-dispatcher.xml` 配置文件缺失的问题。

#### 功能移除

- (无)

---

### Release_1.2.1_20221205_build_A

#### 功能构建

- 优化 `node` 模块的依赖导出，去除导出到 `lib` 目录的多余的 `pom` 文件。

- 优化 `pom.xml` 格式。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.2.0_20221130_build_A

#### 功能构建

- Dubbo 微服务增加分组配置。

- 使用 `MapStruct` 重构 `BeanTransformer`。

- 增加依赖。
  - 增加依赖 `protobuf` 以规避漏洞，版本为 `3.19.6`。
  - 增加依赖 `guava` 以规避漏洞，版本为 `31.1-jre`。
  - 增加依赖 `gson` 以规避漏洞，版本为 `2.8.9`。
  - 增加依赖 `snakeyaml` 以规避漏洞，版本为 `1.33`。
  - 增加依赖 `jackson` 以规避漏洞，版本为 `2.14.0`。

- 依赖升级。
  - 升级 `kafka` 依赖版本为 `2.6.3` 以规避漏洞。
  - 升级 `spring-kafka` 依赖版本为 `2.8.10` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.0.31` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.8.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.5` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.18` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.5.7` 以规避漏洞。
  - 升级 `curator` 依赖版本为 `4.3.0` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.2.5.Final` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.2.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.10.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.14.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.5.a` 以规避漏洞。
  - 升级 `dcti` 依赖版本为 `1.1.5.a` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `4.0.6` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `el` 依赖。
  - 删除 `zkclient` 依赖。
  - 删除 `commons-lang3` 依赖。
  - 删除 `hessian` 依赖。
  - 删除 `jetty` 依赖。
  - 删除 `dozer` 依赖。

---

### Release_1.1.0_20221025_build_A

#### 功能构建

- 实现运维指令。
  - com.dwarfeng.notify.impl.service.telqos.DlcCommand。
  - com.dwarfeng.notify.impl.service.telqos.RlcCommand。
  - com.dwarfeng.notify.impl.service.telqos.SlcCommand。

- 完善预设的调度器。
  - com.dwarfeng.notify.impl.handler.dispatcher.EmptyDispatcherRegistry。
  - com.dwarfeng.notify.impl.handler.dispatcher.EntireDispatcherRegistry。

- 完善预设的路由器注册。
  - com.dwarfeng.notify.impl.handler.router.EntireRouterRegistry。
  - com.dwarfeng.notify.impl.handler.router.EmptyRouterRegistry。
  - com.dwarfeng.notify.impl.handler.router.IdentityRouterRegistry。
  - com.dwarfeng.notify.impl.handler.router.StaticRouterRegistry。

- 优化通知操作的整体流程。
  - com.dwarfeng.notify.impl.handler.NotifyHandlerImpl。

- 新增预设查询。
  - com.dwarfeng.notify.stack.service.TopicMaintainService.ENABLED。

- 新增实体字段。
  - com.dwarfeng.notify.stack.bean.entity.User.enabled。

- 变更实体主键类型。
  - com.dwarfeng.notify.stack.bean.entity.SenderInfo。

- 新增实体及其维护服务。
  - com.dwarfeng.notify.stack.bean.entity.DispatcherInfo。
  - com.dwarfeng.notify.stack.bean.entity.DispatcherSupport。
  - com.dwarfeng.notify.stack.bean.entity.Meta。
  - com.dwarfeng.notify.stack.bean.entity.MetaIndicator。
  - com.dwarfeng.notify.stack.bean.entity.SendHistory。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.2.13.b` 以应用其新功能/规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- 删除实体。
  - com.dwarfeng.notify.stack.bean.entity.SendRelation。

- 删除实体字段。
  - com.dwarfeng.notify.stack.bean.entity.RouterInfo.notifySettingKey。
  - com.dwarfeng.notify.stack.bean.entity.RouterInfo.enabled。

---

### Release_1.0.6_20221011_build_A

#### 功能构建

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.2.12.a` 以应用其最新功能。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.5_20221008_build_A

#### 功能构建

- 优化通知的发送逻辑。
  - 新增主题筛选，通知不会发送到未使能的主题中。
  - 优化通知发送的顺序，按照主题的优先级进行发送。
  - 新增路由器筛选，通知不会调用未使能的路由器的路由方法。
  - 新增通知设置使能，使用未使能的通知设置发送通知会抛出异常。

- 增加实体字段。
  - com.dwarfeng.notify.stack.bean.entity.Topic.enabled。
  - com.dwarfeng.notify.stack.bean.entity.Topic.priority。
  - com.dwarfeng.notify.stack.bean.entity.RouterInfo.enabled。
  - com.dwarfeng.notify.stack.bean.entity.NotifySetting.enabled。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.4_20220927_build_A

#### 功能构建

- 优化项目结构。
  - 增加 `libext` 文件夹，用于加载外部依赖。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.3_20220927_build_A

#### 功能构建

- 优化项目结构。
  - 增加 `confext` 文件夹，用于加载外部的配置文件。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `commons-lang3` 依赖。
  - 删除 `commons-io` 依赖。

---

### Release_1.0.2_20220913_build_A

#### 功能构建

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 依赖升级。
  - 升级 `spring-terminator` 依赖版本为 `1.0.9.a`。
  - 升级 `dutil` 依赖版本为 `beta-0.3.1.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.4.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.9.a` 以规避漏洞。
  - 升级 `hibernate` 依赖版本为 `5.4.24.Final` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.1_20220702_build_A

#### 功能构建

- 依赖升级。
  - 升级 `junit` 依赖版本为 `4.13.2` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.0.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.7.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.8.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.8.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.3.a` 以规避漏洞。

- 新增内置的路由器实现。
  - com.dwarfeng.notify.impl.handler.router.IdentityRouterRegistry。

- 新增内置的发送器实现。
  - com.dwarfeng.notify.impl.handler.sender.LogSenderRegistry。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `joda-time` 依赖。
  - 删除 `pagehelper` 依赖。
  - 删除 `jsqlparser` 依赖。
  - 删除 `commons-fileupload` 依赖。
  - 删除 `solrj` 依赖。

---

### Release_1.0.0_20220604_build_A

#### 功能构建

- 项目建立，清除测试通过。

- 实体建立，单元测试通过。
  - com.dwarfeng.notify.stack.bean.entity.User。
  - com.dwarfeng.notify.stack.bean.entity.RouterInfo。
  - com.dwarfeng.notify.stack.bean.entity.RouterSupport。
  - com.dwarfeng.notify.stack.bean.entity.NotifySetting。
  - com.dwarfeng.notify.stack.bean.entity.SenderInfo。
  - com.dwarfeng.notify.stack.bean.entity.SenderSupport。
  - com.dwarfeng.notify.stack.bean.entity.Topic。
  - com.dwarfeng.notify.stack.bean.entity.SenderSenderRelation。

- 通知发送核心逻辑实现。

- 实现推送机制，并开发预设推送器。
  - com.dwarfeng.notify.impl.handler.pusher.AbstractPusher。
  - com.dwarfeng.notify.impl.handler.pusher.DrainPusher。
  - com.dwarfeng.notify.impl.handler.pusher.LogPusher。
  - com.dwarfeng.notify.impl.handler.pusher.MultiPusher。

- 实现路由机制，并开发预设路由器。
  - com.dwarfeng.notify.impl.handler.router.AbstractRouterRegistry。
  - com.dwarfeng.notify.impl.handler.router.GroovyRouterRegistry。

- 实现发送机制，并开发预设发送器。
  - com.dwarfeng.notify.impl.handler.sender.AbstractSenderRegistry。
  - com.dwarfeng.notify.impl.handler.sender.GroovySenderRegistry。

- 程序开发完成，运行测试通过，打包测试通过。

#### Bug修复

- (无)

#### 功能移除

- (无)
