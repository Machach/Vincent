package com.example.machachong.youdu.network.http;

/**
 *
 * 所有请求地址
 * @data:18/12/9
 *
 */
public class HttpConstants {
    /**
     * 方便参数修改
     */

    private static final String ROOT_URL = "http://imooc.com/api";
    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "/product/home_recommand.php";

    /**
     * 版本更新接口
     */
    public static String CHECK_UPDATE = ROOT_URL + "/config/check_update.php";

    /**
     * 登錄接口
     */
    public static String LOGIN = ROOT_URL + "/config/login.php";

    /**
     * 课程详情接口
     */
    public static String COURSE_DETAIL = ROOT_URL + "/product/course_detail.php";
}
