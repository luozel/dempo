package com.company;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class StudyLambda2 {
    public void happy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }
    @Test
    public void test1(){
        happy(1000,(m)-> System.out.println("消费金额"+m));
    }
    
    public List<Integer> getNumber(int num, Supplier<Integer> sp){
        List<Integer> result = new ArrayList<>();
        for (int i=0;i<num;i++){
            result.add(sp.get());
        }
        return result;
    }
    @Test
    public void test2(){
        List<Integer> numList = getNumber(5,()->(int)(Math.random()*100));
        numList.forEach(System.out::println);
    }
    public String strHander(String str, Function<String,String> fun){
        return fun.apply(str);
    }
    @Test
    public void test3(){
        String result = strHander("武林",(x)->x+"张无忌");
        System.out.println(result);
    }
}
