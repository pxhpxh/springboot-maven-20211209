package org.pxh.testController;

import org.pxh.http.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassName testController
 * @Description
 * @Author pxh
 * @Date 2022/2/16 11:20
 * @Version
 */
@Controller
public class OaTestController {


    @RequestMapping("/testUrl")
    public void testUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String serviceUrl=request.getParameter("service");
        UUID uuid = UUID.randomUUID();
        String ticket = uuid.toString();
        serviceUrl = serviceUrl + "&ticket=" + ticket;
        System.out.println(serviceUrl);
        //String  ret = HttpClientUtil.getJsonString(serviceUrl);
        //System.out.println(ret);

        response.sendRedirect(serviceUrl);
    }




    @ResponseBody
    @RequestMapping("/getData")
    public  String  getData(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String data= "{\"serviceResponse\" : {\n" +
                "\"authenticationSuccess\" : {\n" +
                "\"user\" : \"04c46b4d1089481bba75e8507f2ccce7\",\n" +
                "\"attributes\" : {\n" +
                "\"credentialType\" : [ \"UsernamePasswordCaptchaCredential\" ],\n" +
                "\"code\" : [ 200 ],\n" +
                "\"isFromNewLogin\" : [ true ],\n" +
                "\"data\" : [ {\n" +
                "\"idCode\" : \"pxh\"\n" +
                "}, {\n" +
                "\"phone\" : \"13333331233\"\n" +
                "}, {\n" +
                "\"realName\" : \"测试 CAS 账号\"\n" +
                "}, {\n" +
                "\"deptId\" : \"97a77f76a8ec4dd8a9ab570a0205e4f2\"\n" +
                "}, {\n" +
                "\"userName\" : \"1000\"\n" +
                "}, {\n" +
                "\"deptName\" : \"测试部门\"\n" +
                "} ],\n" +
                "\"authenticationDate\" : [ 1606352383.909000000 ],\n" +
                "\"authenticationMethod\" : [ \"DynamciAuthentication\" ],\n" +
                "\"success\" : [ true ],\n" +
                "\"successfulAuthenticationHandlers\" : \n" +
                "[ \"DynamciAuthentication\" ],\n" +
                "\"longTermAuthenticationRequestTokenUsed\" : [ false ]\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}\n";
        return  data;
        // return new ModelAndView(new MappingJackson2JsonView(), map);
    }

}