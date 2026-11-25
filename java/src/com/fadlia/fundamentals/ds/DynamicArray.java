package com.fadlia.fundamentals.ds;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic resizable array implementation demonstrating amortized O(1) append,
 * bounds validation, and automatic capacity doubling.
 */
public class DynamicArray<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 8;

    private Object[] elements;
    private int size;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    public void add(T item) {
        ensureCapacity(size + 1);
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkBounds(index);
        return (T) elements[index];
    }

    public T set(int index, T item) {
        checkBounds(index);
        T previous = get(index);
        elements[index] = item;
        return previous;
    }

    public T removeAt(int index) {
        checkBounds(index);
        T removed = get(index);
        int moveCount = size - index - 1;
        if (moveCount > 0) {
            System.arraycopy(elements, index + 1, elements, index, moveCount);
        }
        elements[--size] = null;
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int capacity() {
        return elements.length;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) {
            return;
        }
        int nextCapacity = Math.max(elements.length * 2, minCapacity);
        elements = Arrays.copyOf(elements, nextCapacity);
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(cursor++);
            }
        };
    }
}
