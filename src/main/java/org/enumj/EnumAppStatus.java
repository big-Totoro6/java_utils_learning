package org.enumj;

public enum EnumAppStatus {
    UnApproved(0),Approved(1);

    private final Integer value;

    EnumAppStatus(Integer value) {
        this.value = value;
    }
}
