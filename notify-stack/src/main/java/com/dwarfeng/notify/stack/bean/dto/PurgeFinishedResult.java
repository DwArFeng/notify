package com.dwarfeng.notify.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 清除结束结果。
 *
 * @author DwArFeng
 * @since 1.6.0
 */
public class PurgeFinishedResult implements Dto {

    @Serial
    private static final long serialVersionUID = 6611130777195797830L;

    /**
     * 通知历史实体的删除数量。
     */
    private int notifyHistoryDeletionCount;

    /**
     * 通知历史是否发散。
     *
     * <p>
     * 发散的意思是，在任务执行间隔中，生成的实体数量大于清除的数量。<br>
     * 如果不处理发散情况，会导致数据的积压。
     */
    private boolean notifyHistoryDivergent;

    public PurgeFinishedResult() {
    }

    public PurgeFinishedResult(int notifyHistoryDeletionCount, boolean notifyHistoryDivergent) {
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
        return "PurgeFinishedResult{" +
                "notifyHistoryDeletionCount=" + notifyHistoryDeletionCount +
                ", notifyHistoryDivergent=" + notifyHistoryDivergent +
                '}';
    }
}
