package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.stack.service.NotifyInfoRecordMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class NotifyInfoRecordPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case NotifyInfoRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY:
                childForNotifyHistory(detachedCriteria, objects);
                break;
            case NotifyInfoRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY_ORDERED:
                childForNotifyHistoryOrdered(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
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

    private void childForNotifyHistoryOrdered(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("notifyHistoryId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("notifyHistoryId", longIdKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.asc("type"));
            detachedCriteria.addOrder(Order.asc("recordId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
