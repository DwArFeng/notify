package com.dwarfeng.notify.stack.dao;

import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;

/**
 * 用户数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface UserDao extends BatchBaseDao<StringIdKey, User>, EntireLookupDao<User> {
}
