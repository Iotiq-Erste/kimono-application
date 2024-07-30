package com.iotiq.application.messages.customer.contact;

import lombok.Getter;

@Getter
public enum Salutation {
    MR("Mr."),
    MS("Ms."),
    MRS("Mrs."),
    MISS("Miss"),
    DR("Dr."),
    PROF("Prof."),
    DEAR("Dear");

    private final String displayName;

    Salutation(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
