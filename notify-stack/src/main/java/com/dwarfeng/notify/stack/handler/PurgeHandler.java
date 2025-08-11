package com.dwarfeng.notify.stack.handler;

import com.dwarfeng.subgrade.stack.handler.DistributedLockHandler;

/**
 * 清除处理器。
 *
 * <p>
 * 该处理器用于定期清除过期的数据。
 *
 * <p>
 * 在 Notify 服务集群中，最多只有一个清除处理器在运行。<br>
 * 当运行的清除处理器发生故障时，集群会自动选举新的清除处理器。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public interface PurgeHandler extends DistributedLockHandler {
}
