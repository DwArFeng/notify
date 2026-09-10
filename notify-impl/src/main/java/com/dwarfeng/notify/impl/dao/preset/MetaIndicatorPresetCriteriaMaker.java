package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.impl.bean.entity.HibernateMetaIndicator;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;
import com.dwarfeng.notify.stack.service.MetaIndicatorMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class MetaIndicatorPresetCriteriaMaker implements PresetCriteriaMaker<HibernateMetaIndicator> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateMetaIndicator> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case MetaIndicatorMaintainService.CHILD_FOR_TOPIC:
                childForTopic(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case MetaIndicatorMaintainService.CHILD_FOR_TOPIC_META_ID_ASC:
                childForTopicMetaIdAsc(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException(ImplMessages.message(ImplMessageKey.ERROR_UNRECOGNIZED_PRESET, s));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForTopic(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateMetaIndicator> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("topicId"))
                );
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("topicId"), stringIdKey.getStringId()
                        )
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }

    private void childForTopicMetaIdAsc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateMetaIndicator> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("topicId"))
                );
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("topicId"), stringIdKey.getStringId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("metaId")));
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    ImplMessages.message(ImplMessageKey.ERROR_ILLEGAL_ARGUMENT, Arrays.toString(objects))
            );
        }
    }
}
