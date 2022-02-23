package org.pxh.http;

import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Test
 * @Description
 * @Author pxh
 * @Date 2021/12/10 10:55
 * @Version
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @org.junit.Test
    public  void  test(){
        Map map = new HashMap();
        map.put("a","1");
        map.put("b",2);

        Map headerMap = new HashMap();
        headerMap.put("c",3+"");
        headerMap.put("d",4+"");

        //String ret =  RestTemplateUtil.getpostReturnJsonString("http://127.0.0.1:8081/student?dd=5", JSON.toJSONString(map),"application/json; charset=UTF-8",headerMap);
        //System.out.println(ret);
        //ResponseEntity<ArrayList> getpost = RestTemplateUtil.getpost("http://127.0.0.1:8081/students?dd=5", JSON.toJSONString(map), ArrayList.class);
        //Student student = RestTemplateUtil.getpostReturnData("http://127.0.0.1:8081/student?dd=5", JSON.toJSONString(map), Student.class);
        //System.out.println(student);

        String s = RestTemplateUtil.postReturnData("http://127.0.0.1:8081/students?dd=5", JSON.toJSONString(map), String.class, "application/json; charset=UTF-8", headerMap);
        System.out.println(s);
      //  ResponseEntity getpost = RestTemplateUtil.post("http://127.0.0.1:8081/students?dd=5", JSON.toJSONString(map), String.class, "application/json; charset=UTF-8", headerMap);
       // System.out.println(getpost);

    }


    @org.junit.Test
    public  void  test1(){

        MultiValueMap<String,String> map = new LinkedMultiValueMap();
        map.add("a","1");
        map.add("b","2");

        Map headerMap = new HashMap();
        headerMap.put("c",3+"");
        headerMap.put("d",4+"");


        ResponseEntity getpost = RestTemplateUtil.post("http://127.0.0.1:8081/student?dd=5", map, String.class,  headerMap);
        System.out.println(getpost);

       // ResponseEntity getpost1 = RestTemplateUtil.post("http://127.0.0.1:8081/student?dd=5", map, List.class,  headerMap);
        //System.out.println(getpost1);


    }


    @org.junit.Test
    public  void  test3(){
        Map map = new HashMap();
        map.put("a","1");
        map.put("b",2);

        Map headerMap = new HashMap();
        headerMap.put("c",3+"");
        headerMap.put("d",4+"");
        ResponseEntity getpost = RestTemplateUtil.get("http://127.0.0.1:8081/student?dd=5",  String.class,  headerMap);
        System.out.println(getpost);
    }


    @org.junit.Test
    public  void  test4(){

        try {
            String  json = HttpClientUtil.getJsonString("http://127.0.0.1:8081/student?dd=5&a=ab");
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public  void  test5(){
        Map map = new HashMap();
        map.put("a","1");
        map.put("b",2);
        //String  json = httpClientUtil.post("http://127.0.0.1:8081/students" ,"dd=5&a=ab");
        String  json = HttpClientUtil.postByBody("http://127.0.0.1:8081/students" ,map);
        System.out.println(json);
    }


}