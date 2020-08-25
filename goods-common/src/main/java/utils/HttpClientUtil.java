package utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.*;

/**
 * Copyright © 2017 金科教育. All rights reserved. <br>
 * 类: HttpClient <br>
 * 描述: httpclient工具类 <br>
 * 作者: Teacher song<br>
 * 时间: 2017年7月21日 下午3:15:27
 */
public class HttpClientUtil {

    static CloseableHttpClient client = null;

    static {
        client = HttpClients.createDefault();
    }

    /**
     * 方法: get <br>
     * 描述: get请求 <br>
     * 作者: Teacher song<br>
     * 时间: 2017年7月21日 下午3:15:25
     *
     * @param url
     * @param params
     * @return
     * @throws
     */
    public static String get(String url, HashMap<String, Object> params) throws Exception {
        HttpGet httpGet = new HttpGet();
        Set<String> keySet = params.keySet();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(url).append("?t=").append(System.currentTimeMillis());
        for (String key : keySet) {
            stringBuffer.append("&").append(key).append("=").append(params.get(key));
        }
        httpGet.setURI(new URI(stringBuffer.toString()));
        CloseableHttpResponse execute = client.execute(httpGet);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (200 != statusCode) {
            return "";
        }
        return EntityUtils.toString(execute.getEntity(), "utf-8");
    }

    /**
     * 方法: get2 <br>
     * 描述: 斜杠传参 <br>
     * 作者: songxj 金科教育 <br>
     * 时间: 2019年7月10日 下午3:29:01
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String get2(String url, String params) throws Exception {
        HttpGet httpGet = new HttpGet();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(url).append("/").append(params);
        httpGet.setURI(new URI(stringBuffer.toString()));
        CloseableHttpResponse execute = client.execute(httpGet);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (200 != statusCode) {
            return "";
        }
        return EntityUtils.toString(execute.getEntity(), "utf-8");
    }

    /**
     * 方法: post <br>
     * 描述: post请求 <br>
     * 作者: Teacher song<br>
     * 时间: 2017年7月21日 下午3:20:31
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String post(String url, HashMap<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(new URI(url));
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            NameValuePair e = new BasicNameValuePair(key, params.get(key).toString());
            parameters.add(e);
        }
        HttpEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse execute = client.execute(httpPost);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (200 != statusCode) {
            return "";
        }
        return EntityUtils.toString(execute.getEntity(), "utf-8");
    }

    /**
     * 方法: post <br>
     * 描述: 网易短信接口请求 <br>
     * 作者: songxj 金科教育 <br>
     * 时间: 2019年7月10日 下午3:29:21
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws Exception
     */
    public static String post(String url, HashMap<String, Object> params, HashMap<String, Object> headers) throws Exception {
        HttpPost httpPost = new HttpPost();
        Set<String> keySet2 = headers.keySet();
        Iterator<String> iterator = keySet2.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = headers.get(key).toString();
            httpPost.addHeader(key, value);
        }

        httpPost.setURI(new URI(url));
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            NameValuePair e = new BasicNameValuePair(key, params.get(key).toString());
            parameters.add(e);
        }
        HttpEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse execute = client.execute(httpPost);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (200 != statusCode) {
            return "";
        }
        return EntityUtils.toString(execute.getEntity(), "utf-8");
    }

    /**
     * 请求参数为json字符串
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String postJson(String url, HashMap<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(new URI(url));
        String jsonString = JSON.toJSONString(params);
        StringEntity stringEntity = new StringEntity(jsonString, "utf-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");//发送json数据需要设置contentType
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse execute = client.execute(httpPost);
        int statusCode = execute.getStatusLine().getStatusCode();
        if (200 != statusCode) {
            return "";
        }
        return EntityUtils.toString(execute.getEntity(), "utf-8");
    }

    /**
     * postJson使用示例代码
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8081/express/findProductList.do";
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> searchinfo = new HashMap<String, Object>();
        searchinfo.put("productType", "手机测试");
        params.put("searchinfo", searchinfo);
        params.put("page", 2);

        String postJson = HttpClientUtil.postJson(url, params);
        System.out.println(postJson);
    }
}
