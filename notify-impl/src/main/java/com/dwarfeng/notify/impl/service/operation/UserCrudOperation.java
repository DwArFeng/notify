package com.dwarfeng.notify.impl.service.operation;

import com.dwarfeng.notify.stack.bean.entity.Meta;
import com.dwarfeng.notify.stack.bean.entity.NotifySendRecord;
import com.dwarfeng.notify.stack.bean.entity.User;
import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.notify.stack.bean.key.NotifySendRecordKey;
import com.dwarfeng.notify.stack.cache.MetaCache;
import com.dwarfeng.notify.stack.cache.NotifySendRecordCache;
import com.dwarfeng.notify.stack.cache.UserCache;
import com.dwarfeng.notify.stack.dao.MetaDao;
import com.dwarfeng.notify.stack.dao.NotifySendRecordDao;
import com.dwarfeng.notify.stack.dao.UserDao;
import com.dwarfeng.notify.stack.service.MetaMaintainService;
import com.dwarfeng.notify.stack.service.NotifySendRecordMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCrudOperation implements BatchCrudOperation<StringIdKey, User> {

    private final UserDao userDao;
    private final UserCache userCache;

    private final MetaDao metaDao;
    private final MetaCache metaCache;

    private final NotifySendRecordDao notifySendRecordDao;
    private final NotifySendRecordCache notifySendRecordCache;

    @Value("${cache.timeout.entity.user}")
    private long userTimeout;

    public UserCrudOperation(
            UserDao userDao, UserCache userCache,
            MetaDao metaDao, MetaCache metaCache,
            NotifySendRecordDao notifySendRecordDao, NotifySendRecordCache notifySendRecordCache
    ) {
        this.userDao = userDao;
        this.userCache = userCache;
        this.metaDao = metaDao;
        this.metaCache = metaCache;
        this.notifySendRecordDao = notifySendRecordDao;
        this.notifySendRecordCache = notifySendRecordCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return userCache.exists(key) || userDao.exists(key);
    }

    @Override
    public User get(StringIdKey key) throws Exception {
        if (userCache.exists(key)) {
            return userCache.get(key);
        } else {
            if (!userDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            User user = userDao.get(key);
            userCache.push(user, userTimeout);
            return user;
        }
    }

    @Override
    public StringIdKey insert(User user) throws Exception {
        userCache.push(user, userTimeout);
        return userDao.insert(user);
    }

    @Override
    public void update(User user) throws Exception {
        userCache.push(user, userTimeout);
        userDao.update(user);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 删除与通知设置相关的元数据。
        List<MetaKey> metaKeys = metaDao.lookup(
                MetaMaintainService.CHILD_FOR_USER, new Object[]{key}
        ).stream().map(Meta::getKey).collect(Collectors.toList());
        metaDao.batchDelete(metaKeys);
        metaCache.batchDelete(metaKeys);

        // 删除与用户相关的通知发送信息记录。
        List<NotifySendRecordKey> notifySendRecordKeys = notifySendRecordDao.lookup(
                NotifySendRecordMaintainService.CHILD_FOR_USER, new Object[]{key}
        ).stream().map(NotifySendRecord::getKey).collect(Collectors.toList());
        notifySendRecordCache.batchDelete(notifySendRecordKeys);
        notifySendRecordDao.batchDelete(notifySendRecordKeys);

        // 删除通知设置实体本身。
        userDao.delete(key);
        userCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return userCache.allExists(keys) || userDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return userCache.nonExists(keys) && userDao.nonExists(keys);
    }

    @Override
    public List<User> batchGet(List<StringIdKey> keys) throws Exception {
        if (userCache.allExists(keys)) {
            return userCache.batchGet(keys);
        } else {
            if (!userDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<User> users = userDao.batchGet(keys);
            userCache.batchPush(users, userTimeout);
            return users;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<User> users) throws Exception {
        userCache.batchPush(users, userTimeout);
        return userDao.batchInsert(users);
    }

    @Override
    public void batchUpdate(List<User> users) throws Exception {
        userCache.batchPush(users, userTimeout);
        userDao.batchUpdate(users);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
