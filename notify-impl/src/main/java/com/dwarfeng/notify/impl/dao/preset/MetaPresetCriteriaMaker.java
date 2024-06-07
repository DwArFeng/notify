package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.stack.service.MetaMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class MetaPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case MetaMaintainService.CHILD_FOR_NOTIFY_SETTING:
                childForNotifySetting(detachedCriteria, objects);
                break;
            case MetaMaintainService.CHILD_FOR_TOPIC:
                childForTopic(detachedCriteria, objects);
                break;
            case MetaMaintainService.CHILD_FOR_USER:
                childForUser(detachedCriteria, objects);
                break;
            case MetaMaintainService.CHILD_FOR_NOTIFY_SETTING_TOPIC_USER_META_ID_ASC:
                childForNotifySettingTopicUserMetaIdAsc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNotifySetting(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("notifySettingId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("notifySettingId", longIdKey.getLongId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForTopic(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("topicId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("topicId", stringIdKey.getStringId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUser(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("userId", stringIdKey.getStringId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNotifySettingTopicUserMetaIdAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("notifySettingId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("notifySettingId", longIdKey.getLongId()));
            }
            if (Objects.isNull(objects[1])) {
                detachedCriteria.add(Restrictions.isNull("topicId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[1];
                detachedCriteria.add(Restrictions.eqOrIsNull("topicId", stringIdKey.getStringId()));
            }
            if (Objects.isNull(objects[2])) {
                detachedCriteria.add(Restrictions.isNull("userId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[2];
                detachedCriteria.add(Restrictions.eqOrIsNull("userId", stringIdKey.getStringId()));
            }
            detachedCriteria.addOrder(Order.asc("metaId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
