package org.pxh.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName RandomUtil
 * @Description 获取不重复的随机数
 * @Author pxh
 * @Date 2022/1/7 11:09
 * @Version
 */
public class RandomUtil {

    private static String str="abcdefghijklmnopqrstuvwxyz0123456789a";

    private static List<String> list  = new ArrayList<>();
    private static int  maxSize = 400 ;//数组长度
    private static int  length = 4 ;//随机数长度

    public RandomUtil(int maxSize, int  length){
        this.maxSize = maxSize;
        this.length = length;
    }

    private  static String random(){
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number=r.nextInt(str.length()-1);
            rs.append(str.charAt(number));
        }
        return rs.toString();
    }

    /**
     * 产生一个4位在最近400个数中不重复的数
     * @return
     */
    public static String getNotRepeatRandom(){
        String ranDom = "";
        boolean  cf = true;
        while (cf == true){
            cf = false;
            ranDom = random();
            for (String s : list) {
                if(ranDom.equals(s)){
                    cf = true;
                    break;
                }
            }
        }

        // 记录最近的数，保证最近生成的 maxsize 这么多的数据 不与刚生成的数据重复
        if(list.size() >= maxSize){
            list.remove(0);
        }
        list.add(ranDom);

        return ranDom;
    }


    /**
     * 产生一个随机数
     * @param length
     * @return
     */
    public  static String getrandom(int length){
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number=r.nextInt(str.length()-1);
            rs.append(str.charAt(number));
        }
        return rs.toString();
    }

    public static void main(String[] args) {


        for(int i = 0 ; i< 10000 ; i ++){
            System.out.println(RandomUtil.getNotRepeatRandom());
            System.out.println(list.size());
            System.out.println(list.get(0));
            System.out.println(list.get(list.size() - 1));
        }
        System.out.println(list.size());
        for (String s : list) {
            System.out.println("list " + s);
        }
    }



}