package org.pxh.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.pxh.test.controller.po.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName TestController
 * @Description
 * @Author pxh
 * @Date 2021/12/9 16:54
 * @Version
 */
@Controller
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/1")
    @ResponseBody
    public Map test1(){
        Map map = new HashMap();
        map.put("name","zs");
        map.put("age","12");
        System.out.println(map.toString());
        System.out.println(JSON.toJSONString(map));
        return map;
    }

    @RequestMapping("/2")
    @ResponseBody
    public Student test2(){
        Student student = new Student();
        student.setAge(10);
        student.setId(1l);
        student.setName("zs");
        System.out.println(student.toString());
        System.out.println(JSON.toJSONString(student));
        return student;
    }


    @PostMapping("/students")
    @ResponseBody
    public List<Student> students(HttpServletRequest request , @RequestBody(required = false) JSONObject jsonObject) throws IOException {
        List list = new ArrayList();
        Student student = new Student();
        student.setAge(10);
        student.setId(1l);
        student.setName("zs");
        list.add(student);
        list.add(student);
        list.add(student);
        if(jsonObject != null){
            System.out.println(jsonObject.toJSONString());
        }
        System.out.println(request.getParameter("a"));


        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {//传递json数据的时候request是没有表单参数的
            String parameterName = parameterNames.nextElement();
            System.out.println(parameterName + "===" + request.getParameter(parameterName));
        }
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            System.out.println(header+"=="+request.getHeader(header));
        }
        return list;
    }


    @RequestMapping("/student")
    @ResponseBody
    public Student student(HttpServletRequest request){
        List list = new ArrayList();
        Student student = new Student();
        student.setAge(10);
        student.setId(1l);
        student.setName("zs");
        list.add(student);
        list.add(student);
        list.add(student);


        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {//传递json数据的时候request是没有表单参数的
            String parameterName = parameterNames.nextElement();
            System.out.println(parameterName + "===" + request.getParameter(parameterName));
        }
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            System.out.println(header+"=="+request.getHeader(header));
        }
        System.out.println(student.toString());
        return student;
    }

}