package com.demo.websocket.test.testentity;

import lombok.Data;

/**
 * @author pengnian
 * @version V1.0
 * @date 2019/6/12 10:20
 * @Desc
 */
@Data
public class Employee {
    /**
     * id
     */
    private int id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    private Double salary;

    public Employee() {
    }

    public Employee(int id, String name, Integer age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
