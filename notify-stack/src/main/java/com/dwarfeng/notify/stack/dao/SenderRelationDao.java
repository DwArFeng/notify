package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.SenderRelation;
import com.dwarfeng.notify.stack.bean.entity.key.SenderRelationKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 发送器关系数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SenderRelationDao extends BatchBaseDao<SenderRelationKey, SenderRelation>, EntireLookupDao<SenderRelation>,
        PresetLookupDao<SenderRelation> {
}
