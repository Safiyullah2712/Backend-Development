package com.student.management.cache;

import java.time.LocalDateTime;

public class CacheEntry<T> {

    private T data;
    private LocalDateTime createdAt;

    public CacheEntry(
            T data,
            LocalDateTime createdAt) {

        this.data = data;
        this.createdAt = createdAt;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}