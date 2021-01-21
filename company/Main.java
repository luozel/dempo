package com.company;

import org.junit.Test;

import java.util.*;

public class Main {

    @Test
    public void test1() {

        
        TreeSet<Integer> set = new TreeSet<>((x, y) -> Integer.compare(x, y));
    }

        List<Person> student = Arrays.asList(
                new Person("乌鸡", 18, 88, 36000),
                new Person("joil", 28, 188, 3600),
                new Person("南山", 30, 288, 32000),
                new Person("舒服", 44, 88, 3600)
        );

        public List<Person> getStudent(List < Person > list) {
            List<Person> personList = new ArrayList<>();
            for (Person student : list) {
                if (student.getSalary() > 25000) {
                    personList.add(student);
                }
            }
            return personList;
        }

        public List<Person> getStudent2 (List < Person > list) {
            List<Person> personList1 = new ArrayList<>();
            for (Person student : list) {
                if (student.getAge() > 20) {
                    personList1.add(student);
                }
            }
            return personList1;
        }
        public List<Person> filterStudent3(List<Person> list,MyPredicate<Person> myPredicate){
            List<Person> result = new ArrayList<>();
            for(Person student : list){
                if(myPredicate.test(student)){
                    result.add(student);
                }
            }
            return result;
        }
    
    @Test
    public void test3() {
        List<Person> list = filterStudent3(student, (x)->x.getSalary()>25000);
        List<Person> list1 = filterStudent3(student,(x)->x.getAge()>18);
        for(Person student : list){
            System.out.println(student);
        }
    }
    @Test
    public void Test4(){
            student.stream().filter((x)->x.getSalary()>25000)
                    .filter((x)->x.getAge()>18)
                    .limit(2)
                    .forEach(System.out::println);
        System.out.println("____________________");
    }
    
}
    

