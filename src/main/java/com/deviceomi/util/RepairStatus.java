package com.deviceomi.util;

public enum RepairStatus {
    REPAIR(1),//sua chua
    UPGRADE(2),//nang cap
    GUARANTEE(3),//bao hanh
    PENDING(4),//GUI
    DONE(5);//XONG

    public int value;

    private RepairStatus(int value) {
        this.value = value;
    }
}
