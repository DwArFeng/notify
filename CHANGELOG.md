# ChangeLog

### Release_1.0.0_20220416_build_A

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

#### Bug修复

- (无)

#### 功能移除

- (无)
