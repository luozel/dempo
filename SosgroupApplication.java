package com.dev.testsos.sosgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

//@MapperScan("com.dev.testsos.sosgroup.mapper")
//@SpringBootApplication
public class SosgroupApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SosgroupApplication.class, args);
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        ListIterator<String> listIt = list.listIterator();

        while (listIt.hasNext()) {
            System.out.print(listIt.next() + " ");//a b c
        }

        while (listIt.hasPrevious()) {
            System.out.print(listIt.previous() + " ");//c b a
        }
        Set<BigDecimal> hashSet1 = new HashSet<>();
        TreeSet<Object> objectTreeSet = new TreeSet<>();
        objectTreeSet.add(new BigDecimal("1.0").stripTrailingZeros());
        System.out.println(objectTreeSet.contains(new BigDecimal("1.0").stripTrailingZeros()));
        BigInteger i = new BigInteger(String.valueOf(Long.MAX_VALUE));
        System.out.println(i.add(BigInteger.ONE).toString());
        String[] arr = {"1", "2", "3"};
        List list2 = new ArrayList(Arrays.asList(arr));
        arr[1] = "4";
        try {
            list2.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("arr:{} list:{}"+Arrays.toString(arr)+""+list2);
        int[] arr1 = {1,2,3};
        List ints = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        System.out.println(ints+"--"+ints.size()+"--"+ints.get(0).getClass());
        Integer[] arr2 = {1, 2, 3};
        List list1 = Arrays.asList(arr2);
        System.out.println(list1+"--"+list1.size()+"--"+list1.get(0).getClass());
        DateTimeFormatter dateTimeFormatter =DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(dateTimeFormatter);
        System.out.println(format);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            Date parse = simpleDateFormat1.parse("2018-07-09");
            calendar.setTime(parse);
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(calendar.MINUTE,0);
            calendar.set(calendar.SECOND,0);
            Date newDate = calendar.getTime();
            System.out.println(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
}
