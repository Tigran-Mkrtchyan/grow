package com.grow.kernel.repository.sort;

import java.util.Arrays;
import java.util.Optional;

public enum SortDirection {
    ASC("asc"),
    DESC("desc");
    private final String displayName;

    SortDirection(String displayName) {
        this.displayName = displayName;
    }

    public static Optional<SortDirection> fromString(String value) {
        return Arrays.stream(SortDirection.values())
                .filter(direction -> direction.displayName.equalsIgnoreCase(value))
                .findAny();
    }
}
