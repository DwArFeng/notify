package com.dwarfeng.notify.sdk.bean;

import com.dwarfeng.notify.sdk.bean.dto.FastJsonNotifyHistoryRecordInfo;
import com.dwarfeng.notify.sdk.bean.dto.FastJsonPurgeFinishedResult;
import com.dwarfeng.notify.sdk.bean.dto.JSFixedFastJsonNotifyHistoryRecordInfo;
import com.dwarfeng.notify.sdk.bean.dto.WebInputNotifyInfo;
import com.dwarfeng.notify.sdk.bean.entity.*;
import com.dwarfeng.notify.sdk.bean.key.*;
import com.dwarfeng.notify.stack.bean.dto.NotifyHistoryRecordInfo;
import com.dwarfeng.notify.stack.bean.dto.NotifyInfo;
import com.dwarfeng.notify.stack.bean.dto.PurgeFinishedResult;
import com.dwarfeng.notify.stack.bean.entity.*;
import com.dwarfeng.notify.stack.bean.key.*;
import com.dwarfeng.subgrade.sdk.bean.key.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>sdk</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    JSFixedFastJsonLongIdKey longIdKeyToJSFixedFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromJSFixedFastJson(JSFixedFastJsonLongIdKey jSFixedFastJsonLongIdKey);

    WebInputLongIdKey longIdKeyToWebInput(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromWebInput(WebInputLongIdKey webInputLongIdKey);

    WebInputStringIdKey stringIdKeyToWebInput(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromWebInput(WebInputStringIdKey webInputStringIdKey);

    // -----------------------------------------------------------Notify Key-----------------------------------------------------------
    FastJsonMetaIndicatorKey metaIndicatorKeyToFastJson(MetaIndicatorKey metaIndicatorKey);

    @InheritInverseConfiguration
    MetaIndicatorKey metaIndicatorKeyFromFastJson(FastJsonMetaIndicatorKey fastJsonMetaIndicatorKey);

    FastJsonMetaKey metaKeyToFastJson(MetaKey metaKey);

    @InheritInverseConfiguration
    MetaKey metaKeyFromFastJson(FastJsonMetaKey fastJsonMetaKey);

    FastJsonNotifyInfoRecordKey notifyInfoRecordKeyToFastJson(NotifyInfoRecordKey notifyInfoRecordKey);

    @InheritInverseConfiguration
    NotifyInfoRecordKey notifyInfoRecordKeyFromFastJson(FastJsonNotifyInfoRecordKey fastJsonNotifyInfoRecordKey);

    FastJsonNotifySendRecordKey notifySendRecordKeyToFastJson(NotifySendRecordKey notifySendRecordKey);

    @InheritInverseConfiguration
    NotifySendRecordKey notifySendRecordKeyFromFastJson(FastJsonNotifySendRecordKey fastJsonNotifySendRecordKey);

    FastJsonSenderInfoKey senderInfoKeyToFastJson(SenderInfoKey senderInfoKey);

    @InheritInverseConfiguration
    SenderInfoKey senderInfoKeyFromFastJson(FastJsonSenderInfoKey fastJsonSenderInfoKey);

    JSFixedFastJsonMetaKey metaKeyToJSFixedFastJson(MetaKey metaKey);

    @InheritInverseConfiguration
    MetaKey metaKeyFromJSFixedFastJson(JSFixedFastJsonMetaKey jSFixedFastJsonMetaKey);

    JSFixedFastJsonNotifyInfoRecordKey notifyInfoRecordKeyToJSFixedFastJson(NotifyInfoRecordKey notifyInfoRecordKey);

    @InheritInverseConfiguration
    NotifyInfoRecordKey notifyInfoRecordKeyFromJSFixedFastJson(
            JSFixedFastJsonNotifyInfoRecordKey jSFixedFastJsonNotifyInfoRecordKey
    );

    JSFixedFastJsonNotifySendRecordKey notifySendRecordKeyToJSFixedFastJson(NotifySendRecordKey notifySendRecordKey);

    @InheritInverseConfiguration
    NotifySendRecordKey notifySendRecordKeyFromJSFixedFastJson(
            JSFixedFastJsonNotifySendRecordKey jSFixedFastJsonNotifySendRecordKey
    );

    JSFixedFastJsonSenderInfoKey senderInfoKeyToJSFixedFastJson(SenderInfoKey senderInfoKey);

    @InheritInverseConfiguration
    SenderInfoKey senderInfoKeyFromJSFixedFastJson(JSFixedFastJsonSenderInfoKey jSFixedFastJsonSenderInfoKey);

    WebInputMetaIndicatorKey metaIndicatorKeyToWebInput(MetaIndicatorKey metaIndicatorKey);

    @InheritInverseConfiguration
    MetaIndicatorKey metaIndicatorKeyFromWebInput(WebInputMetaIndicatorKey webInputMetaIndicatorKey);

    WebInputMetaKey metaKeyToWebInput(MetaKey metaKey);

    @InheritInverseConfiguration
    MetaKey metaKeyFromWebInput(WebInputMetaKey webInputMetaKey);

    WebInputSenderInfoKey senderInfoKeyToWebInput(SenderInfoKey senderInfoKey);

    @InheritInverseConfiguration
    SenderInfoKey senderInfoKeyFromWebInput(WebInputSenderInfoKey webInputSenderInfoKey);

    // -----------------------------------------------------------Notify Entity-----------------------------------------------------------
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

    FastJsonNotifyHistory notifyHistoryToFastJson(NotifyHistory notifyHistory);

    @InheritInverseConfiguration
    NotifyHistory notifyHistoryFromFastJson(FastJsonNotifyHistory fastJsonNotifyHistory);

    FastJsonNotifyInfoRecord notifyInfoRecordToFastJson(NotifyInfoRecord notifyInfoRecord);

    @InheritInverseConfiguration
    NotifyInfoRecord notifyInfoRecordFromFastJson(FastJsonNotifyInfoRecord fastJsonNotifyInfoRecord);

    FastJsonNotifySendRecord notifySendRecordToFastJson(NotifySendRecord notifySendRecord);

    @InheritInverseConfiguration
    NotifySendRecord notifySendRecordFromFastJson(FastJsonNotifySendRecord fastJsonNotifySendRecord);

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

    FastJsonTopic topicToFastJson(Topic topic);

    @InheritInverseConfiguration
    Topic topicFromFastJson(FastJsonTopic fastJsonTopic);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);

    JSFixedFastJsonMeta metaToJSFixedFastJson(Meta meta);

    @InheritInverseConfiguration
    Meta metaFromJSFixedFastJson(JSFixedFastJsonMeta jSFixedFastJsonMeta);

    JSFixedFastJsonNotifyHistory notifyHistoryToJSFixedFastJson(NotifyHistory notifyHistory);

    @InheritInverseConfiguration
    NotifyHistory notifyHistoryFromJSFixedFastJson(JSFixedFastJsonNotifyHistory jSFixedFastJsonNotifyHistory);

    JSFixedFastJsonNotifyInfoRecord notifyInfoRecordToJSFixedFastJson(NotifyInfoRecord notifyInfoRecord);

    @InheritInverseConfiguration
    NotifyInfoRecord notifyInfoRecordFromJSFixedFastJson(
            JSFixedFastJsonNotifyInfoRecord jSFixedFastJsonNotifyInfoRecord
    );

    JSFixedFastJsonNotifySendRecord notifySendRecordToJSFixedFastJson(NotifySendRecord notifySendRecord);

    @InheritInverseConfiguration
    NotifySendRecord notifySendRecordFromJSFixedFastJson(
            JSFixedFastJsonNotifySendRecord jSFixedFastJsonNotifySendRecord
    );

    JSFixedFastJsonNotifySetting notifySettingToJSFixedFastJson(NotifySetting notifySetting);

    @InheritInverseConfiguration
    NotifySetting notifySettingFromJSFixedFastJson(JSFixedFastJsonNotifySetting jSFixedFastJsonNotifySetting);

    JSFixedFastJsonRouterInfo routerInfoToJSFixedFastJson(RouterInfo routerInfo);

    @InheritInverseConfiguration
    RouterInfo routerInfoFromJSFixedFastJson(JSFixedFastJsonRouterInfo jSFixedFastJsonRouterInfo);

    JSFixedFastJsonSenderInfo senderInfoToJSFixedFastJson(SenderInfo senderInfo);

    @InheritInverseConfiguration
    SenderInfo senderInfoFromJSFixedFastJson(JSFixedFastJsonSenderInfo jSFixedFastJsonSenderInfo);

    WebInputDispatcherInfo dispatcherInfoToWebInput(DispatcherInfo dispatcherInfo);

    @InheritInverseConfiguration
    DispatcherInfo dispatcherInfoFromWebInput(WebInputDispatcherInfo webInputDispatcherInfo);

    WebInputMeta metaToWebInput(Meta meta);

    @InheritInverseConfiguration
    Meta metaFromWebInput(WebInputMeta webInputMeta);

    WebInputMetaIndicator metaIndicatorToWebInput(MetaIndicator metaIndicator);

    @InheritInverseConfiguration
    MetaIndicator metaIndicatorFromWebInput(WebInputMetaIndicator webInputMetaIndicator);

    WebInputNotifySetting notifySettingToWebInput(NotifySetting notifySetting);

    @InheritInverseConfiguration
    NotifySetting notifySettingFromWebInput(WebInputNotifySetting webInputNotifySetting);

    WebInputRouterInfo routerInfoToWebInput(RouterInfo routerInfo);

    @InheritInverseConfiguration
    RouterInfo routerInfoFromWebInput(WebInputRouterInfo webInputRouterInfo);

    WebInputSenderInfo senderInfoToWebInput(SenderInfo senderInfo);

    @InheritInverseConfiguration
    SenderInfo senderInfoFromWebInput(WebInputSenderInfo webInputSenderInfo);

    WebInputTopic topicToWebInput(Topic topic);

    @InheritInverseConfiguration
    Topic topicFromWebInput(WebInputTopic webInputTopic);

    WebInputUser userToWebInput(User user);

    @InheritInverseConfiguration
    User userFromWebInput(WebInputUser webInputUser);

    // -----------------------------------------------------------Notify DTO-----------------------------------------------------------
    FastJsonNotifyHistoryRecordInfo notifyHistoryRecordInfoToFastJson(NotifyHistoryRecordInfo notifyHistoryRecordInfo);

    @InheritInverseConfiguration
    NotifyHistoryRecordInfo notifyHistoryRecordInfoFromFastJson(
            FastJsonNotifyHistoryRecordInfo fastJsonNotifyHistoryRecordInfo
    );

    FastJsonPurgeFinishedResult purgeFinishedResultToFastJson(PurgeFinishedResult purgeFinishedResult);

    @InheritInverseConfiguration
    PurgeFinishedResult purgeFinishedResultFromFastJson(FastJsonPurgeFinishedResult fastJsonPurgeFinishedResult);

    JSFixedFastJsonNotifyHistoryRecordInfo notifyHistoryRecordInfoToJSFixedFastJson(
            NotifyHistoryRecordInfo notifyHistoryRecordInfo
    );

    @InheritInverseConfiguration
    NotifyHistoryRecordInfo notifyHistoryRecordInfoFromJSFixedFastJson(
            JSFixedFastJsonNotifyHistoryRecordInfo jSFixedFastJsonNotifyHistoryRecordInfo
    );

    WebInputNotifyInfo notifyInfoToWebInput(NotifyInfo notifyInfo);

    @InheritInverseConfiguration
    NotifyInfo notifyInfoFromWebInput(WebInputNotifyInfo webInputNotifyInfo);
}
