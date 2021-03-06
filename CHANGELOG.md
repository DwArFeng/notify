# ChangeLog

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
  - com.dwarfeng.notify.stack.bean.entity.SenderRelation。

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
