package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.impl.bean.entity.HibernateNotifyHistory;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;
import com.dwarfeng.notify.stack.service.NotifyHistoryMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Component
public class NotifyHistoryPresetCriteriaMaker implements PresetCriteriaMaker<HibernateNotifyHistory> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifyHistory> root,
            String preset, Object[] objs
    ) {
        switch (preset) {
            case NotifyHistoryMaintainService.CHILD_FOR_NOTIFY_SETTING:
                childForNotifySetting(criteriaBuilder, criteriaQuery, root, objs);
                break;
            case NotifyHistoryMaintainService.HAPPENED_DATE_DESC:
                happenedDateDesc(criteriaBuilder, criteriaQuery, root, objs);
                break;
            case NotifyHistoryMaintainService.CHILD_FOR_NOTIFY_SETTING_HAPPENED_DATE_DESC:
                childForNotifySettingHappenedDateDesc(criteriaBuilder, criteriaQuery, root, objs);
                break;
            case NotifyHistoryMaintainService.TO_PURGED:
                toPurged(criteriaBuilder, criteriaQuery, root, objs);
                break;
            default:
                throw new IllegalArgumentException(
                        ImplMessages.message(ImplMessageKey.ERROR_UNRECOGNIZED_PRESET, preset)
                );
        }
    }

    private void childForNotifySetting(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifyHistory> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("notifySettingLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("notifySettingLongId"), longIdKey.getLongId()
                        )
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }

    private void happenedDateDesc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifyHistory> root,
            Object[] objects
    ) {
        try {
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.desc(root.get("happenedDate")));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }

    private void childForNotifySettingHappenedDateDesc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifyHistory> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("notifySettingLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("notifySettingLongId"), longIdKey.getLongId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.desc(root.get("happenedDate")));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }

    private void toPurged(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifyHistory> root,
            Object[] objs
    ) {
        try {
            Date date = (Date) objs[0];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.lessThan(root.get("happenedDate"), date)
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("happenedDate")));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objs))
            );
        }
    }
}
