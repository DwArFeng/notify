package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.notify.stack.bean.entity.key.MetaKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 元数据数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface MetaDao extends BatchBaseDao<MetaKey, Meta>, EntireLookupDao<Meta>,
        PresetLookupDao<Meta> {
}
