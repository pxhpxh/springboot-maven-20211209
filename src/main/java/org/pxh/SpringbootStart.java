package org.pxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @ClassName SpringbootStart
 * @Description
 * @Author pxh
 * @Date 2021/12/9 16:43
 * @Version
 */

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class SpringbootStart {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStart.class,args);
    }

}