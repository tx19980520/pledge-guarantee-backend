package wd.pledge.guarantee.entity;

/*
    INWAREHOUSING: 入库中
    INWAREHOUSED: 已入库
    EXWAREHOUSING: 可出库
    EXWAREHOUSED: 已出库
 */
public enum LogicalState {
    INWAREHOUSING, INWAREHOUSED, EXWAREHOUSING, EXWAREHOUSED;
}
