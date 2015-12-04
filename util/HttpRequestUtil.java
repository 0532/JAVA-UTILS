package com.haier.neusoft.o2o.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>Description: [http请求工具类]</p>
 * Created on 2012-6-26
 * @author  <a href="mailto: zh-fan@neusoft.com">zh-fan</a>
 * @version 1.0
 */
public class HttpRequestUtil {

	/**
	 * <p>Discription:[发doPost请求]</p>
	 * @param destUrl
	 * @param postData
	 * @return
	 * @throws Exception
	 * @author:[zh-fan]
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static String send(String destUrl, String postData,String type) throws Exception {
		String recString = "";
		URL url = null;
		HttpURLConnection urlconn = null;
		url = new URL(destUrl);
		urlconn = (HttpURLConnection) url.openConnection();
		//urlconn.setRequestProperty("content-type",type);
		urlconn.setRequestMethod("POST");
		urlconn.setDoInput(true);
		urlconn.setDoOutput(true);
		OutputStream out = urlconn.getOutputStream();
		out.write(postData.getBytes("UTF-8"));

		out.flush();
		out.close();

		BufferedReader rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer();
		int ch;
		while ((ch = rd.read()) > -1) {
			sb.append((char) ch);
		}
		recString = sb.toString();
		rd.close();
		urlconn.disconnect();

		return recString;
	}
    public static String sendGet(String destUrl,String type) throws Exception {
        String recString = "";
        URL url = null;
        HttpURLConnection urlconn = null;
        url = new URL(destUrl);
        urlconn = (HttpURLConnection) url.openConnection();
        urlconn.setRequestProperty("content-type",type);
        urlconn.setRequestMethod("GET");
        urlconn.setDoInput(true);
        urlconn.setDoOutput(true);
        BufferedReader rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));
        StringBuffer sb = new StringBuffer();
        int ch;
        while ((ch = rd.read()) > -1) {
            sb.append((char) ch);
        }
        recString = sb.toString();
        rd.close();
        urlconn.disconnect();

        return recString;
    }
}
