package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.stack.service.NotifySendRecordMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class NotifySendRecordPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case NotifySendRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY:
                childForNotifyHistory(criteria, objs);
                break;
            case NotifySendRecordMaintainService.CHILD_FOR_TOPIC:
                childForTopic(criteria, objs);
                break;
            case NotifySendRecordMaintainService.CHILD_FOR_USER:
                childForUser(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNotifyHistory(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("notifyHistoryId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("notifyHistoryId", longIdKey.getLongId()));
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
}
