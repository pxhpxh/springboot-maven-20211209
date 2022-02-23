package org.pxh.mongodb;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.UnknownHostException;


/**
 * @ClassName TestMongodb
 * @Description
 * @Author pxh
 * @Date 2022/2/9 16:12
 * @Version
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMongodb {

    @org.junit.Test
    public void test() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("test01");
        MongoCollection<Document> student = mongoDatabase.getCollection("student");
        FindIterable<Document> documents = student.find();

        Document document = new Document("name", "张三1")
                .append("sex", "男")
                .append("age", 88);
        student.insertOne(document);


        Bson filter = Filters.eq("age", 88);
        //删除与筛选器匹配的单个文档
        student.deleteOne(filter);
        student.deleteMany(filter);

        MongoCursor<Document> iterator = documents.iterator();
       /* while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/

        //指定查询过滤器
        filter = Filters.eq("stu_name", "李四");
        FindIterable<Document> documents1 = student.find(filter).sort(new BasicDBObject("age",1));

        iterator = documents1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

    @org.junit.Test
    public void updateOneTest() {
        //获取数据库连接对象
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test01");
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");


        //修改过滤器
        Bson filter = Filters.eq("name", "张三1");
        //指定修改的更新文档
        Document document = new Document("$set", new Document("age", 100));
        //修改单个文档
        collection.updateOne(filter, document);
    }
}