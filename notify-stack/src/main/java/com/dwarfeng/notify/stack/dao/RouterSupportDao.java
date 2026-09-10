package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.RouterSupport;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.data.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.data.stack.dao.PresetLookupDao;

/**
 * 路由器支持数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RouterSupportDao extends BatchBaseDao<StringIdKey, RouterSupport>, EntireLookupDao<RouterSupport>,
        PresetLookupDao<RouterSupport> {
}
