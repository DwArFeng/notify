package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.impl.bean.entity.HibernateNotifySetting;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;
import com.dwarfeng.notify.stack.service.NotifySettingMaintainService;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NotifySettingPresetCriteriaMaker implements PresetCriteriaMaker<HibernateNotifySetting> {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifySetting> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case NotifySettingMaintainService.LABEL_LIKE:
                labelLike(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException(ImplMessages.message(ImplMessageKey.ERROR_UNRECOGNIZED_PRESET, s));
        }
    }

    private void labelLike(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNotifySetting> root,
            Object[] objects
    ) {
        try {
            String pattern = (String) objects[0];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder,
                    criteriaQuery,
                    criteriaBuilder.like(root.get("label"), "%" + pattern + "%")
            );
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }
}
