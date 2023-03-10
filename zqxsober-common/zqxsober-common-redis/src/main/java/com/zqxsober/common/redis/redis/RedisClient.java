package com.zqxsober.common.redis.redis;

import com.alibaba.fastjson.JSON;
import com.zqxsober.common.redis.constant.RedisKeyConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author Bacon
 * @createTime 2018年08月31日 16:45
 * @description 基本redis常量
 */
@Component
public class RedisClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Description: 得到值
     *
     * @param key
     */
    public <V> V get(String key) {
        return (V) redisTemplate.opsForValue().get(key);
    }


    /**
     * Description: 设置键值
     *
     * @param key value
     */
    public <V> void set(String key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * Description: 获取原来的值并设置为新值
     *
     * @param key value
     */
    @SuppressWarnings("unchecked")
    public <V> V getset(String key, V value) {
        return (V) redisTemplate.opsForValue().getAndSet(key, value);
    }

    /*** Description: 设置键值 并同时设置有效期
     * @param key seconds秒数 value
     */
    public <V> void setex(String key, int seconds, V value) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 设置key value,如果key已经存在则返回0,nx==> not exist
     *
     * @param
     * @return 成功返回true 如果存在 和 发生异常 返回 false
     */
    public <V> Boolean setnx(String key, V value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 设置key value,如果key已经存在则返回0,nx==> not exist
     * 并设置有效时间
     *
     * @param
     * @return 成功返回true 如果存在 和 发生异常 返回 false
     */
    public <V> Boolean setnxEx(String key, V value, long time, TimeUnit tu) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, tu);
    }

    /*** Description:
     * 通过key 和offset 从指定的位置开始将原先value替换
     * 下标从0开始,offset表示从offset下标开始替换
     * 如果替换的字符串长度过小则会这样
     * example:
     * value : bigsea@zto.cn
     * str : abc
     * 从下标7开始替换  则结果为
     * RES : bigsea.abc.cn
     *
     * @param key
     * @param value
     * @param offset
     */
    public <V> void setrange(String key, V value, int offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * Description: 通过批量的key获取批量的value
     *
     * @param keys
     * @return 成功返回value的集合, 失败返回null的集合 ,异常返回空
     */
    public <V> List<V> mget(List<String> keys) {
        return (List<V>) redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * Description: 批量的设置key:value,可以一个
     *
     * @param
     * @return
     */
    public <V> void mset(Map<String, V> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    /**
     * Description: 通过key向指定的value值追加值
     *
     * @param key value 追加字符串
     */
    public int append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    /**
     * Description: 判断key是否存在
     *
     * @param
     */
    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * Description: 设置key有效期
     *
     * @param key seconds秒数
     */
    public Boolean expire(String key, int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * Description: 设置key有效期
     *
     * @param key MILLISECONDS 毫秒
     */
    public Boolean expire(String key, long milliseconds) {
        return redisTemplate.expire(key, milliseconds, TimeUnit.MILLISECONDS);
    }


    /**
     * Description: 删除某个键
     *
     * @param keys
     */
    public void del(List<String> keys) {
        redisTemplate.delete(keys);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }


    /**
     * Description: 该key还能存活多久
     *
     * @param key
     */
    public Long timetolive(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public Long time() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.time();
            }
        });
    }

    /**
     * Description: 批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚
     *
     * @param map
     * @return 成功返回1 失败返回0
     */
    public <V> Boolean msetnx(Map<String, V> map) {
        return redisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    /**
     * 通过下标 和key 获取指定下标位置的 value
     *
     * @param key
     * @param startOffset 开始位置 从0 开始 负数表示从右边开始截取
     * @param endOffset
     * @return 如果没有返回null
     */
    public Object getrange(String key, int startOffset, int endOffset) {
        return redisTemplate.opsForValue().get(key, startOffset, endOffset);
    }

    /**
     * 通过key给指定的value加值,如果key不存在,则这是value为该值
     *
     * @param key
     * @param integer
     * @return
     */
    public Long incrBy(String key, Long integer) {
        return redisTemplate.opsForValue().increment(key, integer);
    }

    /**
     * 通过key给加1
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 通过key获取value值的长度
     *
     * @param key
     * @return 失败返回null
     */
    public Long strlen(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * 通过key给field设置指定的值,如果key不存在,则先创建
     *
     * @param key
     * @param field 字段
     * @param value
     * @return 如果存在返回0 异常返回null
     */
    public <V> void hset(String key, String field, V value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Boolean hsetnx(String key, String field, Object value) {
        return redisTemplate.opsForHash().putIfAbsent(key, field, value);
    }

    /**
     * 通过key同时设置 hash的多个field
     *
     * @param key
     * @param hash
     * @return 返回OK 异常返回null
     */
    public void hmset(String key, Map<String, Object> hash) {
        redisTemplate.opsForHash().putAll(key, hash);
    }

    /**
     * 通过key 和 field 获取指定的 value
     *
     * @param key
     * @param field
     * @return 没有返回null
     */
    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 通过key 和 fields 获取指定的value 如果没有对应的value则返回null
     *
     * @param key
     * @param hashKeys
     * @return
     */
    public List<Object> hmget(String key, List<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 通过key给指定的field的value加上给定的值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hincrBy(String key, String field, Long value) {
        return redisTemplate.opsForHash().increment(key, field, value);
    }

    /**
     * 通过key给指定的field加1
     *
     * @param key
     * @param field
     * @return
     */
    public Long hincr(String key, String field) {
        return redisTemplate.opsForHash().increment(key, field, 1);
    }

    /**
     * 通过key和field判断是否有指定的value存在
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean hexists(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 通过key返回field的数量
     *
     * @param key
     * @return
     */
    public Long hlen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 通过key 删除指定的 field
     *
     * @param key
     * @param fields 可以是 一个 field 也可以是 一个数组
     * @return
     */
    @SuppressWarnings("unchecked")
    public void hdel(String key, String... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 通过key返回所有的field
     *
     * @param key
     * @return
     */
    public Set<Object> hkeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 通过key返回所有和key有关的value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V> List<V> hvals(String key) {
        return (List<V>) redisTemplate.opsForHash().values(key);
    }

    /**
     * 通过key获取所有的field和value
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 通过key向list头部添加字符串
     * <p>
     * s
     *
     * @param key
     * @param value 可以使一个string
     * @return 返回list的value个数
     */
    public <V> Long lpush(String key, V value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 通过key向list头部添加字符串
     *
     * @param key
     * @param values 可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public <V> Long lpushAll(String key, List<V> values) {
        return redisTemplate.opsForList().leftPushAll(key, values.toArray());
    }

    /**
     * 通过key向list尾部添加字符串
     *
     * @param key
     * @param values 可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public <V> Long rpush(String key, List<V> values) {
        return redisTemplate.opsForList().rightPushAll(key, values.toArray());
    }

    /**
     * 通过key向list尾部添加字符串
     *
     * @param key
     * @param values 可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public <V> Long rPushAll(String key, List<V> values) {
        return redisTemplate.opsForList().rightPushAll(key, values.toArray());
    }

    /**
     * 通过key设置list指定下标位置的value
     * 如果下标超过list里面value的个数则报错
     *
     * @param key
     * @param index 从0开始
     * @param value
     * @return 成功返回OK
     */
    public <V> void lset(String key, Long index, V value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 通过key从对应的list中删除指定的count个 和 value相同的元素
     *
     * @param key
     * @param count 当count为0时删除全部
     * @param value
     * @return 返回被删除的个数
     */
    public <V> Long lrem(String key, long count, V value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 通过key"保留"list中
     * 从strat下标开始到end下标结束的value值
     *
     * @param key
     * @param start
     * @param end
     * @return 成功返回OK
     */
    public void ltrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 通过key从list的头部删除一个value,并返回该value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V> V lpop(String key) {
        return (V) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 通过key从list尾部删除一个value,并返回该元素
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V> V rpop(String key) {
        return (V) redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 阻塞式删删除list尾部
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V> V brpop(String key, long timeout) {
        return (V) redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 通过key从一个list的尾部删除一个value并添加到另一个list的头部,并返回该value
     * 如果第一个list为空或者不存在则返回null
     *
     * @param srckey
     * @param dstkey
     * @return
     */
    @SuppressWarnings("unchecked")
    public <V> V rpoplpush(String srckey, String dstkey) {
        return (V) redisTemplate.opsForList().rightPopAndLeftPush(srckey, dstkey);
    }

    /**
     * 通过key获取list中指定下标位置的value
     *
     * @param key
     * @param index
     * @return 如果没有返回null
     */
    @SuppressWarnings("unchecked")
    public <V> V lindex(String key, long index) {
        return (V) redisTemplate.opsForList().index(key, index);
    }

    /**
     * 通过key返回list的长度
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过key获取list指定下标位置的value
     * 如果start 为 0 end 为 -1 则返回全部的list中的value
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public <V> List<V> lrange(String key, long start, long end) {
        return (List<V>) redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 通过key向指定的set中添加value
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public <V> Long sadd(String key, V... members) {
        return redisTemplate.opsForSet().add(key, members);
    }

    public <V> Long sadd(String key, List<V> members) {
        return redisTemplate.opsForSet().add(key, members.toArray());
    }


    /**
     * 通过key获得集合
     */
    public <V> Set<Object> sget(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    /**
     * 通过key删除set中对应的value值
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 删除的个数
     */
    public <V> Long srem(String key, V... members) {
        return redisTemplate.opsForSet().remove(key, members);
    }

    /**
     * 通过key随机删除一个set中的value并返回该值
     *
     * @param key
     * @return
     */
    public <V> V spop(String key) {
        return (V) redisTemplate.opsForSet().pop(key);
    }

    /**
     * 通过key获取set中的差集
     *
     * @param otherKeys 可以是一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public <V> Set<V> sdiff(String key, Collection<String> otherKeys) {
        return (Set<V>) redisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * 通过key获取set中的差集并存入到另一个key中
     *
     * @param key
     * @param dstkey    差集存入的key
     * @param otherKeys 可以使一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public Long sdiffstore(String key, String dstkey, List<String> otherKeys) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, dstkey);
    }

    /**
     * 通过key获取指定set中的交集
     *
     * @param otherKeys 可以使一个string 也可以是一个string数组
     * @return
     */
    public <V> Set<V> sinter(String key, List<String> otherKeys) {
        return (Set<V>) redisTemplate.opsForSet().intersect(key, otherKeys);
    }

    /**
     * 通过key获取指定set中的交集 并将结果存入新的set中
     *
     * @param key
     * @param dstkey
     * @param otherKeys 可以使一个string 也可以是一个string数组
     * @return
     */
    public Long sinterstore(String key, String dstkey, List<String> otherKeys) {

        return redisTemplate.opsForSet().intersectAndStore(key, otherKeys, dstkey);
    }


    /**
     * 通过key返回所有set的并集
     *
     * @param otherKeys 可以使一个string 也可以是一个string数组
     * @return
     */
    public <V> Set<V> sunion(String key, List<String> otherKeys) {
        return (Set<V>) redisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * 通过key返回所有set的并集,并存入到新的set中
     *
     * @param dstkey
     * @param otherKeys 可以使一个string 也可以是一个string数组
     * @return
     */
    public Long sunionstore(String key, String dstkey, List<String> otherKeys) {
        return redisTemplate.opsForSet().unionAndStore(key, otherKeys, dstkey);
    }

    /**
     * 通过key将set中的value移除并添加到第二个set中
     *
     * @param srckey 需要移除的
     * @param dstkey 添加的
     * @param value  set中的value
     * @return
     */
    public <V> Boolean smove(String srckey, V value, String dstkey) {
        return redisTemplate.opsForSet().move(srckey, value, dstkey);
    }

    /**
     * 通过key获取set中value的个数
     *
     * @param key
     * @return
     */
    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 通过key判断value是否是set中的元素
     *
     * @param key
     * @param member
     * @return
     */
    public <V> Boolean sismember(String key, V member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    /**
     * 通过key获取set中随机的value,不删除元素
     *
     * @param key
     * @return
     */
    public <V> V srandmember(String key) {
        return (V) redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 通过key获取set中所有的value
     *
     * @param key
     * @return
     */
    public <V> Set<V> smembers(String key) {
        return (Set<V>) redisTemplate.opsForSet().members(key);
    }

    /**
     * 通过key删除给定区间内的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long zremoveRange(String key, long start, long end) {

        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * 通过key删除 给定values内的元素
     *
     * @param key
     * @return
     */
    public Long zremove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 通过key删除指定score内的元素
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zremoveRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    /**
     * 添加元素
     *
     * @param key
     * @param value
     * @param score
     */
    public <V> boolean zAdd(String key, V value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 添加元素集
     *
     * @param key
     * @param set
     */
    public <V> Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> set) {
        return redisTemplate.opsForZSet().add(key, set);
    }


    /**
     * 获取元素分值
     *
     * @param key
     * @param value
     */
    public <V> Double zScore(String key, V value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 获取分值间元素个数
     *
     * @param key
     * @param min
     * @param max
     */
    public <V> Long zCount(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    /**
     * 反向获取指定区间的元素
     *
     * @param key
     * @param start 起始坐标
     * @param end   尾部坐标
     */
    public <V> Set zRevRangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, start, end);
    }

    /**
     * 反向获取指定区间的元素
     *
     * @param key
     * @param start 起始坐标
     * @param end   尾部坐标
     */
    public <V> Set zRevRangeWithScores(String key, long start, long end, long offset, long count) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, start, end, offset, count);
    }

    /**
     * 正向获取指定区间的元素
     *
     * @param key
     * @param start 起始坐标
     * @param end   尾部坐标
     */
    public <V> Set zRangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, start, end);
    }

    /**
     * 发布消息
     */
    public void publishMessage(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }

    /**
     * bitmap bit位设置为1
     *
     * @param key    redis key
     * @param offset 偏移量
     */
    public void bitmapSet1(String key, Long offset) {
        this.redisTemplate.opsForValue().setBit(key, offset, true);
    }

    /**
     * bitmap bit位设置为0
     *
     * @param key    redis key
     * @param offset 偏移量
     */
    public void bitmapSet0(String key, Long offset) {
        this.redisTemplate.opsForValue().setBit(key, offset, false);
    }

    /**
     * bitmap bit位是否已被设置
     *
     * @param key
     * @param offset
     * @return
     */
    public boolean bitmapOffsetIfTrue(String key, Long offset) {
        return this.redisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * bitmap bit位是1的数量
     *
     * @param key
     * @return
     */
    public int bitmapOffsetTrueCount(String key) {
        return this.redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(StandardCharsets.UTF_8))).intValue();
    }

    /**
     * 缓存值不是数组类型(List)
     * 支持Byte/Short/Integer/Long/String/JSONObject类型的缓存值，不支持JSONArray
     *
     * @param key                    缓存key
     * @param cacheSeconds           缓存秒数，如果<=0则表示永久缓存
     * @param clazz                  返回值Class
     * @param directlySelectFunction 当查询不到缓存时，直接查询的方法；如果为null，则直接返回null
     * @param cacheNullAsEmpty       是否缓存NULL（redis中value缓存为空白字符串），true：则返回值被查询出来是NULL也会被缓存；但不进行缓存延期，且最多允许缓存N分钟。
     * @param duration               是否续缓存时间，true：每次从redis中获取缓存会对缓存进行续时
     * @param <T>                    返回值类型
     * @return
     */
    public <T> T getObject(final String key, long cacheSeconds,
                           Class<T> clazz, Supplier<T> directlySelectFunction,
                           boolean cacheNullAsEmpty, boolean duration) {
        T obj = null;
        Object v = this.redisTemplate.opsForValue().get(key);
        String stringValue = v != null ? v.toString() : null;
        boolean isJSON = false;
        boolean isKeyNotExistInRedis = true;
        if (stringValue != null) {
            // 有在redis中获取到值
            isKeyNotExistInRedis = false;
            if (stringValue.length() > 0) {
                try {
                    if (Byte.class.equals(clazz)) {
                        // parseByte
                        obj = (T) Byte.valueOf(stringValue);
                    } else if (Short.class.equals(clazz)) {
                        // parseShort
                        obj = (T) Short.valueOf(stringValue);
                    } else if (Integer.class.equals(clazz)) {
                        // parseInt
                        obj = (T) Integer.valueOf(stringValue);
                    } else if (Long.class.equals(clazz)) {
                        // parseLong
                        obj = (T) Long.valueOf(stringValue);
                    } else if (String.class.equals(clazz)) {
                        // String
                        obj = (T) stringValue;
                    } else {
                        // 解析JSON
                        // 解析JSONObject，本方法不支持JSONArray
                        isJSON = true;
                        obj = JSON.parseObject(stringValue, clazz);
                    }
                    if (obj != null && duration && cacheSeconds > 0) {
                        // 延期
                        this.redisTemplate.expire(key, cacheSeconds, TimeUnit.SECONDS);
                    }
                } catch (Exception e) {
                    logger.error("getObjectOver-" + key, e);
                }
            }
        } else {
            if (Byte.class.equals(clazz)) {
            } else if (Short.class.equals(clazz)) {
            } else if (Integer.class.equals(clazz)) {
            } else if (Long.class.equals(clazz)) {
            } else if (String.class.equals(clazz)) {
            } else {
                // 解析JSON
                isJSON = true;
            }
        }
        if (isKeyNotExistInRedis || (!cacheNullAsEmpty && obj == null)) {
            // 如果key不在redis中 或者 （不缓存NULL 但obj为null）
            // 则都要尝试直接查询
            obj = directlySelectFunction != null ? directlySelectFunction.get() : null;
            if (obj != null) {
                try {
                    String value;
                    if (isJSON) {
                        value = JSON.toJSONString(obj);
                    } else {
                        value = obj.toString();
                    }
                    if (cacheSeconds > 0) {
                        this.redisTemplate.opsForValue().set(key, value, cacheSeconds, TimeUnit.SECONDS);
                    } else {
                        this.redisTemplate.opsForValue().set(key, value);
                    }
                } catch (Exception e) {
                    logger.error("getObjectOver-" + key, e);
                }
            } else if (cacheNullAsEmpty) {
                // obj经过直接查询后为null，但要缓存NULL（redis中value缓存为空白字符串）
                // 空值最多只缓存时间；但不进行缓存延期，且最多允许缓存N分钟。
                long nullCacheSeconds = RedisKeyConstant.CacheTimeConstant.SECONDS_OF_TEN_MINUTE;
                if (cacheSeconds > 0) {
                    // 且最多允许缓存N分钟
                    nullCacheSeconds = Math.min(RedisKeyConstant.CacheTimeConstant.SECONDS_OF_HALF_HOUR, cacheSeconds);
                }
                // 为空值设置缓存（直接查询到null，缓存空值，但没有续期）
                try {
                    this.redisTemplate.opsForValue().set(key, "", nullCacheSeconds, TimeUnit.SECONDS);
                } catch (Exception e) {
                    logger.error("getObjectOver-" + key, e);
                }
            }
        }
        return obj;
    }

    /**
     * 缓存值是数组类型(List)
     *
     * @param key                    缓存key
     * @param cacheSeconds           缓存秒数，如果<=0则表示永久缓存
     * @param clazz                  返回的List中的Class
     * @param directlySelectFunction 当查询不到缓存时，直接查询的方法；如果为null，则直接返回null
     * @param isCacheEmptyList       是否缓存空白List
     * @param duration               是否续缓存时间，true：每次从redis中获取缓存会对缓存进行续时
     * @param <T>                    返回值类型
     * @return
     */
    public <T> List<T> getList(final String key, long cacheSeconds,
                               Class<T> clazz, Supplier<List<T>> directlySelectFunction,
                               boolean isCacheEmptyList, boolean duration) {
        List<T> list = null;
        Object v = this.redisTemplate.opsForValue().get(key);
        String stringValue = v != null ? v.toString() : null;
        if (stringValue != null && stringValue.length() > 0) {
            try {
                list = JSON.parseArray(stringValue, clazz);
                if (list != null && duration && cacheSeconds > 0) {
                    // 延期
                    this.redisTemplate.expire(key, cacheSeconds, TimeUnit.SECONDS);
                }
            } catch (Exception e) {
                logger.error("getUserFavSubjectRootVoList-" + key, e);
            }
        }
        if (list == null) {
            list = directlySelectFunction != null ? directlySelectFunction.get() : null;
            if (list != null) {
                if (!list.isEmpty() || isCacheEmptyList) {
                    try {
                        String value = JSON.toJSONString(list);
                        if (cacheSeconds > 0) {
                            this.redisTemplate.opsForValue().set(key, value, cacheSeconds, TimeUnit.SECONDS);
                        } else {
                            this.redisTemplate.opsForValue().set(key, value);
                        }
                    } catch (Exception e) {
                        logger.error("getUserFavSubjectRootVoList-" + key, e);
                    }
                }
            }
        }
        return list != null ? list : new ArrayList<>(0);
    }
}
