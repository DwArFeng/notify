package com.dwarfeng.notify.impl.dao.preset;

import com.dwarfeng.notify.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.notify.impl.internal.i18n.ImplMessages;

import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Jakarta Criteria 查询配置辅助类。
 *
 * <p>该类保持旧 Hibernate Criteria 逐项追加查询条件和排序的语义。</p>
 *
 * @author DwArFeng
 * @since 4.0.0
 */
final class CriteriaQueryHelper {

    public static void addRestriction(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Predicate predicate
    ) {
        Predicate restriction = criteriaQuery.getRestriction();
        criteriaQuery.where(Objects.isNull(restriction) ? predicate : criteriaBuilder.and(restriction, predicate));
    }

    public static Predicate equalOrIsNull(
            CriteriaBuilder criteriaBuilder, Expression<?> expression, Object value
    ) {
        return Objects.isNull(value) ? criteriaBuilder.isNull(expression) : criteriaBuilder.equal(expression, value);
    }

    public static void addOrder(CriteriaQuery<?> criteriaQuery, Order order) {
        List<Order> orders = new ArrayList<>(criteriaQuery.getOrderList());
        orders.add(order);
        criteriaQuery.orderBy(orders);
    }

    private CriteriaQueryHelper() {
        throw new IllegalStateException(ImplMessages.message(ImplMessageKey.ERROR_EXTERNAL_INSTANTIATION_PROHIBITED));
    }
}
