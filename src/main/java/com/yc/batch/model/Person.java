package com.yc.batch.model;

import javax.validation.constraints.Size;

/**
 * 描述:
 *
 * @author YC
 * @create 2018-10-06 16:33
 */
public class Person {
    @Size(max=4,min=2)
    private String name;

    private  int age ;

    private String nation ;

    private String address;

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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
