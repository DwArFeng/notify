# ChangeLog

## Release_2.0.0_20260608_build_A

### 功能构建

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
