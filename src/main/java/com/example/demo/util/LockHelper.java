package com.example.demo.util;

import com.example.demo.TimeService;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

public class LockHelper {

    private static final String REDIS_LOCK = "redis_lock";
    private static final long INTERVAL_LOCK_LEASE_TIME = 60000;
    private static final long timeout = 100000;
    private static final Jedis jedis = new Jedis("192.168.248.129", 6379);
    private static final SetParams setParams = SetParams.setParams().nx().px(INTERVAL_LOCK_LEASE_TIME);

    public static boolean lock(String id) {
        long start = TimeService.getTime();
        try {
            for (; ; ) {
                String result = jedis.set(REDIS_LOCK, id, setParams);
                if (StringUtils.equalsIgnoreCase("ok", result)) {
                    return true;
                }
                if (TimeService.getTime() - start > timeout) {
                    return false;
                }
                try {
                    Thread.sleep(5);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } finally {
            jedis.close();
        }
    }

    public static boolean unlock(String id) {
        String script=" if redis.call('get',KEYS[1])==ARGV[1] then "+
                           " return redis.call('del',KEYS[1]) "+
                           " else return 0 "+
                           " end ";
        String result=jedis.eval(script, Collections.singletonList(REDIS_LOCK),Collections.singletonList(id)).toString();
        return StringUtils.equalsIgnoreCase(result,"1");
    }
}
