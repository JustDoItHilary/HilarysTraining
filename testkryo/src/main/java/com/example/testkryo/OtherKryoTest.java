package com.example.testkryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import org.objenesis.strategy.StdInstantiatorStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilary on 2016/8/8.
 *
 */
public class OtherKryoTest {


    public static void test001() {

        Kryo kryo = new Kryo();
         kryo.setReferences(true);
//        kryo.setRegistrationRequired(true);
//        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
//        注册类
        Registration registration = kryo.register(Student.class);
        long time = System.currentTimeMillis();
        // 序列化
        Output output = null;
        // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // output = new Output( outStream , 4096);
        output = new Output(1, 4096);
        List<Student> students = new ArrayList<Student>();

        students.add(new Student(11, "name_wjh", "北京11"));
        students.add(new Student(12, "name_wjh", "北京"));
        students.add(new Student(13, "name_wjh", "上海"));

        Student myStudent = new Student(10, "xx", "xxx", students);

        kryo.writeObject(output, myStudent);
        byte[] bb = output.toBytes();
        output.flush();

        String str = Base64.encodeToString(bb, Base64.DEFAULT);
        System.out.println(str);

        // 反序列化
        Input input = new Input(Base64.decode(str, Base64.DEFAULT));
        Student s = (Student) kryo.readObject(input, registration.getType());
        System.out.println(s);
        input.close();
        // 反序列化为其他类
        input = new Input(Base64.decode(str, Base64.DEFAULT));
        Teacher teacher = (Teacher) kryo.readObject(input, Teacher.class);
        System.out.println(teacher);
        input.close();
        time = System.currentTimeMillis() - time;
        System.out.println("time:" + time);
    }

}
