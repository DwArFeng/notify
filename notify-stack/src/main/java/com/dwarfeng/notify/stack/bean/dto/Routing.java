package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Objects;

/**
 * 路径。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Routing implements Dto {

    private static final long serialVersionUID = 5174914869380276516L;

    private StringIdKey topicKey;
    private StringIdKey userKey;

    public Routing() {
    }

    public Routing(StringIdKey topicKey, StringIdKey userKey) {
        this.topicKey = topicKey;
        this.userKey = userKey;
    }

    public StringIdKey getTopicKey() {
        return topicKey;
    }

    public void setTopicKey(StringIdKey topicKey) {
        this.topicKey = topicKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Routing routing = (Routing) o;

        if (!Objects.equals(topicKey, routing.topicKey)) return false;
        return Objects.equals(userKey, routing.userKey);
    }

    @Override
    public int hashCode() {
        int result = topicKey != null ? topicKey.hashCode() : 0;
        result = 31 * result + (userKey != null ? userKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Routing{" +
                "topicKey=" + topicKey +
                ", userKey=" + userKey +
                '}';
    }
}
