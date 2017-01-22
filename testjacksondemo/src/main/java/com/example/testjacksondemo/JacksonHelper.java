package com.example.testjacksondemo;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Hilary on 2016/11/15.
 */

public class JacksonHelper {

    private ArrayList<ExtWeeklyInfoEntity> list = new ArrayList<>();
    private Person person;

    public JacksonHelper() {
        list.add(new ExtWeeklyInfoEntity("小鸟说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        list.add(new ExtWeeklyInfoEntity("小鸡说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        list.add(new ExtWeeklyInfoEntity("小鹅说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        list.add(new ExtWeeklyInfoEntity("小鸭说：早 早 早 ", Calendar.getInstance().getTime().toString()));

        person = new Person("zhaokaiqiang", 22, new Birthday(1992, 1, 19));
    }

    public String createObjectJson() throws Exception {
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = JacksonMapper.getInstance().getJsonFactory().createJsonGenerator(stringWriter);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeString("string_太阳当空照，花儿对我笑");
        jsonGenerator.writeStringField("content", "string_太阳当空照，花儿对我笑");
        jsonGenerator.writeObject(new ExtWeeklyInfoEntity("小鸟说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        jsonGenerator.writeObjectField("value", new ExtWeeklyInfoEntity("小鸟说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        jsonGenerator.writeEndObject();
        jsonGenerator.flush();
        jsonGenerator.close();
        return jsonGenerator.toString();
    }

    public String createObjectJsonOther() throws IOException {
        return JacksonMapper.getInstance().writeValueAsString(list);
    }

    public String createArrayJson() throws Exception {
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = JacksonMapper.getInstance().getJsonFactory().createJsonGenerator(stringWriter);

        jsonGenerator.writeStartArray();
        jsonGenerator.writeString("string_太阳当空照，花儿对我笑");
        jsonGenerator.writeStringField("content", "string_太阳当空照，花儿对我笑");
        jsonGenerator.writeObject(new ExtWeeklyInfoEntity("小鸟说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        jsonGenerator.writeObjectField("value", new ExtWeeklyInfoEntity("小鸟说：早 早 早 ", Calendar.getInstance().getTime().toString()));
        jsonGenerator.writeEndArray();
        jsonGenerator.flush();
        jsonGenerator.close();
        return jsonGenerator.toString();
    }

    public String jsonToArray() throws IOException {
        List<ExtWeeklyInfoEntity> list = JacksonMapper.getInstance().readValue(createObjectJsonOther(), new ArrayList<ExtWeeklyInfoEntity>().getClass());
        return list.toString();
    }
}
