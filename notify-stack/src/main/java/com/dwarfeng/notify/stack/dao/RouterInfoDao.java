package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.RouterInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 评价信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouterInfoDao extends BatchBaseDao<LongIdKey, RouterInfo>, EntireLookupDao<RouterInfo>,
        PresetLookupDao<RouterInfo> {
}
