package com.company;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class StudyLambda1 {
    //无参构造方法
    @Test
    public void Test1(){
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("1");
//            }
//        };
        int i = 0;
        Runnable runnable =()->{
            System.out.println(i);
        };
    }
  //有参构造方法
    @Test
    public void Test2(){
        Consumer<String> com = (x)->{
            System.out.println(x);};
            com.accept("13233");
        }
    //只有一个参数 可以省略括号
    @Test
    public void Test3(){
        Consumer<String> com = x->{
            System.out.println(x);};
        com.accept("知情书");
    }
    //有两个以上的参数 有返回值 并且lambda体中有多条语句
    @Test
    public void Test4(){
        Comparator<Integer> com = (x, y)->{
            System.out.println("222");
        return Integer.compare(x,y);
        };
    }
    //只有一条语句 return 和大括号都可以不写
    @Test
    public void Test5(){
        Comparator<Integer> com = (x, y)->Integer.compare(x,y);
    }
    //参数指定类型
    @Test
    public void Test6(){
        Comparator<Integer> com = (Integer x, Integer y)->Integer.compare(x,y);
        
    }
}
