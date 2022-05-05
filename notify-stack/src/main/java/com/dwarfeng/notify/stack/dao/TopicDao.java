package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.Topic;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 主题数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface TopicDao extends BatchBaseDao<StringIdKey, Topic>, EntireLookupDao<Topic>, PresetLookupDao<Topic> {
}
