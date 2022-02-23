package org.pxh.http;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RestTemplateUtil
 * @Description
 * @Author pxh
 * @Date 2021/12/10 9:28
 * @Version
 */
@Component
public class RestTemplateUtil {

    @Autowired
    private  static RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }


    public   static   <T> ResponseEntity<T>  post(String url ,String   jsonParams ,Class<T>  tClass,  HttpHeaders  headers){
        HttpEntity<String> request = new HttpEntity<>(jsonParams, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, tClass);
        return  responseEntity;
    }

    public   static   <T> ResponseEntity<T>  post(String url ,String   jsonParams ,Class<T>  tClass){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        return  post(url,jsonParams,tClass,headers);
    }

    public   static   <T>   T  postReturnData(String url ,String   jsonParams ,Class<T>  tClass,  HttpHeaders  headers){
        HttpEntity<String> request = new HttpEntity<>(jsonParams, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, tClass);
        T t = responseEntity.getBody();
        return  t ;
    }

    public   static   <T>   T  postReturnData(String url ,String   jsonParams ,Class<T>  tClass){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        return  postReturnData(url,jsonParams,tClass,headers) ;
    }


    public   static   String  postReturnJsonString(String url ,String   jsonParams ,  HttpHeaders  headers){
        HttpEntity<String> request = new HttpEntity<>(jsonParams, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        String JsonStr = responseEntity.getBody();
        return  JsonStr ;
    }

    public   static   String  postReturnJsonString(String url ,String   jsonParams){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        return  postReturnJsonString(url,jsonParams,headers) ;
    }


    public   static   <T> ResponseEntity<T>  post(String url , String   jsonParams , Class<T>  tClass, String  contentType, Map<String,String> headParams){
        HttpHeaders headers = new HttpHeaders();
        if(!Strings.isBlank(contentType)){
            MediaType mediaType = MediaType.parseMediaType(contentType);
            headers.setContentType(mediaType);
        }
        if(headParams.isEmpty() == false){
            for(String key : headParams.keySet()){
                headers.add(key,headParams.get(key) );
            }
        }
        return  post(url,jsonParams,tClass,headers);
    }

    /**
     *
     * @param url
     * @param mapParams  LinkedMultiValueMap
     * @param tClass
     * @param headParams
     * @param <T>
     * @return
     */
    public   static   <T> ResponseEntity<T>  post(String url , MultiValueMap<String, String> mapParams , Class<T>  tClass, Map<String,String> headParams){
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        if(headParams.isEmpty() == false){
            for(String key : headParams.keySet()){
                headers.add(key,headParams.get(key) );
            }
        }
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(mapParams, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, tClass);
        return  responseEntity ;
    }



    public   static   <T>  T  postReturnData(String url , String   jsonParams , Class<T>  tClass, String  contentType, Map<String,String> headParams){
        HttpHeaders headers = new HttpHeaders();
        if(!Strings.isBlank(contentType)){
            MediaType mediaType = MediaType.parseMediaType(contentType);
            headers.setContentType(mediaType);
        }
        if(headParams.isEmpty() == false){
            for(String key : headParams.keySet()){
                headers.add(key,headParams.get(key)+"" );
            }
        }
        return  postReturnData(url,jsonParams,tClass,headers);
    }


    /**
     *  通过post请求获取数据
     * @param url
     * @param jsonParams  url中的参数
     * @param contentType 请求的数据类型
     * @param headParams 加在请求头中的参数
     * @return
     */
    public   static   String  postReturnJsonString(String url , String   jsonParams ,  String  contentType, Map<String,String> headParams){
        HttpHeaders headers = new HttpHeaders();
        if(!Strings.isBlank(contentType)){
            MediaType mediaType = MediaType.parseMediaType(contentType);
            headers.setContentType(mediaType);
        }
        if(headParams.isEmpty() == false){
            for(String key : headParams.keySet()){
                headers.add(key,headParams.get(key)+"" );
            }
        }
        return  postReturnJsonString(url,jsonParams,headers);
    }


    public   static   <T> ResponseEntity<T>  get(String url ,  Class<T>  tClass, Map<String,String> headParams){
       // ResponseEntity<T> responseEntity = restTemplate.getForEntity(url,tClass);
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        if(headParams.isEmpty() == false){
            for(String key : headParams.keySet()){
                headers.add(key,headParams.get(key) );
            }
        }
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, tClass);
        return  responseEntity ;
    }





}