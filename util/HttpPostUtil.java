package com.haier.neusoft.o2o.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.haier.neusoft.o2o.common.model.Dtddriver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * <p>Description: [http请求工具类]</p>
 * Created on 2012-6-26
 * @author  <a href="mailto: zh-fan@neusoft.com">zh-fan</a>
 * @version 1.0
 */
@Slf4j
public class HttpPostUtil {

    /**
     * httpClient虚拟httpPost
     * @param url  要访问的url 例如："www.baidu.com"
     * @param params 要传输的参数 NameValuePair[]类型
     * @return 访问地址传回的结果,包含status，error，data 3个字段
     * @throws Exception
     */
    public static JSONObject httpPost(String url,String params){
       // HttpClient client = new HttpClient();
        JSONObject result=new JSONObject();
//        client.getHostConfiguration().setHost(url, 8080, protocol);
        // 使用POST方式提交数据

//       params[0].setValue(dataStr);
//        HttpMethod method = getPostMethod(action,params);
//        client.executeMethod(method);
//        // 打印服务器返回的状态
//        System.out.println(method.getStatusLine());
//        // 打印结果页面
//        String responseStr = new String(method.getResponseBodyAsString());
//        // 打印返回的信息
//        method.releaseConnection();
        //定义要返回的数据
        try {
            String responseStr = toDtdPost(url,params);
            //返回数据转化为json
            JSONObject responseJson= JSON.parseObject(responseStr);
            String status = responseJson.get("status").toString();
            if("200".equals(status)){
                JSONObject data = JSON.parseObject(responseJson.get("data").toString());
                Object success = data.get("success");
                if(success == null){
                    result.put("status","F");
                }else if ("true".equals(success.toString().toLowerCase())){
                    result.put("status","S");
                }else{
                    result.put("status","F");
                }
                result.put("error",data.get("error"));
            }else{
                result.put("status","E");
                result.put("error","连接失败！ status:"+status);
            }
        }catch(JSONException e1){
            result.put("status","E");
            result.put("error",e1.getMessage());
        }catch(Exception e) {
            result.put("status","E");
            result.put("error",e.getMessage());
        }
        return result;
    }

    /**
     * 使用POST方式提交数据
     * @return
     */
//    private static HttpMethod getPostMethod(String url,NameValuePair[] params) {
//        PostMethod post = new UTF8PostMethod(url);
//        post.setRequestHeader("Content-Type","html/xml;charset=utf-8");
//        post.setRequestBody(params);
//        return post;
//    }
    //Inner class for UTF-8 support
//    public static class UTF8PostMethod extends PostMethod {
//        public UTF8PostMethod(String url){
//            super(url);
//        }
//        @Override
//        public String getRequestCharSet() {
//            //return super.getRequestCharSet();
//            return "UTF-8";
//        }
//    }

    /**
     * 封装cdk请求xml
     *
     * @param json
     * @param notifyType
     *            接口名
     * @return
     */
//    public static String getCdkXml(String json, String notifyType) {

/*
        StringBuffer sb = new StringBuffer();
        sb.append("<cdkdriver>");
        sb.append("<spCode>" + Constant.CDK_CONFIG.SP_CODE + "</spCode>");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String sysDate = dateFormat.format(c.getTime());

        SimpleDateFormat dateFormatDefault = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String sysDateDefault = dateFormatDefault.format(c.getTime());
        String notify_id = Constant.CDK_CONFIG.SP_CODE + sysDate
                + getRandomString(8);
        sb.append("<notifyId>" + notify_id + "</notifyId>");
        sb.append("<notifyType>" + notifyType + "</notifyType>");
        sb.append("<notifyTime>" + sysDateDefault + "</notifyTime>");
        // sb.append("<sign>"
        // + encodeBase64Str(MD5util.Md5(notify_id
        // + Constant.CDK_CONFIG.SP_CODE)) + "</sign>");
        sb.append("<sign>"
                + encodeBase64Str(DigestUtils.md5Hex(notify_id
                + Constant.CDK_CONFIG.SP_CODE)) + "</sign>");
        sb.append("<content>" + json + "</content>");
        sb.append("</cdkdriver>");
        String xml = URL.encode(sb.toString());
        xml = encodeBase64Str(xml.replaceAll("\r|\n|\t", ""));

        return xml;
return "";*/
//    }
    /**
     * Base64解密CDK请求
     *
     * @param xml
     * @return
     */
//    public static String decodeBase64Str(String xml) {
//        byte[] xmlBase64 = Base64.decodeBase64(xml.getBytes());
//        String base64Toxml = "";
//        try {
//            base64Toxml = new String(xmlBase64, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return base64Toxml;
//    }

    /**
     * Base64加密
     *
     * @param xml
     * @return
     */
//    public static String encodeBase64Str(String xml) {
//        byte[] base64xml = Base64.encodeBase64(xml.getBytes());
//        String xmlToBase64 = "";
//        try {
//            xmlToBase64 = new String(base64xml, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return xmlToBase64;
//    }

    /**
     * 随机获取字符串（带字母）
     *
     * @param length
     *            随机字符串长度
     *
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        if (length <= 0) {
            return "";
        }
        char[] randomChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's',
                'd', 'f', 'g', 'h', 'j', 'k', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(randomChar[Math.abs(random.nextInt(randomChar.length - 1))
                    % randomChar.length]);
        }
        return stringBuffer.toString();
    }

    public static String toDtdPost(String url,String data)  {
        String result = "";
        Dtddriver driver=new Dtddriver();
        driver.setContent(data);
        driver.setSpCode("EHAIER");
        driver.setNotifyId(driver.getSpCode()+CommonTool.getCurrentDate("yyyyMMddHHmmss")+getRandomString(8));
        String sign="";
        sign = Md5.getMd5(driver.getNotifyId() + driver.getSpCode());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormatDefault = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        String sysDateDefault = dateFormatDefault.format(c.getTime());
        driver.setNotifyTime(sysDateDefault);
            driver.setSign(sign);
            String postdate = "<dtddriver><spCode>"+driver.getSpCode()+"</spCode><notifyId>"+driver.getNotifyId()+"</notifyId><notifyType>"+driver.getNotifyType()+"</notifyType><notifyTime>"+driver.getNotifyTime()+"</notifyTime><sign>"+driver.getSign()+"</sign><content>"+driver.getContent()+"</content></dtddriver>";
        try {
            String in = Base64.encodeBase64String(postdate.replaceAll("\r|\n|\t", "").getBytes());
            result = HttpRequestUtil.send(url, in, "application/x-www-form-urlencoded;charset=utf-8");
        }catch(IOException e1){
            log.error(e1.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        //  String to = HttpRequestUtil.send(urlstr,postDateenCode,"application/x-www-form-urlencoded;charset=utf-8");
           // postResult = JSON.parseObject(to);

        return result;
    }


    public static String encode(String value) {
        if (value == null || value.length() == 0) {
            return "";
        }
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


}
