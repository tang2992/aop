package com.coffee.core.db;

import java.net.UnknownHostException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.coffee.core.entity.Track;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDBAdapter implements DBInterface {

    private static MongoDBAdapter adapter = null;

    private static Mongo mongo = null;

    private static DB db = null;

    private static final Lock lock = new ReentrantLock();

    private MongoDBAdapter() {
        try {
            mongo = new Mongo("127.0.0.1", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        db = mongo.getDB("track_logs");
    }

    public static MongoDBAdapter getInstance() {
        lock.lock();
        try {
            if (adapter == null) {
                adapter = new MongoDBAdapter();
            }
        } finally {
            lock.unlock();
        }
        return adapter;
    }

    public void save(Track track) {
        DBCollection coll = db.getCollection("test_log");
        BasicDBObjectBuilder b = BasicDBObjectBuilder.start();
        b.append("sid", track.getSid());
        b.append("uid", track.getUid());
        b.append("startTime", track.getStartTime());
        b.append("endTime", track.getEndTime());
        b.append("process", track.getProcess());
        b.append("thread", track.getThread());
        b.append("className", track.getClassName());
        b.append("methodName", track.getMethodName());
        b.append("param", track.getStrParam());
        b.append("length", track.getEndTime() - track.getStartTime());
        try {
            coll.insert(b.get());
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
