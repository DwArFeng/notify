package com.dwarfeng.notify.impl.bean;

import com.dwarfeng.notify.impl.bean.entity.*;
import com.dwarfeng.notify.impl.bean.key.*;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.key.*;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Mapper
public interface HibernateMapper {

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    HibernateNotifyInfoRecordKey notifyInfoRecordKeyToHibernate(NotifyInfoRecordKey notifyInfoRecordKey);

    @InheritInverseConfiguration
    NotifyInfoRecordKey notifyInfoRecordKeyFromHibernate(HibernateNotifyInfoRecordKey hibernateNotifyInfoRecordKey);

    HibernateNotifySendRecordKey notifySendRecordKeyToHibernate(NotifySendRecordKey notifySendRecordKey);

    @InheritInverseConfiguration
    NotifySendRecordKey notifySendRecordKeyFromHibernate(HibernateNotifySendRecordKey hibernateNotifySendRecordKey);

    HibernateMetaIndicatorKey metaIndicatorKeyToHibernate(MetaIndicatorKey metaIndicatorKey);

    @InheritInverseConfiguration
    MetaIndicatorKey metaIndicatorKeyFromHibernate(HibernateMetaIndicatorKey hibernateMetaIndicatorKey);

    HibernateMetaKey metaKeyToHibernate(MetaKey metaKey);

    @InheritInverseConfiguration
    MetaKey metaKeyFromHibernate(HibernateMetaKey hibernateMetaKey);

    HibernateSenderInfoKey senderInfoKeyToHibernate(SenderInfoKey senderInfoKey);

    @InheritInverseConfiguration
    SenderInfoKey senderInfoKeyFromHibernate(HibernateSenderInfoKey hibernateSenderInfoKey);

    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "stringId", ignore = true)
    HibernateDispatcherInfo dispatcherInfoToHibernate(DispatcherInfo dispatcherInfo);

    @InheritInverseConfiguration
    DispatcherInfo dispatcherInfoFromHibernate(HibernateDispatcherInfo hibernateDispatcherInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateDispatcherSupport dispatcherSupportToHibernate(DispatcherSupport dispatcherSupport);

    @InheritInverseConfiguration
    DispatcherSupport dispatcherSupportFromHibernate(HibernateDispatcherSupport hibernateDispatcherSupport);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "topicId", ignore = true)
    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "notifySettingId", ignore = true)
    @Mapping(target = "notifySetting", ignore = true)
    @Mapping(target = "metaId", ignore = true)
    HibernateMeta metaToHibernate(Meta meta);

    @InheritInverseConfiguration
    Meta metaFromHibernate(HibernateMeta hibernateMeta);

    @Mapping(target = "topicId", ignore = true)
    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "metaId", ignore = true)
    HibernateMetaIndicator metaIndicatorToHibernate(MetaIndicator metaIndicator);

    @InheritInverseConfiguration
    MetaIndicator metaIndicatorFromHibernate(HibernateMetaIndicator hibernateMetaIndicator);

    @Mapping(target = "senderInfos", ignore = true)
    @Mapping(target = "routerInfo", ignore = true)
    @Mapping(target = "notifyHistories", ignore = true)
    @Mapping(target = "metas", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateNotifySetting notifySettingToHibernate(NotifySetting notifySetting);

    @InheritInverseConfiguration
    NotifySetting notifySettingFromHibernate(HibernateNotifySetting hibernateNotifySetting);

    @Mapping(target = "notifySetting", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateRouterInfo routerInfoToHibernate(RouterInfo routerInfo);

    @InheritInverseConfiguration
    RouterInfo routerInfoFromHibernate(HibernateRouterInfo hibernateRouterInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateRouterSupport routerSupportToHibernate(RouterSupport routerSupport);

    @InheritInverseConfiguration
    RouterSupport routerSupportFromHibernate(HibernateRouterSupport hibernateRouterSupport);

    @Mapping(target = "topicId", ignore = true)
    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "notifySettingId", ignore = true)
    @Mapping(target = "notifySetting", ignore = true)
    HibernateSenderInfo senderInfoToHibernate(SenderInfo senderInfo);

    @InheritInverseConfiguration
    SenderInfo senderInfoFromHibernate(HibernateSenderInfo hibernateSenderInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateSenderSupport senderSupportToHibernate(SenderSupport senderSupport);

    @InheritInverseConfiguration
    SenderSupport senderSupportFromHibernate(HibernateSenderSupport hibernateSenderSupport);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "senderInfos", ignore = true)
    @Mapping(target = "notifySendRecords", ignore = true)
    @Mapping(target = "metas", ignore = true)
    @Mapping(target = "metaIndicators", ignore = true)
    @Mapping(target = "dispatcherInfo", ignore = true)
    HibernateTopic topicToHibernate(Topic topic);

    @InheritInverseConfiguration
    Topic topicFromHibernate(HibernateTopic hibernateTopic);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "sendRecords", ignore = true)
    @Mapping(target = "metas", ignore = true)
    HibernateUser userToHibernate(User user);

    @InheritInverseConfiguration
    User userFromHibernate(HibernateUser hibernateUser);

    @Mapping(target = "notifySettingLongId", ignore = true)
    @Mapping(target = "notifySetting", ignore = true)
    @Mapping(target = "notifySendRecords", ignore = true)
    @Mapping(target = "notifyInfoRecords", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateNotifyHistory notifyHistoryToHibernate(NotifyHistory notifyHistory);

    @InheritInverseConfiguration
    NotifyHistory notifyHistoryFromHibernate(HibernateNotifyHistory hibernateNotifyHistory);

    @Mapping(target = "recordId", ignore = true)
    @Mapping(target = "notifyHistoryId", ignore = true)
    @Mapping(target = "notifyHistory", ignore = true)
    HibernateNotifyInfoRecord notifyInfoRecordToHibernate(NotifyInfoRecord notifyInfoRecord);

    @InheritInverseConfiguration
    NotifyInfoRecord notifyInfoRecordFromHibernate(HibernateNotifyInfoRecord hibernateNotifyInfoRecord);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "topicId", ignore = true)
    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "notifyHistoryId", ignore = true)
    @Mapping(target = "notifyHistory", ignore = true)
    HibernateNotifySendRecord notifySendRecordToHibernate(NotifySendRecord notifySendRecord);

    @InheritInverseConfiguration
    NotifySendRecord notifySendRecordFromHibernate(HibernateNotifySendRecord hibernateNotifySendRecord);
}
