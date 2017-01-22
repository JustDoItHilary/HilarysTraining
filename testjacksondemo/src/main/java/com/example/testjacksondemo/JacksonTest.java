/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：JacksonTest.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-11-27
 */

package com.example.testjacksondemo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import android.test.AndroidTestCase;
import android.util.Log;

public class JacksonTest extends AndroidTestCase {

    private static final String TAG = "JacksonTest";
    //ObjectMapper 用于 write and read json,either to and from（无论是从） pojo,jsonNode as well as(还是）执行转换的相关功能

    /**
     * 创建Array形式的json
     */
    public String createArrayJson() throws Exception {

        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

        StringWriter stringWriter = new StringWriter();
        // 只能通过这种方式获取 用来 write Json ，只能通过 JsonFactory 来创建 instanse
        JsonGenerator jsonGenerator = JacksonMapper.getInstance()
                .getJsonFactory().createJsonGenerator(stringWriter);

        //开始 write 的标识
        jsonGenerator.writeStartArray();
        jsonGenerator.writeString("zhaokaiqiang");
        jsonGenerator.writeNumber(22);
        jsonGenerator.writeObject(p);
        //结束 write 的标识
        jsonGenerator.writeEndArray();

        //清空
        jsonGenerator.flush();
        //关闭
        jsonGenerator.close();

        Log.d(TAG, stringWriter.toString());
        return stringWriter.toString();
    }

    /**
     * 生成Object形式的json
     *
     * @throws Exception
     */
    public String createObjectJson() throws Exception {

        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

        StringWriter stringWriter = new StringWriter();

        // 必须通过这种方式获取
        JsonGenerator jsonGenerator = JacksonMapper.getInstance()
                .getJsonFactory().createJsonGenerator(stringWriter);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", "zhaokaiqiang");
        jsonGenerator.writeNumberField("age", 22);
        jsonGenerator.writeObjectField("person", p);
        jsonGenerator.writeEndObject();

        jsonGenerator.flush();
        jsonGenerator.close();

        return stringWriter.toString();

    }

    /**
     * json转换为集合对象
     */
    public String jsonToObjects() throws Exception {

        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(p);
        persons.add(p);
        persons.add(p);

        @SuppressWarnings("unchecked")
        ArrayList<Person> personArrayList = JacksonMapper.getInstance()
                .readValue(getJsonString(persons),
                        new ArrayList<Person>().getClass());
        Log.d(TAG, "persons------" + personArrayList);
        return personArrayList.toString();
    }

    //json转换为单一对象
    public String jsonToObject() throws Exception {
        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
        Person person = JacksonMapper.getInstance().readValue(getJsonString(p),
                new Person().getClass());
        Log.d(TAG, "person------" + person);
        return person.toString();
    }

    //集合对象
    public String objectsToJson() throws Exception {
        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(p);
        persons.add(p);
        persons.add(p);

        Log.d(TAG, getJsonString(persons));
        return  getJsonString(persons);
    }

    //单一对象
    public String objectToJson() throws Exception {
        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
        Log.d(TAG, getJsonString(p));
        return getJsonString(p);
    }

    //获取Json数据方法一
    public String getJsonString1(Object object) throws Exception {
        ObjectMapper mapper = JacksonMapper.getInstance();
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, object);
        gen.flush();
        gen.close();
        return sw.toString();
    }

    //获取Json数据方法二
    public String getJsonString(Object object) throws Exception {
        return JacksonMapper.getInstance().writeValueAsString(object);
    }

    public String toObject() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
        String jsonString = getJsonString(p);
        Person person = objectMapper.readValue(jsonString, Person.class);
        Log.d(TAG, person.toString());
        return person.toString();
    }

    public String toObjects() throws Exception {

        Person p = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));

        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(p);
        persons.add(p);
        persons.add(p);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = getJsonString(persons);

        String result = null;


