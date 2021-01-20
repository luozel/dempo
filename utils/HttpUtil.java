package com.dev.testsos.sosgroup.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * HTTP工具类
 *
 * @Author: JiaXinChen
 * @Date: Create in 20:11 2018/5/23
 */
@Slf4j
public class HttpUtil {


    private HttpUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取当前网络ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * URL名字编码
     *
     * @param name
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String toUrlName(String name) {
        String urlName = name;
        try {
            urlName = new String(name.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlName;
    }

    /**
     * post表单提交
     *
     * @param url    地址
     * @param params 参数
     * @return 执行结果
     */
    public static JSONObject postOfForm(String url, LinkedMultiValueMap<String, Object> params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity requestEntity = new HttpEntity<>(params, headers);
        //  执行HTTP请求
        String body = client.postForEntity(url, requestEntity, String.class).getBody();
        return new JSONObject(body);
    }

    /**
     * postJSON提交
     *
     * @param url
     * @param params
     * @return
     */
    public static JSONObject postOfJson(String url, Map<String, Object> params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity formEntity = new HttpEntity(new JSONObject(params).toString(), headers);
        String body = client.postForEntity(url, formEntity, String.class).getBody();
        return new JSONObject(body);
    }

    public static com.alibaba.fastjson.JSONObject postOfForm(String url, Object params, HttpHeaders headers) {
        com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject) JSON.toJSON(params);
        log.debug("【POST传输的信息】" + jsonObject.toJSONString());
        Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
        MultiValueMap<String, Object> paramsMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : entrySet) {
            paramsMap.add(entry.getKey(), entry.getValue());
        }
        RestTemplate client = new RestTemplate();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity requestEntity = new HttpEntity<>(paramsMap, headers);
        //  执行HTTP请求
        String body;
        try {
            body = client.postForEntity(url, requestEntity, String.class).getBody();
        } catch (HttpClientErrorException e) {
            body = e.getResponseBodyAsString();
        }
        return com.alibaba.fastjson.JSONObject.parseObject(body);
    }

    /**
     * get请求
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @param splitsParams  拼接参数
     * @return
     */
    public static JSONObject get(String url, Map<String, Object> requestParams, Object... splitsParams) {
        RestTemplate restTemplate = new RestTemplate();
        //region 拼接请求参数
        if (requestParams != null && requestParams.size() > 0) {
            StringBuilder sb = new StringBuilder(url).append("?");
            int time = 1;
            for (Map.Entry<String, Object> entry : requestParams.entrySet()) {
                if (time >= 2) {
                    sb.append("&");
                }
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                time++;
            }
            url = sb.toString();
        }
        //endregion
        String string = restTemplate.getForObject(url, String.class, splitsParams);
        log.debug("【GET请求返回结果】：{}", string);
        return new JSONObject(string);
    }

    /**
     * get请求
     *
     * @param url 请求地址
     * @return
     */
    public static JSONObject get(String url) {
        return HttpUtil.get(url, null);
    }

    public static com.alibaba.fastjson.JSONObject get(
            String url, Map<String, Object> requestParams, Map<String, String> requestHeaders) {
        RestTemplate restTemplate = new RestTemplate();
        // region 拼接请求参数
        if (requestParams != null && requestParams.size() > 0) {
            StringBuilder sb = new StringBuilder(url).append("?");
            int time = 1;
            for (Map.Entry<String, Object> entry : requestParams.entrySet()) {
                if (time >= 2) {
                    sb.append("&");
                }
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                time++;
            }
            url = sb.toString();
        }
        // endregion
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAll(requestHeaders);
        HttpEntity<String> formEntity = new HttpEntity<>(null, headers);
        String string;
        try {
            string = restTemplate.exchange(url, HttpMethod.GET, formEntity, String.class).getBody();
        } catch (HttpClientErrorException e) {
            string = e.getResponseBodyAsString();
        }
        log.debug("【GET请求返回结果】：{}", string);
        return com.alibaba.fastjson.JSONObject.parseObject(string);
    }

    public static String getString(String url, Map<String, Object> requestParams, Map<String, String> requestHeaders) {
        RestTemplate restTemplate = new RestTemplate();
        // region 拼接请求参数
        if (requestParams != null && requestParams.size() > 0) {
            StringBuilder sb = new StringBuilder(url).append("?");
            int time = 1;
            for (Map.Entry<String, Object> entry : requestParams.entrySet()) {
                if (time >= 2) {
                    sb.append("&");
                }
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                time++;
            }
            url = sb.toString();
        }
        // endregion
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAll(requestHeaders);
        HttpEntity<String> formEntity = new HttpEntity<>(null, headers);
        String string;
        try {
            string = restTemplate.exchange(url, HttpMethod.GET, formEntity, String.class).getBody();
        } catch (HttpClientErrorException e) {
            string = e.getResponseBodyAsString();
        }
        log.debug("【GET请求返回结果】：{}", string);
        return string;
    }


    public static void main(String[] args) {
        String code = HttpUtil.getString("http://www.hi-watch.com.cn/hiwatchclient/setWatch.htm?deviceid=626160003689268&s_heart=10&s_ox=5&s_hp=10&s_lp=10&s_tp=0.2",null,new HashMap<>());
        System.out.println(code);
    }

    /**
     * @param IP
     * @return
     */
    public static String GetAddressByIp(String IP) {
        String resout = "";
        try {
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + IP);

            System.out.println(str);


            MyMapUtil.MyMap obj = MyJsonUtil.stringToObject(str, MyMapUtil.MyMap.class);
            Map<String, String> obj2 = (Map<String, String>) obj.get("data");
            String code = String.valueOf(obj.get("code"));
            if (code.equals("0")) {

                resout = obj2.get("country") + "--" + obj2.get("area") + "--" + obj2.get("city") + "--" + obj2.get("isp");
            } else {
                resout = "IP地址有误";
            }
        } catch (Exception e) {

            e.printStackTrace();
            resout = "获取IP地址异常：" + e.getMessage();
        }
        return resout;

    }

    public static String getJsonContent(String urlStr) {
        try {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

}
