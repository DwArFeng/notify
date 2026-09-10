package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.impl.bean.entity.HibernateNotifyInfoRecord;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;
import com.dwarfeng.notify.stack.service.NotifyInfoRecordMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class NotifyInfoRecordPresetCriteriaMaker implements PresetCriteriaMaker<HibernateNotifyInfoRecord> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifyInfoRecord> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case NotifyInfoRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY:
                childForNotifyHistory(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NotifyInfoRecordMaintainService.CHILD_FOR_NOTIFY_HISTORY_ORDERED:
                childForNotifyHistoryOrdered(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException(ImplMessages.message(ImplMessageKey.ERROR_UNRECOGNIZED_PRESET, s));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNotifyHistory(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifyInfoRecord> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("notifyHistoryId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("notifyHistoryId"), longIdKey.getLongId()
                        )
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }

    private void childForNotifyHistoryOrdered(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifyInfoRecord> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("notifyHistoryId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("notifyHistoryId"), longIdKey.getLongId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("type")));
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("recordId")));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }
}
