package com.coffee.core.db;

public class DBFactory {

    public static DBInterface getConsoleInstance() {
        return ConsoleAdapter.getInstance();
    }

    public static DBInterface getMongoInstance() {
        return MongoDBAdapter.getInstance();
    }
}
