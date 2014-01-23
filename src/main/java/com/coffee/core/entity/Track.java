package com.coffee.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class Track implements Serializable {

    private static final long serialVersionUID = 8744731815243461903L;

    // 系统ID
    private String sid;
    // 业务ID
    private int uid = 0;
    // 开始时间
    private long startTime = 0;
    // 结束时间
    private long endTime = 0;
    // 进程
    private String process;
    // 线程
    private String thread;
    // 类名
    private String className;
    // 方法名
    private String methodName;
    // 参数
    private transient List<Object> param = new ArrayList<Object>();
    // 参数字符串化
    private String strParam;

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

    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the process
     */
    public String getProcess() {
        return process;
    }

    /**
     * @param process
     *            the process to set
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * @return the thread
     */
    public String getThread() {
        return thread;
    }

    /**
     * @param thread
     *            the thread to set
     */
    public void setThread(String thread) {
        this.thread = thread;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className
     *            the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName
     *            the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * @return the param
     */
    public List<Object> getParam() {
        return param;
    }

    /**
     * @param param
     *            the param to set
     */
    public void setParam(List<Object> param) {
        this.param = param;
        this.strParam = paramToJson(param);
    }

    /**
     * @return the strParam
     */
    public String getStrParam() {
        if (strParam == null && param.size() > 0) {
            strParam = paramToJson(param);
        }
        return strParam;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("sid:" + sid + "\t");
        builder.append("uid:" + uid + "\t");
        builder.append("startTime:" + startTime + "\t");
        builder.append("endTime:" + endTime + "\t");
        builder.append("porcess:" + process + "\t");
        builder.append("thread:" + thread + "\t");
        builder.append("className:" + className + "\t");
        builder.append("methodName:" + methodName + "\t");
        builder.append("param:" + strParam);
        return builder.toString();
    }

    private static String paramToJson(List<Object> paramList) {
        JSONArray json = new JSONArray();
        if (paramList != null) {
            for (Object obj : paramList) {
                json.put(obj);
            }
        }
        return json.toString();
    }
}
