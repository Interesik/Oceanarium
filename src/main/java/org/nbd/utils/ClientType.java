package org.nbd.utils;

public enum ClientType {

    NORMAL,
    STUDENT,
    SENIOR;

    @Override
    public String toString() {
        switch (this) {
            case STUDENT -> {
                return "student";
            }
            case SENIOR -> {
                return "charity";
            }
            default -> {
                return "normal";
            }
        }
    }
}
