package com.zqxsober.common.redis.redisson.lock;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zqxsober
 * @Description: RedissonLock 类
 * @Date: 2022-08-04
 */
@RequiredArgsConstructor
public class RedissonLock {

    private final RedissonClient redissonClient;

    /**
     * 获取锁对象
     * @param key           锁key
     * @return              锁对象
     */
    private RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    /**
     * 加锁
     * @param key       锁key
     */
    public void lock(String key) {
        this.getLock(key).lock();
    }

    /**
     * 加锁
     * @param key           锁key
     * @param seconds       过期时间
     * @param timeUnit      单位时间单位
     * @return 是否获取锁
     */
    public boolean lock(String key, Integer seconds, TimeUnit timeUnit) {
        this.getLock(key).lock(seconds, timeUnit);
        return isLocked(key);
    }

    /**
     * 解锁
     * @param key           锁key
     */
    public void unlock(String key) {
        this.getLock(key).unlock();
    }

    /**
     * 此方法没有等待时间，没有锁过期时间，但是会默认使用看门狗的30秒作为超时时间，
     * 并构建一个定时任务自动续约，续约的条件是业务执行时间大于默认时间的2/3，即20秒时自动续约，重新续约的时间为30秒
     */
    public boolean tryLock(String key) {
        return this.getLock(key).tryLock();
    }

    /**
     * 有等待时间，没有锁过期时间，所以续约策略如上
     * @param key           锁key
     * @param waitTime      等待时间
     * @return              是否获取锁
     */
    public boolean tryLock(String key, Long waitTime) {
        try {
            return this.getLock(key).tryLock(waitTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 有等待时间，有锁过期时间，没有自动续约策略，即业务执行时间超过锁的过期时间，锁也将被自动释放
     * @param key           锁key
     * @param waitTime      等待时间
     * @param leaseTime     超时时间
     * @return              是否获取锁
     */
    public boolean tryLock(String key, Long waitTime, Long leaseTime) {
        try {
            return getLock(key).tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否加锁
     * @param key           锁key
     * @return              是否加锁
     */
    public boolean isLocked(String key) {
        return getLock(key).isLocked();
    }

}
