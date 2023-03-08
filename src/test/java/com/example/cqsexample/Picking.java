package com.example.cqsexample;

public class Picking {
    private final Long lpnId;
    private final int allocatedQuantity;

    public Picking(final Long lpnId, final int allocateQuantity) {
        this.lpnId = lpnId;
        allocatedQuantity = allocateQuantity;
    }

    public Long getLpnId() {
        return lpnId;
    }

    public int getAllocatedQuantity() {
        return allocatedQuantity;
    }

    @Override
    public String toString() {
        return "Picking{" +
                "lpnId=" + lpnId +
                ", allocatedQuantity=" + allocatedQuantity +
                '}';
    }
}
