package com.fadlia.fundamentals;

import com.fadlia.fundamentals.algo.SortingAlgorithms;
import com.fadlia.fundamentals.domain.CapacityExceededException;
import com.fadlia.fundamentals.domain.ResourceRegistry;
import com.fadlia.fundamentals.domain.ResourceRegistry.MediaItem;
import com.fadlia.fundamentals.ds.DoublyLinkedList;
import com.fadlia.fundamentals.ds.DynamicArray;

import java.util.Arrays;

/**
 * Zero-dependency automated test runner verifying all Java data structures,
 * sorting/searching algorithms, and OOP exception invariants.
 */
public final class TestRunner {
    private static int passed = 0;

    private static void check(boolean condition, String name) {
        if (!condition) {
            throw new AssertionError("FAILED: " + name);
        }
        passed++;
        System.out.println("  [PASS] " + name);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Running Java CS Fundamentals Test Suite ===");

        // 1. DynamicArray growth, indexing, removal
        DynamicArray<Integer> arr = new DynamicArray<>(2);
        for (int i = 0; i < 10; i++) {
            arr.add(i * 10);
        }
        check(arr.size() == 10 && arr.capacity() >= 10, "DynamicArray auto-resize");
        check(arr.removeAt(2) == 20 && arr.get(2) == 30 && arr.size() == 9, "DynamicArray removeAt shift");

        // 2. DoublyLinkedList O(1) endpoints
        DoublyLinkedList<String> dll = new DoublyLinkedList<>();
        dll.addFirst("B");
        dll.addFirst("A");
        dll.addLast("C");
        check("A".equals(dll.removeFirst()) && "C".equals(dll.removeLast()) && dll.size() == 1, "DoublyLinkedList endpoints");

        // 3. MergeSort, QuickSort & BinarySearch
        int[] sample1 = {42, -7, 19, 0, 19, 105, -23, 8};
        int[] sample2 = Arrays.copyOf(sample1, sample1.length);
        SortingAlgorithms.mergeSort(sample1);
        SortingAlgorithms.quickSort(sample2);
        check(SortingAlgorithms.isSorted(sample1) && Arrays.equals(sample1, sample2), "MergeSort & QuickSort equivalence");
        check(SortingAlgorithms.binarySearch(sample1, 19) >= 0, "BinarySearch finds existing target");
        check(SortingAlgorithms.binarySearch(sample1, 999) == -1, "BinarySearch returns -1 for missing target");

        // 4. OOP Domain & Checked Exceptions
        ResourceRegistry registry = new ResourceRegistry("Heilbronn Tech Collection", 50);
        registry.register(new MediaItem("978-0134685991", "Effective Java", 30));
        registry.register(new MediaItem("978-0262046305", "CLRS Algorithms", 20));
        check(Math.abs(registry.utilizationRatio() - 1.0) < 1e-9, "ResourceRegistry utilization tracking");

        boolean caughtCapacity = false;
        try {
            registry.register(new MediaItem("978-0131103627", "The C Programming Language", 1));
        } catch (CapacityExceededException expected) {
            caughtCapacity = true;
        }
        check(caughtCapacity, "ResourceRegistry enforces CapacityExceededException");

        System.out.println("=== All " + passed + " Java tests PASSED ===");
    }
}
