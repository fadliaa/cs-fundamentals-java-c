package com.fadlia.fundamentals.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Original OOP domain model demonstrating encapsulation, defensive copying,
 * immutability (Java record), and checked exception handling.
 */
public class ResourceRegistry {
    public record MediaItem(String isbn, String title, int copies) {
        public MediaItem {
            Objects.requireNonNull(isbn, "ISBN must not be null");
            Objects.requireNonNull(title, "Title must not be null");
            if (isbn.isBlank() || title.isBlank()) {
                throw new IllegalArgumentException("ISBN and title must not be blank.");
            }
            if (copies < 0) {
                throw new IllegalArgumentException("Copies cannot be negative.");
            }
        }
    }

    private final String facilityName;
    private final int maxTotalCopies;
    private final Map<String, MediaItem> catalog = new LinkedHashMap<>();
    private int currentTotalCopies = 0;

    public ResourceRegistry(String facilityName, int maxTotalCopies) {
        if (facilityName == null || facilityName.isBlank()) {
            throw new IllegalArgumentException("Facility name must not be blank.");
        }
        if (maxTotalCopies <= 0) {
            throw new IllegalArgumentException("Maximum capacity must be positive.");
        }
        this.facilityName = facilityName;
        this.maxTotalCopies = maxTotalCopies;
    }

    public void register(MediaItem item) throws CapacityExceededException {
        Objects.requireNonNull(item, "MediaItem must not be null");
        if (catalog.containsKey(item.isbn())) {
            throw new IllegalStateException("Duplicate ISBN in registry: " + item.isbn());
        }
        if (currentTotalCopies + item.copies() > maxTotalCopies) {
            throw new CapacityExceededException(
                "Adding " + item.copies() + " copies exceeds capacity (" + maxTotalCopies + ")."
            );
        }
        catalog.put(item.isbn(), item);
        currentTotalCopies += item.copies();
    }

    public double utilizationRatio() {
        return (double) currentTotalCopies / maxTotalCopies;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public int getTotalCopies() {
        return currentTotalCopies;
    }

    public Map<String, MediaItem> snapshot() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(catalog));
    }
}
