package com.dev.testsos.sosgroup.utils;


import lombok.Data;
import lombok.Getter;

import java.util.HashMap;

/**
 * @author 陈嘉欣
 * @date 2018/9/19 11:46
 **/
public class ResultUtil {

    /**
     * 自定义消息状态
     *
     * @param code 状态码
     * @param msg  状态信息
     * @return 封装结果
     */
    public static ResultUtil.ResultVO custom(Integer code, String msg) {
        return new ResultUtil.ResultVO(code, msg);
    }

    /**
     * 自定义消息状态
     *
     * @param statusEnum 状态枚举
     * @return 封装结果
     */
    public static ResultUtil.ResultVO custom(ResultEnum statusEnum) {
        return custom(statusEnum.getCode(), statusEnum.getMessage());
    }

    /**
     * 自定义消息状态
     *
     * @return 封装结果
     * @param: statusEnum 状态枚举 extend扩展信息（在msg 后进行拼接）
     * @author 杰峰
     * @date 2018/12/26 11:57
     */
    public static ResultUtil.ResultVO custom(ResultEnum statusEnum, String extend) {
        return custom(statusEnum.getCode(), statusEnum.getMessage() + extend);
    }

    /**
     * 操作成功
     *
     * @return 封装结果
     */
    public static ResultUtil.ResultVO success() {
        return custom(ResultEnum.SUCCESS);
    }

    /**
     * 操作成功
     *
     * @param data 成功时返回的数据
     * @return 封装结果
     */
    public static ResultUtil.ResultVO success(Object data) {
        return custom(ResultEnum.SUCCESS).setData(data);
    }

    public static ResultUtil.ResultVO error(ResultEnum tip, Exception e) {
        return custom(tip).setErr(e.getMessage());
    }

    /**
     * 操作数据状态
     *
     * @param expr 处理结果条数是否满足需求表达式 如 num > 0
     * @return 封装结果
     */
    public static ResultUtil.ResultVO status(boolean expr) {
        if (expr) {
            return success();
        } else {
            return custom(ResultEnum.OPT_FAIL);
        }
    }

    /**
     * 返回自定义信息
     *
     * @param expr
     * @param successResult
     * @param failResult
     * @return
     */
    public static ResultUtil.ResultVO status(boolean expr, ResultUtil.ResultVO successResult, ResultUtil.ResultVO failResult) {
        if (expr) {
            return successResult;
        } else {
            return failResult;
        }
    }

    public static ResultUtil.ResultVO tip(String tip) {
        return custom(ResultEnum.MISS_MUST_COND).setMsg(tip);
    }

    @Getter
    public enum ResultEnum {

