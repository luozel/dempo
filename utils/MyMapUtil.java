package com.dev.testsos.sosgroup.utils;

import java.util.HashMap;

/**
 * @Author: 陈嘉欣
 * @Date: 2018/12/19 18:00
 */
public class MyMapUtil {

    public static class MyMap extends HashMap<String, Object> {
        @Override
        public Object put(String key, Object value) {
            String underline = "_";
            String humpKey = key;
            if (key.contains(underline)) {
                humpKey = key.toLowerCase();
                while (humpKey.contains(underline)) {
                    int pos = humpKey.indexOf(underline);
                    String lineWithWord = humpKey.substring(pos, pos + 2);
                    String upperCaseWord = lineWithWord.replace(underline, "").toUpperCase();
                    humpKey = humpKey.replaceFirst(lineWithWord, upperCaseWord);
                }
            }
            Object obj = value;
            try {
                if (value instanceof String) {
                    String val = (String) value;
                    String listPrefix = "[";
                    String listSuffix = "]";
                    String objPrefix = "{";
                    String objSuffix = "}";
                    if (val.startsWith(listPrefix) && val.endsWith(listSuffix)) {
                        if (val.startsWith(listPrefix + objPrefix) && val.endsWith(objSuffix + listSuffix)) {
                            obj = MyJsonUtil.stringToList(val, MyMap.class);
                        } else {
                            obj = MyJsonUtil.stringToList(val, String.class);
                        }
                    } else if (val.startsWith(objPrefix) && val.endsWith(objSuffix)) {
                        obj = MyJsonUtil.stringToObject(val, MyMap.class);
                    }
                    if(null == obj){
                        obj = val;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                obj = value;
            }
            return super.put(humpKey, obj);
        }
    }

    public static void main(String[] args) {
//        String key = "hello_hao_aa";
//        MyMap myMap = new MyMap();
//        myMap.put(key, "[{\"id\":[{\"id\":1},{\"id\":2}]},{\"id\":2}]");
//        System.out.println(MyJsonUtil.stringToList("[\"高血压\",\"冠心病\"]",String.class));
//        System.out.println(myMap.toString());
    }
}
