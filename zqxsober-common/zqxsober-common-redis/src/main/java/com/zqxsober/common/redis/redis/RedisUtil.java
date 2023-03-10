package com.zqxsober.common.redis.redis;

import cn.hutool.core.collection.CollUtil;
import com.zqxsober.common.redis.constant.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redisTemplate封装
 *
 * @author yinxp@dist.com.cn
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /*============================String=============================*/

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 计数器
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long counter(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 获取计数器
     */
    public long getCounter(String key) {
        return redisTemplate.opsForValue().increment(key);
    }


    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }
    /*============================String end=============================*/




    /*================================Map==============================*/

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @return 对应的多个键值
     */
    public List<Map> hmget(String keyPattern) {
        Set<String> keys = redisTemplate.keys(keyPattern);
        List<Map> results = new ArrayList<>();
        for (String key : keys) {
            List<Object> values = redisTemplate.opsForHash().values(key);
            List<Object> keys1 = new ArrayList<>(redisTemplate.opsForHash().keys(key));

            int length = keys1.size();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < length; i++) {

                map.put((String) keys1.get(i), values.get(i));
            }
            map.put("key", key);
            results.add(map);

        }
        return results;
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hashMapset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hashSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hashSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hashDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }


    /**
     * hash表累加统计数
     *
     * @param key
     * @param item   项
     * @param number 数量
     */
    public boolean hashValueCount(String key, String item, Integer number) {
        Integer count = (Integer) hashGet(key, item);
        count = count != null ? count + number : 1;
        return hashSet(key, item, count);
    }


    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key
     * @param item
     * @param by
     * @param time 过期时间
     * @return
     */
    public double hincr(String key, String item, double by, long time) {
        double value = redisTemplate.opsForHash().increment(key, item, by);
        if (time > 0) {
            expire(key, time);
        }
        return value;
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by, long time) {
        double value = redisTemplate.opsForHash().increment(key, item, -by);
        if (time > 0) {
            expire(key, time);
        }
        return value;
    }

    /**
     * 获取hash字段个数
     *
     * @param key
     * @return
     */
    public Long hlen(String key) {
        return redisTemplate.opsForHash().size(key);
    }
    /*================================Map  end====================*/

    /*============================set=============================*/

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 更具key删除
     *
     * @param key
     * @return
     */
    public boolean setRemove(String key) {
        try {
            redisTemplate.opsForSet().randomMember(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*============================set end=============================*/

    /*===============================list=============================*/

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @return
     */
    public List<Object> multiGet(Collection keys) {
        try {
            return redisTemplate.opsForValue().multiGet(keys);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
//    public boolean lSet(String key, Object value, long time) {
//        try {
//            redisTemplate.opsForList().rightPush(key, value);
//            if (time > 0) {
//                expire(key, time);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean listSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean listSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除 list
     *
     * @param key
     * @param start
     * @param end
     */
    public void lTrim(String key, long start, long end) {
        try {
            redisTemplate.opsForList().trim(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key
     * @return
     */
    public Object leftPop(String key) {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*===============================list  end=============================*/

    /**
     * 模糊查询获取key值
     *
     * @param pattern "*"查询所有key
     * @return
     */
    public Set keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 使用Redis的消息队列
     *
     * @param channel
     * @param message 消息内容
     */
    public void convertAndSend(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }


    //=========BoundListOperations 用法 start============

    /**
     * 将数据添加到Redis的list中（从右边添加）
     *
     * @param listKey
     * @param expireEnum 有效期的枚举类
     * @param values     待添加的数据
     */
    public void addToListRight(String listKey, Status.ExpireEnum expireEnum, Object... values) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        //插入数据
        boundValueOperations.rightPushAll(values);
        //设置过期时间
        boundValueOperations.expire(expireEnum.getTime(), expireEnum.getTimeUnit());
    }

    /**
     * 根据起始结束序号遍历Redis中的list
     *
     * @param listKey
     * @param start   起始序号
     * @param end     结束序号
     * @return
     */
    public List<Object> rangeList(String listKey, long start, long end) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        //查询数据
        return boundValueOperations.range(start, end);
    }

    /**
     * 弹出右边的值 --- 并且移除这个值
     *
     * @param listKey
     */
    public Object rifhtPop(String listKey) {
        //绑定操作
        BoundListOperations<String, Object> boundValueOperations = redisTemplate.boundListOps(listKey);
        return boundValueOperations.rightPop();
    }

    //=========BoundListOperations 用法 End============


    /**
     * 通过key向指定的set中添加value
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public <V> boolean sadd(String key, V members, long time) {
//        return redisTemplate.opsForSet().add(key, members);
        try {
            redisTemplate.opsForSet().add(key, members);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public <V> boolean sadd(String key, V members) {
        try {
            redisTemplate.opsForSet().add(key, members);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public <V> boolean sadd(String key, List<V> members) {
        try {
            redisTemplate.opsForSet().add(key, members.toArray());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public <V> boolean sadd(String key, List<V> members, long time) {
        try {
            redisTemplate.opsForSet().add(key, members.toArray());
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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


    public Boolean zSet(String key, Long score, Object value) {
        try {
            return redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean zSetremove(String key) {
        try {
            redisTemplate.opsForZSet().removeRange(key, 0, -1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean zSet(String key, Long score, Object value, long time) {
        try {
            redisTemplate.opsForZSet().add(key, value, score);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取zset集合,倒序
     *
     * @param key
     * @return
     */
    public Object reverseRange(String key, int start, int end) {
        try {
            return redisTemplate.opsForZSet().reverseRange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Object zrange(String key, int start, int end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object zrangeByScore(String key, Long start, Long end) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double zScore(String key, String member) {
        try {
            return redisTemplate.opsForZSet().score(key, member);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 按照score 值递增(从小到大)顺序排列
     *
     * @param key
     * @param member
     * @return
     */
    public Long zRank(String key, Object member) {
        try {
            return redisTemplate.opsForZSet().rank(key, member);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 按照score 值递减(从大到小)顺序排列
     *
     * @param key
     * @param member
     * @return
     */
    public Long reverseZRank(String key, Object member) {
        try {
            return redisTemplate.opsForZSet().reverseRank(key, member);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取zst元素个数
     *
     * @param key
     * @return
     */
    public Long zCard(String key) {
        try {
            return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * setBit  setbit 的作用说的直白点就是：在你想要的位置操作字节值，比如说用户 3 在 3月13号 签到了，
     * 那么 setbit(20190313, 3 ,1) 就可以实现签到功能了，这里的 offset 就是3，同理，不同的用户不同的日期，改变对应的值就好了。
     *
     * @param key
     * @param offset
     * @param value
     * @return
     */
    public Boolean setBit(String key, long offset, boolean value) {
        try {
            return redisTemplate.opsForValue().setBit(key, offset, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * getBit
     *
     * @param key
     * @param offset
     * @return
     */
    public Boolean getBit(String key, long offset) {
        try {
            return redisTemplate.opsForValue().getBit(key, offset);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回为true的数量
     *
     * @param key
     * @return
     */
    public long bitCount(String key) {
        try {
            return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 模糊删除
     * @param pre
     */
    public void deleteByPre(String pre) {
        Set<String> keys = redisTemplate.keys(pre + "*");
        if (keys != null && CollUtil.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

}
