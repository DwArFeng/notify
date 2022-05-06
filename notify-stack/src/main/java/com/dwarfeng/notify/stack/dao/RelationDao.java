package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.Relation;
import com.dwarfeng.notify.stack.bean.entity.key.RelationKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 关系数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface RelationDao extends BatchBaseDao<RelationKey, Relation>, EntireLookupDao<Relation>,
        PresetLookupDao<Relation> {
}
