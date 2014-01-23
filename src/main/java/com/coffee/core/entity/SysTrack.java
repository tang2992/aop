package com.coffee.core.entity;

import java.io.Serializable;

public class SysTrack implements Serializable {

    private static final long serialVersionUID = -8266318551798086964L;

    // 系统ID
    private String sid;
    // 业务ID
    private int uid = 0;
    // 开始时间
    private long startTime = 0;

    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid
     *            the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("sid:" + sid + "\t");
        builder.append("uid:" + uid + "\t");
        builder.append("startTime:" + startTime);
        return builder.toString();
    }
}
