package com.dev.testsos.sosgroup.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class IdCard {

    /**
     * 中国公民身份证号码最小长度。
     */
    public static final int CHINA_ID_MIN_LENGTH = 15;

    /**
     * 中国公民身份证号码最大长度。
     */
    public static final int CHINA_ID_MAX_LENGTH = 18;

    /**
     * 根据身份编号获取年龄
     *
     * @param idCard 身份编号
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        Calendar cal = Calendar.getInstance();
        String year = idCard.substring(6, 10);
        String month = idCard.substring(10, 12);
        String day = idCard.substring(12, 14);
        int iCurrYear = cal.get(Calendar.YEAR);
        int iCurrMonth = cal.get(Calendar.MONTH) + 1;
        int iCurrDay = cal.get(Calendar.DAY_OF_MONTH);
        iAge = iCurrYear - Integer.valueOf(year) - 1;
        if (Integer.parseInt(month) < iCurrMonth) {
            return iAge + 1;
        } else if (Integer.parseInt(month) == iCurrMonth) {
            if ((Integer.parseInt(day) < iCurrDay)||(Integer.parseInt(day) == iCurrDay)) {
                return iAge + 1;
            }
        }
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {


        return idCard.substring(6, 14);
    }

    /**
     *将字符串格式yyyyMMdd的字符串转为日期，格式"yyyy-MM-dd"
     *
     * @param date 日期字符串
     * @return 返回格式化的日期
     * @throws ParseException 分析时意外地出现了错误异常
     */
    public static String strToDateFormat(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            formatter.setLenient(false);
            Date newDate= formatter.parse(date);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(newDate);
        }catch (ParseException e){
        }
        return date;
    }
    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard 身份编号
     * @return 生日(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard 身份编号
     * @return 生日(dd)
     */
    public static Short getDateByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M - 男 ， F - 女 ， N - 未知)
     */
    public static String getGenderByIdCard(String idCard) {
        String sGender = "未知";

        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "1";//男
        } else {
            sGender = "2";//女
        }
        return sGender;
    }

    /**
     * 根据15位的身份证号码获得18位身份证号码
     *
     * @param fifteenIDCard 15位的身份证号码
     * @return 升级后的18位身份证号码
     * @throws Exception 如果不是15位的身份证号码，则抛出异常
     */
    public static String getEighteenIDCard(String fifteenIDCard) throws Exception {
        if (fifteenIDCard != null && fifteenIDCard.length() == 15) {
            StringBuilder sb = new StringBuilder();
            sb.append(fifteenIDCard.substring(0, 6))
                    .append("19")
                    .append(fifteenIDCard.substring(6));
            sb.append(getVerifyCode(sb.toString()));
            return sb.toString();
        } else {
            throw new Exception("不是15位的身份证");
        }
    }

    /**
     * 获取校验码
     *
     * @param idCardNumber 不带校验位的身份证号码（17位）
     * @return 校验码
     * @throws Exception 如果身份证没有加上19，则抛出异常
     */
    public static char getVerifyCode(String idCardNumber) throws Exception {
        if (idCardNumber == null || idCardNumber.length() < 17) {
            throw new Exception("不合法的身份证号码");
        }
        char[] Ai = idCardNumber.toCharArray();
        int[] Wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] verifyCode = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int S = 0;
        int Y;
        for (int i = 0; i < Wi.length; i++) {
            S += (Ai[i] - '0') * Wi[i];
        }
        Y = S % 11;
        return verifyCode[Y];
    }

    /**
     * 校验18位的身份证号码的校验位是否正确
     *
     * @param idCardNumber 18位的身份证号码
     * @return
     * @throws Exception
     */
    public static boolean verify(String idCardNumber) throws Exception {
        if (idCardNumber == null || idCardNumber.length() != 18) {
            throw new Exception("不是18位的身份证号码");
        }
        return getVerifyCode(idCardNumber) == idCardNumber.charAt(idCardNumber.length() - 1);
    }

    /**
     * 判断身份证号码是否正确。
     *
     * @param code 身份证号码。
     * @return 如果身份证号码正确，则返回true，否则返回false。
     */
    public static boolean isIdNum(String code) {

        return IDCardUtil.IDCardValidate(code);
    }
    private static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(getAgeByIdCard("441324194804145319"));
    }
}
