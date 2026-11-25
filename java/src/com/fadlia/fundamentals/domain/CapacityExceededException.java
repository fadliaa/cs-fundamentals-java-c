package com.fadlia.fundamentals.domain;

/** Checked exception raised when a resource exceeds configured capacity limits. */
public class CapacityExceededException extends Exception {
    public CapacityExceededException(String message) {
        super(message);
    }
}
