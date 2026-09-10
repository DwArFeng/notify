package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.impl.bean.entity.HibernateTopic;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;
import com.dwarfeng.notify.stack.service.TopicMaintainService;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TopicPresetCriteriaMaker implements PresetCriteriaMaker<HibernateTopic> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateTopic> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case TopicMaintainService.ID_LIKE:
                idLike(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case TopicMaintainService.LABEL_LIKE:
                labelLike(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case TopicMaintainService.ENABLED:
                enabled(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case TopicMaintainService.ENABLED_SORTED:
                enabledSorted(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException(ImplMessages.message(ImplMessageKey.ERROR_UNRECOGNIZED_PRESET, s));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void idLike(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateTopic> root,
            Object[] objects
    ) {
        try {
            String pattern = (String) objects[0];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder,
                    criteriaQuery,
                    criteriaBuilder.like(root.get("stringId"), "%" + pattern + "%")
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void labelLike(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateTopic> root,
            Object[] objects
    ) {
        try {
            String pattern = (String) objects[0];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder,
                    criteriaQuery,
                    criteriaBuilder.like(root.get("label"), "%" + pattern + "%")
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }

    private void enabled(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateTopic> root,
            Object[] objects
    ) {
        try {
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.equal(root.get("enabled"), true)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }

    private void enabledSorted(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateTopic> root,
            Object[] objects
    ) {
        try {
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.equal(root.get("enabled"), true)
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.desc(root.get("priority")));
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }
}
