package com.zqxsober.common.redis.constant;

import java.util.Calendar;

/**
 * @author Bacon
 * @createTime 2018年08月31日 16:45
 * @description 全局redis key常量
 * 恒久缓存：需要主动进行刷新或删除以确保数据一致性
 * 时效缓存：过期后再次获取自动刷新，但是源数据变更依然要调整缓存数据以确保数据一致性
 */
@SuppressWarnings("all")
public class RedisKeyConstant {
    /**
     * 获取当前时刻到明日0:0:0.000（明日开始的时刻）之间的毫秒值
     *
     * @return
     */
    public static Long now_todayEndTimeMsDelta() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() - System.currentTimeMillis();
    }

    /**
     * 系统级别
     */
    public interface SystemConstant {

        /**
         * app的缓存前缀
         */
        public static final String PREFIX_KET_APP = "application.app";
    }

    /**
     * redis缓存时长
     */
    public interface CacheTimeConstant {
        /**
         * 永久
         */
        int FOREVER = 0;

        /**
         * 半天
         */
        int SECONDS_OF_HALF_DAY = 12 * 60 * 60;

        /**
         * 半小时
         */
        int SECONDS_OF_HALF_HOUR = 30 * 60;

        /**
         * 10分钟
         */
        int SECONDS_OF_TEN_MINUTE = 10 * 60;

        /**
         * 验证码有效时长，秒
         */
        int VC_EXPIRE_SECONDS = 60 * 3;

        /**
         * 一周
         */
        int A_WEEK = 60 * 60 * 24 * 7;

        /**
         * 两小时
         */
        int TWO_HOURS = 60 * 60 * 2;

        /**
         * 发送消息60秒内不重复发
         */
        int SEND_MSG = 60;

        /**
         * 发送短信30s不能重发
         */
        int SEND_SMS = 30;

        /**
         * 一个月
         */
        int MONTH = 60 * 60 * 24 * 30;

        /**
         * 微信 API_TICKET 超时时间
         */
        int TICKET_TIME = 7000;
    }


    /**
     * key 过期事件
     */
    public static final String KEY_EVENT_EXPIRED = "__keyevent@*__:expired";
    /**
     * 1天的换算秒
     */
    public static final int EXPIRE_ONE_DAY = 60 * 60 * 24;

    public interface Wx {
        /**
         * 微信开放平台下移动应用 access_token
         */
        String OPEN_APP_ACCESS_TOKEN = "OPEN_APP_ACCESS_TOKEN";
        /**
         * 微信开放平台下移动应用获取access_token锁
         */
        String OPEN_APP_ACCESS_TOKEN_LOCK = "OPEN_APP_ACCESS_TOKEN_LOCK:{appId}";
        /**
         * 微信开放平台一次性订阅消息
         */
        String OPEN_ONE_TIME_SUB_MSG_LOCK = "OPEN_APP_ACCESS_TOKEN_LOCK:{uId}:{appType}";

        /**
         * 微信开放平台JS_API_TICKET
         */
        String OPEN_JS_API_TICKET = "OPEN_JS_API_TICKET_";

    }

    public interface Trade {
        /**
         * 竞拍保证金锁
         */
        String DEPOSIT_LOCK = "DEPOSIT_LOCK:{depositNo}";
        /**
         * 用户钱包支付密码输入错误次数
         */
        String WALLET_USER_PAY_PWD_ERROR_COUNT = "WALLET:USER:PWD_ERROR_COUNT:{uId}";
        /**
         * 用户余额支付时钱包的分布锁
         */
        String WALLET_USER_LOCK = "WALLET:USER:LOCK:{suffix}";


        /**
         * 用户提现审核
         **/
        String ADMIN_WALLET_USER_AUDIT = "ADMIN_WALLET_USER_AUDIT";

        /**
         * 店铺余额支付时钱包的分布锁
         */
        String WALLET_STORE_LOCK = "WALLET:STORE:LOCK:{suffix}";
        /**
         * 平台钱包的分布锁
         */
        String WALLET_PLATFORM_LOCK = "WALLET:PLATFORM:LOCK:{suffix}";
        /**
         * 订单物流信息
         */
        String ORDER_EXPRESS = "EXPRESS:ORDER:{orderNo}";
        /**
         * 售后单物流信息
         */
        String AFTER_SALE_EXPRESS = "EXPRESS:AFTER:{afterNo}";


        /**
         * 自动处理店铺提现任务加锁
         */
        String STORE_WITHDRAWAL_LOCK_JOB = "STORE:WITHDRAWAL:LOCK_JOB";


        /**
         * 提现单审核
         */
        String STORE_WITHDRAWAL_AUDIT = "STORE:STORE_WITHDRAWAL_AUDIT";

        /**
         * 提现聚合单 处理
         */
        String STORE_WITHDRAWAL_GROUP_AUDIT = "STORE:STORE_WITHDRAWAL_GROUP_AUDIT";

        /**
         * 获取当前时刻到明日0:0:0.000（明日开始的时刻）之间的毫秒值
         *
         * @return
         */
        static Long now_todayEndTimeMsDelta() {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTimeInMillis() - System.currentTimeMillis();
        }

        /**
         * cps可读未读记录
         */
        String CPS_CAN_READ_UNREAD = "CPS_CAN_READ_UNREAD";
    }

    /**
     * 敏感词库
     */
    public interface SensitiveWord {
        String SENSITIVE_WORD_KEY = "BASICDATA:SENSITIVE_WORD_KEY";
    }

    /**
     * 字典表的key
     */
    public interface Dictionary {

        /**
         * 整个基础字典表在redis中的 hashmap key
         */
        String DICTIONARY_TABLE = "DICTIONARY";

        /**
         * 店铺关闭时间
         */
        String CLOSE_DAYS = "SELLER:CLOSE_DAYS";

        /**
         * 会员违约率阈值
         */
        String BREAK_CONTRACT_SCALE_THRESHOLD_VALUE = "BREAK_CONTRACT_SCALE_THRESHOLD_VALUE";

        /**
         * 热门关键字
         */
        String HOT_KEYWORD_KEY = "HOT_KEYWORD";

        /**
         * 短连接配置前缀
         */
        String SHORT_URL_PREFIX_KEY = "SHORT_URL_PREFIX";

        /**
         * 用户钱包流水cps分佣详情 1:不允许查看 2:可以查看
         */
        String USER_WALLET_CPS_DETAILS_STATUS = "USER_WALLET_CPS_DETAILS_STATUS";

        /**
         * 普通订单即将确认收货站内信提醒时间
         */
        String ORDINARY_ORDER_BE_ABOUT_ABOUT_CINFIRM_RECEIPT = "ORDINARY_ORDER_BE_ABOUT_ABOUT_CINFIRM_RECEIPT";

        /**
         * 竞价订单即将确认收货站内信提醒时间
         */
        String BIDDING_ORDER_BE_ABOUT_ABOUT_CINFIRM_RECEIPT = "BIDDING_ORDER_BE_ABOUT_ABOUT_CINFIRM_RECEIPT";

        /**
         * 买家售后处理即将超时站内信提醒时间
         */
        String BUYER_AFTER_SALE_OUT_TIME = "BUYER_AFTER_SALE_OUT_TIME";

        /**
         * 商家即将超时确认收货提醒时间
         */
        String MERCHANTS_TIMEOUT_RECEIVING_TIME = "MERCHANTS_TIMEOUT_RECEIVING_TIME";
        /**
         * 竞拍订单即将发货超时时间
         */
        String BID_ORDER_SELLER_SEND_ABOUT_TIME = "BID_ORDER_SELLER_SEND_ABOUT_TIME";
        /**
         * 普通订单即将发货超时时间
         */
        String NORMAL_ORDER_SELLER_SEND_ABOUT_TIME = "NORMAL_ORDER_SELLER_SEND_ABOUT_TIME";
    }

    public interface Manage {

        /**
         * 验证码
         */
        String VERIFY_CODE = "MANAGE:VERIFY_CODE:";
        /**
         * 管理员登录令牌
         */
        String ADMIN_TOKEN = "MANAGE:TOKEN:";

        /**
         * 验证码错误次数
         */
        String USER_MOBILE_VC_CODE_ONE_DAY_OVER_DUE = "MANAGE:VERIFY_CODE:ERROR_TIMES:";


    }

    /**
     * 会员相关
     */
    public interface User {
        /**
         * 微信登录
         */
        String USER_THIRD_WX_TICKET = "USER_THIRD_WX_TICKET";
        /**
         * 苹果登录
         */
        String USER_THIRD_APPLE_TICKET = "USER_THIRD_APPLE_TICKET";
        /**
         * 用户实名认证结果
         */
        String USER_ID_CARD_CHECK = "USER_ID_CARD_CHECK:{uId}";
        /**
         * 用户积分增加
         */
        String USER_POINTS_ADD = "USER_POINTS_ADD:{uId}";
        int USER_POINTS_ADD_LOCK_TIME = 30;
        /**
         * 用户标签
         */
        String USER_TAG = "USER:TAG";

        /**
         * 会员登录令牌
         */
        String USER_TOKEN = "USER:TOKEN:";

        /**
         * 设置登录密码验证码
         */
        String SET_LOGIN_PWD_VC = "USER:VC:SET_LOGIN_PWD:{cellphone}";
        /**
         * 设置登录密码验证码错误次数
         */
        String SET_LOGIN_PWD_VC_ERROR_COUNT = "USER:VC:SET_LOGIN_PWD_ERROR_COUNT:{cellphone}";

        /**
         * 设置支付密码验证码
         */
        String SET_PAY_PWD_VC = "USER:VC:SET_PAY_PWD:{cellphone}";
        /**
         * 设置支付密码验证码错误次数
         */
        String SET_PAY_PWD_VC_ERROR_COUNT = "USER:VC:SET_PAY_PWD_ERROR_COUNT:{cellphone}";

        /**
         * 设置手机号验证码
         */
        String SET_CELLPHONE_VC = "USER:VC:SET_CELLPHONE:{cellphone}";
        /**
         * 设置手机号验证码错误次数
         */
        String SET_CELLPHONE_VC_ERROR_COUNT = "USER:VC:SET_CELLPHONE_ERROR_COUNT:{cellphone}";

        /**
         * 手机验证码登录
         */
        String USER_MOBILE_PHONE_LOGIN_VC = "USER:MOBILE_LOGIN:VERIFY_CODE";

        /**
         * 生成会员id
         */
        String USER_U_ID = "USER:USER_ID:";

        /**
         * 会员基础数据
         */
        String USER_BASE = "USER:BASE:";

        /**
         * 验证码错误次数
         */
        String USER_MOBILE_VC_CODE_ONE_DAY_OVER_DUE = "USER:MOBILE_LOGIN:ERROR_TIMES:";
        /**
         * 微信小程序重新绑定令牌
         */
        String WX_REBINDING_STATE_TOKEN = "USER:WX_REBINDING_STATE_TOKEN:";

        /**
         * 验证码次数KEY
         */
        String USER_MESSAGE_SENT_NUMBER = "USER:MESSAGE_SENT_NUMBER";

        /**
         * 数据库验证码限制次数
         */
        String USER_MESSAGE_SENT_TYPE = "USER_MESSAGE_SENT_TYPE_";

        /**
         * 发送验证码时间限制
         */
        String USER_SMS_SEND_TIME = "USER:SMS:SEND_TIME:{}_TYPE:{}";


    }

    public interface BasicData {
        /**
         * 运费模板中的地区的格式化数据
         */
        String TEMPLATE_AREA = "AREA:TEMPLATE";
        /**
         * 待发消息队列KEY
         */
        String MOMENTUM_MQ_QUEUE_KEY = "MOMENTUM_MQ_QUEUE";

        /**
         * app 版本控制 开关
         */
        String APP_VERSION_SWITCH = "APP:APP_VERSION_SWITCH:";

    }


    public interface Content {

        /**
         * 内容redis前缀
         */
        String CONTENT_REDIS_PRE = "CONTENT:";
        /**
         * 社区
         */
        String TOPIC_KEY = "TOPIC:";

        /**
         * 点赞
         */
        String LIKE_KEY = "LIKE:";

        /**
         * 内容社区用户信息
         */
        String CONTENT_PERSON_INFO = "PERSON_INFO:";

        /**
         * 社区计算权重频率
         */
        String CALCULATE_TIME = "CALCULATE_TIME";

        /**
         * 上下级关注缓存
         */
        String PARENT_FOLLOW = "PARENT_FOLLOW:";

        /**
         * 用户点赞消息按分钟分组时间
         */
        String USER_LIKE_GROUP_TIME_KEY = "USER_LIKE_GROUP_TIME";

        /**
         * 动态点赞数缓存
         */
        String TOPIC_LIKE_KEY = CONTENT_REDIS_PRE + TOPIC_KEY + "LIKE_COUNT";
        /**
         * 话题热门排行榜
         */
        String TOPIC_GAMBIT_HOT_RANKING_KEY = CONTENT_REDIS_PRE + TOPIC_KEY + "GAMBIT_HOT_RANKING";

        /**
         * 动态点赞收藏记录
         */
        String TOPIC_OPERATION_RECORD = CONTENT_REDIS_PRE + TOPIC_KEY + "OPERATION_RECORD";


        /**
         * 点赞记录延迟写key
         */
        String LIKE_RECORD_DELAY_WRITE_KEY = CONTENT_REDIS_PRE + LIKE_KEY + "RECORD_DELAY_WRITE";

        /**
         * 点赞是否点赞记录
         */
        String LIKE_RECORD_KEY = CONTENT_REDIS_PRE + LIKE_KEY + "RECORD";

        /**
         * 动态曝光计数
         */
        String TOPIC_EXPOSURE_NUM_KEY = CONTENT_REDIS_PRE + TOPIC_KEY + "EXPOSURE_NUM:";
        /**
         * 需要更新动态曝光数动态id
         */
        String TOPIC_ID_UPDATE_EXPOSURE_KEY = CONTENT_REDIS_PRE + TOPIC_KEY + "ID_UPDATE_EXPOSURE:";

        /**
         * 内动动态浏览数
         */
        String TOPIC_BROWSE_NUMBER_KEY = CONTENT_REDIS_PRE + TOPIC_KEY + "BROWSE:NUMBER:";


        /**
         * 话题曝光计数
         */
        String GAMBIT_EXPOSURE_NUM_KEY = CONTENT_REDIS_PRE + "GAMBIT:EXPOSURE_NUM:";
        /**
         * 需要更新话题曝光数动态id
         */
        String GAMBIT_ID_UPDATE_EXPOSURE_KEY = CONTENT_REDIS_PRE + "GAMBIT:ID_UPDATE_EXPOSURE:";

        /**
         * 订阅记录头衔信息
         */
        String SUBSCRIPTION_USER_TITLE_RECORD = CONTENT_REDIS_PRE + TOPIC_KEY + "SUBSCRIPTION:";

        /**
         * 关注记录发消息key,超时后发送消息
         */
        String FOLLOW_UID_KEU = CONTENT_REDIS_PRE + "FOLLOW:UID:{}_{}";

        /**
         * 点赞记录发消息key_超时后发送消息
         */
        String LIKE_SEND_MSG = CONTENT_REDIS_PRE + "LIKE:SEND_MSG_UID:{}_{}";

    }


    public interface Lock {
        /**
         * 审核图片锁
         */
        String CHECK_IMG_LOCK = "CHECK_IMG_LOCK:{imgMd5}";

        /**
         * 竞价商品出价锁
         */
        String PRODUCT_BID = "product:bid:spuId:";
        /**
         * 点赞消息锁
         */
        String LIKE_MSG_GROUP = "MESSAGE_LIKE_GROUP:{uId}:{groupId}";
        /**
         * 成为商家锁
         */
        String TO_BE_SELLER = "to_be_seller:";

        /**
         * 点赞锁
         */
        String LIKE_LOCK = "LIKE:LOCK:";

        /**
         * 关注锁
         */
        String FOLLOW_LOCK = "FOLLOW:LOCK:";

        /**
         * 动态点赞收藏锁
         */
        String TOPIC_LIKE_LOCK = "TOPIC:LIKE:LOCK:TYPE{}:UID:{}:TOPICID{}";

        /**
         * 商家申诉锁
         */
        String SELLER_SETTLEMENT_LOCK_KEY = "seller:settlement:{deductNo}";

        /**
         * 发送验证码锁
         */
        String LOGIN_VC_CODE = "USER:VC_LOCK:";

        /**
         * 评论锁
         */
        String COMMENT_LOCK = "COMMENT:LOCK:";
    }

    public interface LockTime {

        /**
         * 竞价商品出价锁时间
         */
        int PRODUCT_BID_TIME = 5;
        int LIKE_MSG_GROUP = 30;
        /**
         * 点赞锁时间
         */
        Integer LIKE_LOCK_TIME = 3;
        /**
         * 关注锁时间
         */
        Integer FOLLOW_LOCK_TIME = 3;

        /**
         * 登录验证码锁时间
         */
        Integer LOGIN_VC_CODE = 3;
    }

    public interface Seller {
        /**
         * 商家全部类目保证金锁
         */
        String CATE_BAIL_ALL_LOCK = "CATE_BAIL_ALL_LOCK:{storeId}";
        /**
         * 年费支付回调锁
         */
        String YEAR_FEE_CALLBACK_LOCK = "YEAR_FEE_CALLBACK_LOCK:{storeId}";

        /**
         * 食品经营许可需要的一级类目Id
         */
        Integer FOOD_FIRST_CATEGORY_ID = 36;

        /**
         * 古物一级类目ID
         */
        Integer ANTIQUE_FIRST_CATEGORY = 9999;

        /**
         * 店铺积分增加
         */
        String STORE_POINTS_ADD = "STORE_POINTS_ADD:{storeId}";
        int STORE_POINTS_ADD_LOCK_TIME = 30;
        String PRODUCT_REDIS_PRE = "HWCS:PRODUCT:";

        String SELLER_REDIS_PRE = "HWCS:SELLER:";

        /**
         * 商家停用营业执照拉黑
         */
        String BUS_LICENSE_BLACKLIST = "BUS_LICENSE_BLACKLIST";

        /**
         * 重复上架限制次数
         */
        String REPEAT_ON_SHELF = "REPEAT_ON_SHELF";

        /**
         * 商品发布锁
         */
        String PRODUCT_CREATE_LOCK = "PRODUCT_CREATE_LOCK:";
        /**
         * 商品发布上架锁  ms
         */
        Long PRODUCT_CREATE_LOCK_tIME = 1000l;

        /**
         * app展示类目
         */
        String ALL_CATEGORY_FOR_APP = "ALL_CATEGORY_FOR_APP";

        /**
         * 商家已预警商家
         */
        String SELLER_ALREADY_WARNING = "SELLER_ALREADY_WARNING:";

        /**
         * 商家开启倒计时已预警商家
         */
        String SELLER_OPEN_STORE_ALREADY_WARNING = "SELLER_OPEN_STORE_ALREADY_WARNING:";

        /**
         * 店铺年费缴费预警
         */
        String STORE_YEAR_FEE_WARNING = "STORE_YEAR_FEE_WARNING:";

        /**
         * 店铺停用key
         */
        String STORE_DISABLE = "STORE_DISABLE:";
        /**
         * 商家停用key
         */
        String SELLER_DISABLE = "SELLER_DISABLE:";

        /**
         * 商家暂停  使用set存redis    判断如果没有key才可以发布
         */
        String STORE_PAUSE = "STORE_PAUSE:";

        /**
         * 竞价商品延时消息key
         */
        String PRODUCT_DELAY_BIDDING_OVER = "PRODUCT_DELAY_BIDDING_OVER:";

        /**
         * 竞拍默认追加时间
         * 当用户在最后一个时间点卡拍卖，则需要在这个基础之上增加一个时间参数，给与其他用户进行追拍；
         */
        String PRODUCT_BIDDING_ADD_TIME = "product_bidding_add_time";

        /**
         * 保证金上限额度
         * 商家设置单个拍品最高的保证金上线额度
         */
        String PRODUCT_BAIL_UP_LIMIT = "product_bail_up_limit";

        /**
         * 竞拍时间周期设置
         * 设置明确的几个时间周期让用户直接选择竞拍的时间周期；
         */
        String PRODUCT_BIDDING_SCHEDULE_TIME = "product_bidding_schedule_time";

        /**
         * 竞拍即将结束预警
         * 设置时间参数，用户提取即将到期的竞拍商品，可形成商品list给与运营使用；
         */
        String PRODUCT_BIDDING_END_WARNING = "product_bidding_end_warning";

        /**
         * 类目竞价交易手续费
         * 每个类目达成交易后，需要缴纳交易手续费，基于成交价的百分比进行支付
         */
        String PRODUCT_CATEGORY_BIDDING_FEE = "product_category_bidding_fee";

        /**
         * 类目一口价交易手续费
         * 每个类目达成交易后，需要缴纳交易手续费，基于成交价的百分比进行支付
         */
        String PRODUCT_CATEGORY_SALE_FEE = "product_category_sale_fee";

        /**
         * 商家店铺开启倒计时
         * 商家完成审核后，需要在一定时间内开启店铺，才可以保证不被驳回商家认证；
         */
        String SELLER_STORE_OPEN_COUNTDOWN = "seller_store_open_countdown";

        /**
         * 商家店铺保证金不足预警
         * 当商家保证金低于x%的情况下，预警提醒
         */
        String SELLER_STORE_BAIL_LACK_WARNING = "seller_store_bail_lack_warning";

        /**
         * 商家认证即将到期提醒
         * 当商家认证周期低于X天的时候，提示预警
         */
        String SELLER_YEAR_FEE_EXPIRE_WARNING = "seller_year_fee_expire_warning";

        /**
         * 违约率
         * 违约率高于30%，则出价时，系统强制要求缴纳保证金，否则无法出价，待竞拍结束，或本人中拍且付款后，保证金全额退款，
         */
        String DEFAULT_RATE = "default_rate";

        /**
         * 竞拍保证金
         * 违约率达到X%的情况下，竞拍任何商品都需要缴纳竞拍保证金
         * 毁拍值较高的用户在参与任何竞拍的情况下，都需要缴纳保证金，如商品已经有保证金，则取商品自有保证金金额，如没有设置，则取该参数；
         */
        String PRODUCT_BIDDING_BAIL_AMOUNT = "product_bidding_bail_amount";

        /**
         * 店铺当日统计时间
         */
        String STORE_REPORT_TIME = "store_report_time";
    }

    public interface ProductBid {

        /**
         * 竞价商品出价锁时间
         */
        String HIGHEST_BID = "product:highest:bid:spuId:";
    }

    public interface Third {
        /**
         * 移动端oss上传sts授权信息
         */
        String APP_OSS_STS_INFO = "APP_OSS_STS_INFO:{ticket}";
        /**
         * 待确认的图片
         */
        String NETEASE_YI_DUN_CHECK_IMG_MAP = "NETEASE_YI_DUN_CHECK_IMG_MAP:{date}";
    }

    public interface Premission {

        /**
         * 用户的权限key
         */
        String PERMISSIONS = "MANAGE:PERMISSION:";

        /**
         * 用户的权限key
         */
        String PERMISSIONS_USER = "MANAGE:PERMISSION:USER:";

        /**
         * 权限code key
         */
        String PERMISSIONS_MENU_CODE = "MANAGE:PERMISSION:MENU";

        /**
         * 权限访问路径key
         */
        String PERMISSIONS_PATH = "MANAGE:PERMISSION:PATH";

    }

    public interface Banner {

        String BANNER = "BANNER:TYPE:";

    }
}
