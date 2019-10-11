package io.wooo.listview_demo3;

import java.util.Random;

public class Person {

    private Long id;

    private String name;

    private int age;

    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Person getPerson() {
        Person person = new Person();
        String name = ChineseNameUtil.getName();
        person.setName(name);
        if (Math.random() < 0.5) {
            person.setSex("男");
        } else {
            person.setSex("女");
        }
        person.setAge(new Random().nextInt(10) + 18);
        return person;
    }

}
