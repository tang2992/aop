package com.coffee.core.aop;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.coffee.core.db.DBFactory;
import com.coffee.core.db.DBInterface;
import com.coffee.core.entity.SysTrack;
import com.coffee.core.entity.Track;

public class Process {

    public static final ThreadLocal<SysTrack> tl = new ThreadLocal<SysTrack>();

    public static void before(Object[] objs) {
        SysTrack st = (SysTrack) tl.get();
        if (st == null) {
            st = initSysTrack();
        } else {
            st.setStartTime(System.currentTimeMillis());
        }
    }

    public static void after(Object[] objs) {
        Track track = new Track();
        SysTrack st = (SysTrack) tl.get();
        if (st == null) {
            System.out.println("系统异常");
            st = initSysTrack();
        }

        // UID的处理
        int uid = st.getUid();
        uid++;
        st.setUid(uid);

        track.setSid(st.getSid());
        track.setUid(uid);
        track.setStartTime(st.getStartTime());
        track.setEndTime(System.currentTimeMillis());
        track.setProcess(ManagementFactory.getRuntimeMXBean().getName());
        track.setThread(Thread.currentThread().getName());
        track.setClassName(Thread.currentThread().getStackTrace()[2].getClassName());
        track.setMethodName(Thread.currentThread().getStackTrace()[2].getMethodName());
        track.setParam(arrayToList(objs));

        // 保存数据
        DBInterface db = DBFactory.getConsoleInstance();
        db.save(track);
    }

    private static List<Object> arrayToList(Object[] objs) {
        List<Object> objList = new ArrayList<Object>();
        if (objs != null && objs.length > 0) {
            for (Object obj : objs) {
                objList.add(obj);
            }
        }
        return objList;
    }

    private static SysTrack initSysTrack() {
        SysTrack st = new SysTrack();
        st.setSid(UUID.randomUUID().toString());
        st.setStartTime(System.currentTimeMillis());
        tl.set(st);
        return st;
    }
}
