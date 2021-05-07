package com.demo.websocket.practice;

import java.util.concurrent.Semaphore;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/10/23 19:13:19:13
 * @Description:
 */
public class Practice4Semaphore {

    private static final int MAX_AVAILABLE = 100;

    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    protected Object[] items = new Object[MAX_AVAILABLE];

    protected boolean[] used = new boolean[MAX_AVAILABLE];

    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object item) {
        if (markAsUnused(item)) {
            available.release();
        }

    }

    private boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null;
    }

}
