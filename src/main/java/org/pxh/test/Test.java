package org.pxh.test;

import org.junit.runner.RunWith;
import org.pxh.test.controller.po.Student;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @ClassName Test
 * @Description
 * @Author pxh
 * @Date 2021/12/20 16:03
 * @Version
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    //将一个对象放入list,是将对象的引用（实际对象的地址）复制一份给list
    @org.junit.Test
    public  void  testList(){
        List list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Student student = new Student();
        list.add(student);

        List list1 = new ArrayList();
        list1.add("d");
        list1.addAll(list);

        list.remove(0);
        for (Object o : list1) {
            System.out.println(o.hashCode());
        }
        for (Object o : list) {
            System.out.println(o.hashCode());
        }

        student = new Student();
        student.setName("1");

        for (Object o : list1) {
            System.out.println(o.toString());
        }
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }


    @org.junit.Test
    public  void  testMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("4",new Student());

        Map<String,Object> map1 = new HashMap<>();
        map1.put("3","c");
        map1.putAll(map);
        map.remove("1");
        Student stu = (Student) map.get("4");
        stu.setName("1");
        for(String key : map.keySet()){
            System.out.println(map.get(key).toString());
        }
        for(String key : map1.keySet()){
            System.out.println(map1.get(key).toString());
        }

    }

    @org.junit.Test
    public  void  testURL(){
        String url =  "http://localhost:8686/cas/p3/serviceValidate?" +
                "ticket=" + "aaa" +
                "&service=http://127.0.0.1:8080/seeyon/login/sso?from=scjdjSSOLoginContent&UserAgentFrom=pc"+
                "&format=json";
        try {
            url = URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(url);

    }


}