//		String jsonString ="[{\"age\":22,\"NAME\":\"zhaokaiqiang\"},{\"age\":22,\"NAME\":\"zhaokaiqiang\"},{\"age\":22,\"NAME\":\"zhaokaiqiang\"}]";
        List<Person> list = objectMapper.readValue(jsonString, new TypeReference<List<Person>>() {
        });
        for (int i = 0; i < list.size(); i++) {
            Person person = list.get(i);
            result = person.getname();
        }

        @SuppressWarnings("unchecked")
        ArrayList<Person> arrayList = objectMapper.readValue(jsonString,
                new ArrayList<Person>().getClass());
//        ArrayList<LinkedHashMap<String, String>> arrayList = objectMapper.readValue(jsonString,
//                new ArrayList<LinkedHashMap<String, String>>().getClass());

        return result;

    }

    public String fromJson() throws Exception {

        String jsonString = createObjectJson();
        ObjectMapper objectMapper = JacksonMapper.getInstance();
        Map<String, Map<String, Object>> objects = objectMapper.readValue(
                jsonString,
                new TypeReference<Map<String, Map<String, Object>>>() {
                });
        Set<String> keys = objects.keySet();

        Log.d(TAG, keys.toString());

        String name = "";
        String age = "";
        String personString = "";
        for (Iterator<String> key = keys.iterator(); key.hasNext(); ) {

            if (key.next().equals("name")) {
                name = objects.get(key).toString();
            } else if (key.next().equals("age")) {
                age = objects.get(key).toString();
            } else if (key.next().equals("person")) {
                personString = objects.get(key).toString();
            }

            Log.d(TAG, "key=" + key);

        }

        Log.d(TAG, "name----" + name);
        Log.d(TAG, "age----" + age);
        Log.d(TAG, "person----" + personString);
        return String.format("name=%s;age=%s;person=%s",name,age,personString);
    }

    public String fromJsons() throws Exception {

        String jsonString = createObjectJson();
        ObjectMapper objectMapper = JacksonMapper.getInstance();

        JsonNode jsonNode = objectMapper.readTree(jsonString);
        JsonNode nameNode = jsonNode.get("name");
        JsonNode ageNode = jsonNode.get("age");
        JsonNode persoNode = jsonNode.get("person");

        String personString = persoNode.toString();
        Person person = objectMapper.readValue(personString, Person.class);

        Log.d(TAG, "person=" + person.toString());
        Log.d(TAG, "age=" + ageNode.asInt());
        Log.d(TAG, "name=" + nameNode.asText());

        return String.format("person=%s;ageNode=%s;nameNode=%s",person,ageNode,nameNode);
    }

    public String toList() throws Exception {
        String jsonString = "[{\"FORMVALUE\":\"测试\",\"CREATEDATE\":\"2016-09-12 12:23:22\"},{\"FORMVALUE\":\"测试\",\"CREATEDATE\":\"2016-09-12 12:23:22\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExtWeeklyInfoEntity> list = null;

        try {           //这个可以实现
            ExtWeeklyInfoEntity[] weeklyInfoEntities = objectMapper.readValue(jsonString, ExtWeeklyInfoEntity[].class);
            list = Arrays.asList(weeklyInfoEntities);
        } catch (IOException e) {
            e.printStackTrace();
        }

//                        try {      //这个不可以实现，LinkedHashMap不可以转化为实体类
//                            list=objectMapper.readValue(result.toString(),new ArrayList<ExtWeeklyInfoEntity>().getClass());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

//                        try {       //这个可以实现
//                            list=objectMapper.readValue(result.toString(), new TypeReference<List<ExtWeeklyInfoEntity>>(){});
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

//                        try {     //这个可以实现
//                            list=objectMapper.readValue(objectMapper.treeAsTokens(result.toString()), new TypeReference<List<ExtWeeklyInfoEntity>>(){});
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

        //		for (int i=0;i<list.size();i++){
//			ExtWeeklyInfoEntity person=list.get(i);
//			result = person.getFormvalue();
//		}
        return list.toString();
    }
    public String getList(){
        ArrayList<ExtWeeklyInfoEntity> list = new ArrayList<>();
        list.add(new ExtWeeklyInfoEntity("小鸟说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        list.add(new ExtWeeklyInfoEntity("小鸡说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        list.add(new ExtWeeklyInfoEntity("小鹅说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        list.add(new ExtWeeklyInfoEntity("小鸭说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        return list.toString();
    }

}
