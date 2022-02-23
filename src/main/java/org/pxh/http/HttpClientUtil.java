package org.pxh.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName httpClientUtil
 * @Description
 * @Author pxh
 * @Date 2021/12/17 9:25
 * @Version
 */
public class HttpClientUtil {

    public  static  HttpEntity  get(String url){
        CloseableHttpClient  httpClient = HttpClients.createDefault();
        HttpEntity entity = null ;
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            //2.创建httpget实例(请求)
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "application/json;charset=utf8");


            // 设置长连接
            //httpGet.setHeader("Connection", "keep-alive");
            // 设置代理（模拟浏览器版本）
            //httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            // 设置 Cookie
           // httpGet.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");


            //3.httpclient执行(httpget)请求
            response = httpClient.execute(httpGet);	//执行http get请求
            //4.获取返回的实体(entity)
            entity = response.getEntity();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  entity;
    }


    public  static  String  getJsonString(String url) throws IOException {
        String retJson = "";
        CloseableHttpClient  httpClient = HttpClients.createDefault();
        HttpEntity entity = null ;
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            //2.创建httpget实例(请求)
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "application/json;charset=utf8");


            // 设置长连接
            //httpGet.setHeader("Connection", "keep-alive");
            // 设置代理（模拟浏览器版本）
            //httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            // 设置 Cookie
            // httpGet.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");


            //3.httpclient执行(httpget)请求
            response = httpClient.execute(httpGet);	//执行http get请求
            //4.获取返回的实体(entity)
            entity = response.getEntity();
            for (Header allHeader : response.getAllHeaders()) {
                System.out.println(allHeader.toString());
            }
            System.out.println(response.getLocale());
            retJson = EntityUtils.toString(entity, "utf-8");	//获取网页内容

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  retJson;
    }


    public static String post(String targetUrl, String params) {
        String result = "";
        // 获得Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(targetUrl + "?" + params);
        // 设置ContentType
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static String postByBody(String targetUrl, Map params) {
        String result = "";
        // 获得Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(targetUrl);
        // 设置ContentType
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            //将请求参数添加到方法中
            StringEntity entity = new StringEntity(JSON.toJSONString(params));
            httpPost.setEntity(entity);

            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }



}