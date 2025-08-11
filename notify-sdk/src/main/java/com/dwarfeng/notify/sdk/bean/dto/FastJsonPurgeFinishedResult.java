package com.dwarfeng.notify.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.notify.stack.bean.dto.PurgeFinishedResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 清除结束结果。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public class FastJsonPurgeFinishedResult implements Dto {

    private static final long serialVersionUID = -5893315369131259424L;

    public static FastJsonPurgeFinishedResult of(PurgeFinishedResult purgeFinishResult) {
        if (Objects.isNull(purgeFinishResult)) {
            return null;
        } else {
            return new FastJsonPurgeFinishedResult(
                    purgeFinishResult.getNotifyHistoryDeletionCount(),
                    purgeFinishResult.isNotifyHistoryDivergent()
            );
        }
    }

    @JSONField(name = "notify_history_deletion_count", ordinal = 1)
    private int notifyHistoryDeletionCount;

    @JSONField(name = "notify_history_divergent", ordinal = 2)
    private boolean notifyHistoryDivergent;

    public FastJsonPurgeFinishedResult() {
    }

    public FastJsonPurgeFinishedResult(int notifyHistoryDeletionCount, boolean notifyHistoryDivergent) {
        this.notifyHistoryDeletionCount = notifyHistoryDeletionCount;
        this.notifyHistoryDivergent = notifyHistoryDivergent;
    }

    public int getNotifyHistoryDeletionCount() {
        return notifyHistoryDeletionCount;
    }

    public void setNotifyHistoryDeletionCount(int notifyHistoryDeletionCount) {
        this.notifyHistoryDeletionCount = notifyHistoryDeletionCount;
    }

    public boolean isNotifyHistoryDivergent() {
        return notifyHistoryDivergent;
    }

    public void setNotifyHistoryDivergent(boolean notifyHistoryDivergent) {
        this.notifyHistoryDivergent = notifyHistoryDivergent;
    }

    @Override
    public String toString() {
        return "FastJsonPurgeFinishedResult{" +
                "notifyHistoryDeletionCount=" + notifyHistoryDeletionCount +
                ", notifyHistoryDivergent=" + notifyHistoryDivergent +
                '}';
    }
}
