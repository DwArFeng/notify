# ChangeLog

## Release_2.0.0_20260608_build_A

### 功能构建

- Wiki 更新。
  - docs/wiki/zh-CN/VersionBlacklist.md。
  - docs/wiki/zh-CN/CompileBySource.md。
  - docs/wiki/zh-CN/QuickStart.md。

- `notify-distribute` 模块新增。
  - 新增 `notify-distribute` 模块，负责各个构型的产物分发。

- `notify-node` 模块重构。
  - 将 `notify-node` 调整为聚合模块。
  - 新建 `notify-node-all-he` 模块，迁移项目原有内容，并形成项目的 `he` 构型。
  - 其它相关文件路径、包路径、配置路径等同步调整。

- 配置命名空间全局唯一化改造。
  - 为 `notify-impl` 模块配置键统一增加全球唯一前缀，以消除跨微服务同名配置冲突。
  - 为 `notify-node` 模块配置键统一增加全球唯一前缀，以消除跨微服务同名配置冲突。
  - 处理 `redis/prefix.properties` 缓存前缀配置的实体命名空间。
  - 同步调整 `*.java` 中的配置读取占位符。
  - 同步调整 `*application-context-*.xml` 中的配置读取占位符。

- 部分代理类实现中的字段类型提升为对应的接口，与具体实现解耦。
  - com.dwarfeng.notify.impl.cache.DispatcherInfoCacheImpl。
  - com.dwarfeng.notify.impl.cache.DispatcherSupportCacheImpl。
  - com.dwarfeng.notify.impl.cache.MetaCacheImpl。
  - com.dwarfeng.notify.impl.cache.MetaIndicatorCacheImpl。
  - com.dwarfeng.notify.impl.cache.NotifyHistoryCacheImpl。
  - com.dwarfeng.notify.impl.cache.NotifyInfoRecordCacheImpl。
  - com.dwarfeng.notify.impl.cache.NotifySendRecordCacheImpl。
  - com.dwarfeng.notify.impl.cache.NotifySettingCacheImpl。
  - com.dwarfeng.notify.impl.cache.RouterInfoCacheImpl。
  - com.dwarfeng.notify.impl.cache.RouterSupportCacheImpl。
  - com.dwarfeng.notify.impl.cache.SenderInfoCacheImpl。
  - com.dwarfeng.notify.impl.cache.SenderSupportCacheImpl。
  - com.dwarfeng.notify.impl.cache.TopicCacheImpl。
  - com.dwarfeng.notify.impl.cache.UserCacheImpl。
  - com.dwarfeng.notify.impl.dao.DispatcherInfoDaoImpl。
  - com.dwarfeng.notify.impl.dao.DispatcherSupportDaoImpl。
  - com.dwarfeng.notify.impl.dao.MetaDaoImpl。
  - com.dwarfeng.notify.impl.dao.MetaIndicatorDaoImpl。
  - com.dwarfeng.notify.impl.dao.NotifyHistoryDaoImpl。
  - com.dwarfeng.notify.impl.dao.NotifyInfoRecordDaoImpl。
  - com.dwarfeng.notify.impl.dao.NotifySendRecordDaoImpl。
  - com.dwarfeng.notify.impl.dao.NotifySettingDaoImpl。
  - com.dwarfeng.notify.impl.dao.RouterInfoDaoImpl。
  - com.dwarfeng.notify.impl.dao.RouterSupportDaoImpl。
  - com.dwarfeng.notify.impl.dao.SenderInfoDaoImpl。
  - com.dwarfeng.notify.impl.dao.SenderSupportDaoImpl。
  - com.dwarfeng.notify.impl.dao.TopicDaoImpl。
  - com.dwarfeng.notify.impl.dao.UserDaoImpl。
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

- 优化项目的异常处理机制。
  - `notify-sdk` 子模块新增 `ServiceExceptionHelper` 工具类，统一维护项目自身的异常映射关系。
  - `notify-impl` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。
  - `notify-node` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。

- 依赖升级。
  - 升级 `spring-telqos` 依赖版本为 `2.0.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring-terminator` 依赖版本为 `2.0.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-datamark` 依赖版本为 `2.2.0.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `kafka` 依赖版本为 `3.9.2` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.10.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.18` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.23` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.9.5` 以规避漏洞。
  - 升级 `log4j2` 依赖版本为 `2.25.4` 以规避漏洞。
  - 升级 `mapstruct` 依赖版本为 `1.5.5.Final` 以规避漏洞。
  - 升级 `jackson` 依赖版本为 `2.21.2` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `4.0.31` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `0.4.2.a-beta` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.8.3.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `2.0.2.a` 以规避漏洞。

- 优化文件格式。
  - 优化 `assembly.xml` 文件的格式。
  - 优化 `*.properties` 文件的格式。
  - 优化 `application-context-*.xml` 文件的格式。
  - 优化 `pom.xml` 文件的格式。

- 优化开发环境支持。
  - 在 .gitignore 中添加 Vibe Coding 相关文件的忽略规则。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## 更早的版本

[View all changelogs](./changelogs)
