package com.coffee.core.db;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.coffee.core.entity.Track;

public class ConsoleAdapter implements DBInterface {

    private static ConsoleAdapter adapter = null;

    private static final Lock lock = new ReentrantLock();

    private ConsoleAdapter() {
    }

    public static ConsoleAdapter getInstance() {
        lock.lock();
        try {
            if (adapter == null) {
                adapter = new ConsoleAdapter();
            }
        } finally {
            lock.unlock();
        }
        return adapter;
    }

    public void save(Track track) {
        System.out.println("Track:" + track.toString());
    }

}
