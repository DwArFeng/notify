package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.SenderInfo;
import com.dwarfeng.notify.stack.bean.entity.key.SenderInfoKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 发送器信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderInfoDao extends BatchBaseDao<SenderInfoKey, SenderInfo>, EntireLookupDao<SenderInfo>,
        PresetLookupDao<SenderInfo> {
}