        /**
         * 未登录
         */
        UN_LOGIN(-999, "未登录"),
        /**
         * 操作失败
         */
        OPT_FAIL(-2, "操作失败"),
        /**
         * 未知错误
         */
        UN_KNOW_ERROR(-1, "未知错误"),
        /**
         * 操作成功
         */
        SUCCESS(1, "操作成功"),
        /**
         * 数据库异常
         */
        DATABASE_ERROR(47, "数据开了小差"),
        /**
         * 缓存未开启
         */
        REDIS_IS_NOT_OPEN(57, "未开启缓存服务器"),
        /**
         * 缺少必要条件
         */
        MISS_MUST_COND(1001, "缺少必要条件"),
        /**
         * 操作频繁
         */
        OPT_FREQUENT(1002, "操作频繁"),
        /**
         * 密码错误
         */
        PWD_ERROR(1003, "密码错误"),
        /**
         * 验证码错误
         */
        PIN_ERROR(1004, "验证码错误"),
        /**
         * 违背数据完整性约束
         */
        DATA_HAS_BE_USED(1005, "违背数据完整性约束"),
        /**
         * 手机号已注册
         */
        EXIST_PHONE(1006, "该手机号已被绑定"),
        /**
         * 账户不存在
         */
        NO_USER(1008, "账户不存在"),
        /**
         * 验证码失效
         */
        PIN_INVALID(1009, "验证码已失效"),
        /**
         * 短信发送失败
         */
        SEND_SMS_FAIL(1010, "短信发送失败"),
        /**
         * 该帐号已存在
         */
        ACCOUNT_EXIST(1013, "该帐号已存在"),
        /**
         * 该角色已存在
         */
        ROLE_EXIST(1014, "该角色已存在"),
        /**
         * 没有权限
         */
        NOT_POWER(1015, "权限不足"),
        /**
         * 空指针异常
         */
        NULL_ERROR(1018, "空指针异常"),
        /**
         * 非法操作
         */
        ILLEGAL_OPERATION(1019, "非法操作"),
        /**
         * 缺少必要参数
         */
        MISSING_PARAMETER(1021, "缺少必要参数"),
        /**
         * 参数不能为空
         */
        PARAMETER_NOT_BLANK(1022, "参数不能为空"),
        /**
         * 文件异常
         */
        FILE_EXCEPTION(2000, "文件异常"),
        /**
         * 数据不存在
         */
        NOT_EXIST_DATA(2002, "数据不存在"),
        /**
         * 数据已存在
         */
        EXIST_DATA(2003, "数据已存在"),
        /**
         * 不是图片
         */
        NOT_PICTURE(2004, "不是图片"),
        /**
         * 文件超过限制大小
         */
        FILE_OVER_LIMIT(2005, "文件超过限制大小"),
        /**
         * 文本超过限制大小
         */
        TEXT_OVER_LIMIT(2006, "文本超过限制大小"),
        /**
         * 冻结帐号
         */
        FROZEN_ACCOUNT(3001, "冻结的帐号"),
        /**
         * 不在权限范围内
         */
        NOT_IN_RANGE(4001, "不在权限范围内"),
        /**
         * 微信登录失败
         */
        WX_LOGIN_FAIL(4002, "微信登录失败"),
        /**
         * 获取手机验证码过于频繁
         */
        GET_PHONE_PIN_FREQUENT(4003, "获取验证码过于频繁"),
        /**
         * 不在操作范围内
         */
        NOT_IN_OPT_RANGE(4004, "不在操作范围内"),
        /**
         * 超时操作
         */
        OVER_TIME(4005, "超时操作"),
        /**
         * 不支持文件类型
         */
        NOT_SUPPORT_FILE(5001, "不支持的文件类型"),
        /**
         * 总条数为0
         */
        NOT_TOTAL(6000, "总条数为0"),
        /**
         * 这一页没有数据
         */
        NOT_PAGESIZE(6001, "这一页没有数据"),
        /**
         * 手机号码格式错误
         */
        ERROR_TYPE_PHONE(6002, "手机号码格式错误"),
        /**
         * 套餐费率错误
         */
        ERROR_RATES(6003, "套餐费率错误"),
        /**
         * 提现金额不足
         */
        ERROE_MONEY(6004, "提现金额不足"),
        /**
         * 用户名或密码错误
         */
        ERROR_USER(6004, "用户名或密码错误"),
        /**
         * 冻结账号freeze
         */
        FREEZE_ACC(6005, "账号被冻结，请联系管理员"),
        /**
         * 修改套餐失败
         */
        ERROR_PAY(6005, "定时器执行错误"),
        /**
         * 该客户联系电话已经被绑定
         */
        HAVING_PHONE(6006,"该客户联系电话已经被绑定"),
        /**
         *该身份证的客户已是客户/或已经有人提交了申请审核，无法新增
         */
        HAVENG_IDCARD(6007,"该身份证的客户已是客户/或已经有人提交了申请审核，无法新增"),
        /**
         * 身份证格式错误
         */
        ERROR_IDCARD(6008,"身份证错误"),
        /**
         * 年龄错误
         */
        ERROR_AGE(6009,"年龄错误"),
        /**
         * 性别错误
         */
        ERROR_SEX(6010,"性别错误"),
        /**
         * 手机号码不能重复
         */
        NOT_REPEAT_PHONE(6011,"手机号码不能重复"),
        /**
         * 生日错误
         */
        ERROR_BIRTHDAY(1011,"生日错误"),
        /**
         * 套餐不存在或者暂停了
         */
        ERROR_BUNDLE(6110,"套餐不存在或者暂停了"),
        /**
         * 套餐可用余额不足
         */
        NOT_MONEY(6111,"套餐可用余额不足"),
        /**
         * 减少金额失败
         */
        ERROR_MONEY(6112,"减少金额失败"),
        /**
         * 呼叫中心的金额不够
         */
        NOT_ERROR_MONEY(6113,"呼叫中心的金额不够"),
        /***
         * 修改以前月数据失败
         */
        ERROR_DATA_BEFORE_MONTH(6114,"修改以前月数据失败"),
        /**
         * 订单的月份没有统计statistics
         */
        NOT_STATISTICS_BY_MONTY(6115,"订单的月份没有统计"),
        /**
         * 订单的月份的冻结金额不够扣 frozenAmount
         */
        NOT_FROZENAMOUT_BY_MONTY(6116,"订单的月份的冻结金额不够"),
        /**
         * 没有超管
         */
        NOT_ADMIN(6117,"没有超管"),
        /**
         * 身份证姓名错误
         */
        NOT_IDNO_NAME(5004, "身份证姓名错误"),
        /**
         * 开户名不能包含特殊字符
         */
        ERROR_NAME(8001, "开户名不能包含特殊字符"),
        /**
         * 身份证号格式错误
         */
        ERROR_IDNO(8002, "身份证号格式错误"),
        /**
         * 无此身份证号码
         */
        NOT_IDNO(8003, "无此身份证号码"),
        /**
         * 身份证信息不匹配
         */
        ERROR_IDNO_NAME_INFO(8004, "身份证信息不匹配"),
        /**
         * 系统维护，请稍后再试
         */
        ERROR_IDNO_SYSTEM(8005, "系统维护，请稍后再试"),
        /**
         * 呼叫中心的钱包错误
         */
        ERROR_FIN_ACCOUNT(8006,"呼叫中心的钱包错误"),
        /**
         * 档案资料已经存在
         */
        ERROR_USSER_EXIT(8007,"档案资料已经存在"),
        /**
         * 老人类型必须选择
         */
        ERROR_FILE_TYPE(8008,"老人类型必须选择"),
        /**
         * 兜底套餐只能兜底老人选择
         */
        ERROR_GOV_SERVER(8009,"兜底套餐只能兜底老人选择"),
        /**
         * 没有主账号 primaryContactNumber
         */
        NO_PRIMARY_CONTACT_NUMBER(8010,"没有主账号"),
        /**
         * 来电号码的主账号没有钱（时间币）
         */
        NO_SHI_JIAN_BI(8011,"来电号码的主账号钱（时间币）不足或者没有"),
        /**
         * 没有这个订单
         */
        NO_ORDER(8012,"没有这个订单"),
        /**
         * 修改录音失败
         */
        ERROR_VIDEO_URL(8013,"修改录音失败"),
        /**
         * 添加来电记录失败  CallRecords
         */
        ERROR_ADD_CALL_RECORD(8014,"添加来电记录失败"),
        /**
         * 这个不是订单的录音
         */
        NO_ORDER_TYPE(8015,"这个不是订单的录音"),
        /**
         * 企话宝账号已经被使用了
         */
        USE_APIS(8016,"企话宝账号已经被使用了"),
        /**
         * 有企话包账号的时候密码不能为空
         */
        NOT_USER_APIS_PWD(8017,"有企话包账号的时候密码不能为空"),
        /**
         * 一次通话只能有一个通话记录id
         */
        ERROR_CALL_ID(8018,"一次通话只能有一个通话记录id"),
        /**
         * 操作成功和失败没有标识 whetherSucceed
         */
        NO_WHETHER_SUCCEED(8019,"操作成功和失败没有标识"),
        /**
         * 添加通话记录失败
         */
        ERROR_CALL_RECORDS(8020,"添加通话记录失败"),
        /**
         * 通话记录id已经被绑定了
         */
        ERROR_BINGD_CALL_ID(8021,"通话记录id已经被绑定了"),
        /**
         * 这个订单还没有通话结束
         */
        NO_END_CALL(8022,"这个订单还没有通话结束"),
        /**
         * 通话记录id已经被绑定了
         */
        NO_USE_APIS(8023,"请联系管理员分配企话宝账号"),
        /**
         * 企话宝账号已经被使用了
         */
        ERROR_USE(8024,"企话宝账号或密码错误"),
        /**
         * 电话没有挂断或者没有提交来电记录
         */
        ERROR_END(8025,"电话没有挂断或者没有提交来电记录"),
        /**
         * 确定订单的评分要在 1到5之间
         */
        ERROR_START_NUM(8026,"确定订单的评分要在 1到5之间"),
        /**
         * 不是电信卡
         */
        NOT_TELECOM(6606, "目前仅支持电信卡办理会员"),
        /**
         * 已经是会员了
         */
        IS_EXIST_VIP(8101, "该用户已经是会员了，如需修改资料，请拨打2212349热线进行资料修改。"),
        /**
         * 该号码已使用
         */
        IS_EXIST_PHONE(8101, "该用户手机号已经存在他人档案中，建议线下纸质登记办理，并提交资料给技术部。"),
        /**
         * 修改用户的资金账号失败
         */
        UPDATE_FINACCOUNT(6021, "修改用户的资金账号失败"),
        /**
         * 迁移流水失败 migrate
         */
        ERROR_FLOW_MIGRATE(6022, "迁移流水失败"),
        /**
         * 用户办理会员，修改用户的订单状态失败
         */
        ERROR_RANCHER_UPDATE_ORDER(6046, "用户办理会员，修改用户的订单状态失败"),
        /**
         * 生成预订单失败 Order Form
         */
        FAIL_ORDER_FORM(6033, "生成预订单失败"),
        /**
         * 4g設置失敗
         */
        ERROR_4G_SET(6034, "4g設置失败");
        private Integer code;
        private String message;

        ResultEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    @Data
    public static class ResultVO<T> {
        private Integer code;
        private String msg;
        private T data;
        private String err;

        public ResultVO(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public ResultUtil.ResultVO<T> setData(T data) {
            this.data = data;
            return this;
        }

        public T getData() {
            return this.data == null ? (T) new HashMap<String, Object>(0) : data;
        }

        public ResultUtil.ResultVO<T> setErr(String err) {
            this.err = err;
            return this;
        }

        public String getErr() {
            return this.err == null ? "" : this.err;
        }

        public ResultUtil.ResultVO<T> setMsg(String msg) {
            this.msg = msg;
            return this;
        }
    }
}
