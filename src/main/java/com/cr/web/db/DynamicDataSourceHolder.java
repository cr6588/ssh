package com.cr.web.db;

public class DynamicDataSourceHolder {
    // 写库对应的数据源key
    public static final String MASTER = "master";

    // 读库对应的数据源key
    private static final String SLAVE = "slave";

    // 使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 设置数据源key
     * @param key
     */
    public static void setDataSourceKey(String key) {
        holder.set(key);
    }

    /**
     * 获取数据源key
     * @return
     */
    public static String getDataSourceKey() {
        return holder.get();
    }

    /**
     * 标记写库
     */
    public static void markMaster() {
        setDataSourceKey(MASTER);
    }

    /**
     * 标记读库
     */
    public static void markSlave() {
        setDataSourceKey(SLAVE);
    }

}
