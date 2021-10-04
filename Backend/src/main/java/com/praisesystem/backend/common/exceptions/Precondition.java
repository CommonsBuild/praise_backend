package com.praisesystem.backend.common.exceptions;

public class Precondition {
    public static void ifTrueThrow(boolean expression, RuntimeException e) {
        if (expression) {
            throw e;
        }
    }

    public static void ifFalseThrow(boolean expression, RuntimeException e) {
        if (!expression) {
            throw e;
        }
    }
}
