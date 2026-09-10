package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.NotifySetting;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.data.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.data.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.data.stack.dao.PresetLookupDao;

/**
 * 通知设置数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NotifySettingDao extends BatchBaseDao<LongIdKey, NotifySetting>, EntireLookupDao<NotifySetting>,
        PresetLookupDao<NotifySetting> {
}
