package com.offcn.webui.utils;

import java.util.concurrent.TimeUnit;

public interface RedisLock {

    public boolean tryLock(String key, long timeout, TimeUnit unit);

    public void releaseLock(String key);

}
