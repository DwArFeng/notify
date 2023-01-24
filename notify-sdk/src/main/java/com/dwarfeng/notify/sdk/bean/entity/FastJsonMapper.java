package com.dwarfeng.notify.sdk.bean.entity;

import com.dwarfeng.notify.sdk.bean.key.FastJsonMetaIndicatorKey;
import com.dwarfeng.notify.sdk.bean.key.FastJsonMetaKey;
import com.dwarfeng.notify.sdk.bean.key.FastJsonRecordKey;
import com.dwarfeng.notify.sdk.bean.key.FastJsonSenderInfoKey;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.key.MetaIndicatorKey;
import com.dwarfeng.notify.stack.bean.key.MetaKey;
import com.dwarfeng.notify.stack.bean.key.RecordKey;
import com.dwarfeng.notify.stack.bean.key.SenderInfoKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonRecordKey recordKeyToFastJson(RecordKey recordKey);

    @InheritInverseConfiguration
    RecordKey recordKeyFromFastJson(FastJsonRecordKey fastJsonRecordKey);

    FastJsonMetaIndicatorKey metaIndicatorKeyToFastJson(MetaIndicatorKey metaIndicatorKey);

    @InheritInverseConfiguration
    MetaIndicatorKey metaIndicatorKeyFromFastJson(FastJsonMetaIndicatorKey fastJsonMetaIndicatorKey);

    FastJsonMetaKey metaKeyToFastJson(MetaKey metaKey);

    @InheritInverseConfiguration
    MetaKey metaKeyFromFastJson(FastJsonMetaKey fastJsonMetaKey);

    FastJsonSenderInfoKey senderInfoKeyToFastJson(SenderInfoKey senderInfoKey);

    @InheritInverseConfiguration
    SenderInfoKey senderInfoKeyFromFastJson(FastJsonSenderInfoKey fastJsonSenderInfoKey);

    FastJsonDispatcherInfo dispatcherInfoToFastJson(DispatcherInfo dispatcherInfo);

    @InheritInverseConfiguration
    DispatcherInfo dispatcherInfoFromFastJson(FastJsonDispatcherInfo fastJsonDispatcherInfo);

    FastJsonDispatcherSupport dispatcherSupportToFastJson(DispatcherSupport dispatcherSupport);

    @InheritInverseConfiguration
    DispatcherSupport dispatcherSupportFromFastJson(FastJsonDispatcherSupport fastJsonDispatcherSupport);

    FastJsonMeta metaToFastJson(Meta meta);

    @InheritInverseConfiguration
    Meta metaFromFastJson(FastJsonMeta fastJsonMeta);

    FastJsonMetaIndicator metaIndicatorToFastJson(MetaIndicator metaIndicator);

    @InheritInverseConfiguration
    MetaIndicator metaIndicatorFromFastJson(FastJsonMetaIndicator fastJsonMetaIndicator);

    FastJsonNotifySetting notifySettingToFastJson(NotifySetting notifySetting);

    @InheritInverseConfiguration
    NotifySetting notifySettingFromFastJson(FastJsonNotifySetting fastJsonNotifySetting);

    FastJsonRouterInfo routerInfoToFastJson(RouterInfo routerInfo);

    @InheritInverseConfiguration
    RouterInfo routerInfoFromFastJson(FastJsonRouterInfo fastJsonRouterInfo);

    FastJsonRouterSupport routerSupportToFastJson(RouterSupport routerSupport);

    @InheritInverseConfiguration
    RouterSupport routerSupportFromFastJson(FastJsonRouterSupport fastJsonRouterSupport);

    FastJsonSenderInfo senderInfoToFastJson(SenderInfo senderInfo);

    @InheritInverseConfiguration
    SenderInfo senderInfoFromFastJson(FastJsonSenderInfo fastJsonSenderInfo);

    FastJsonSenderSupport senderSupportToFastJson(SenderSupport senderSupport);

    @InheritInverseConfiguration
    SenderSupport senderSupportFromFastJson(FastJsonSenderSupport fastJsonSenderSupport);

    FastJsonSendHistory sendHistoryToFastJson(SendHistory sendHistory);

    @InheritInverseConfiguration
    SendHistory sendHistoryFromFastJson(FastJsonSendHistory fastJsonSendHistory);

    FastJsonTopic topicToFastJson(Topic topic);

    @InheritInverseConfiguration
    Topic topicFromFastJson(FastJsonTopic fastJsonTopic);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);

    FastJsonNotifyHistory notifyHistoryToFastJson(NotifyHistory notifyHistory);

    @InheritInverseConfiguration
    NotifyHistory notifyHistoryFromFastJson(FastJsonNotifyHistory fastJsonNotifyHistory);

    FastJsonNotifyInfoRecord notifyInfoRecordToFastJson(NotifyInfoRecord notifyInfoRecord);

    @InheritInverseConfiguration
    NotifyInfoRecord notifyInfoRecordFromFastJson(FastJsonNotifyInfoRecord fastJsonNotifyInfoRecord);

    FastJsonNotifySendRecord notifySendRecordToFastJson(NotifySendRecord notifySendRecord);

    @InheritInverseConfiguration
    NotifySendRecord notifySendRecordFromFastJson(FastJsonNotifySendRecord fastJsonNotifySendRecord);
}
