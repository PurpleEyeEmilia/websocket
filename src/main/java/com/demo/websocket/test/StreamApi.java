package com.demo.websocket.test;

import com.demo.websocket.test.testentity.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengnian
 * @version V1.0
 * @date 2019/6/12 9:36
 * @Desc
 */
public class StreamApi {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(102, "李四", 59, 6666.66));
        employees.add(new Employee(101, "张三", 18, 9999.99));
        employees.add(new Employee(103, "王五", 28, 3333.33));
        employees.add(new Employee(104, "赵六", 8, 7777.77));
        employees.add(new Employee(104, "赵六", 8, 7777.77));
        employees.add(new Employee(104, "赵六", 8, 7777.77));
        employees.add(new Employee(105, "田七", 38, 5555.55));

        employees.stream()
                .filter(employee -> employee.getAge() >= 18)
                .forEach(System.out::println);

        System.out.println("----------------");

        employees.stream()
                .filter(employee -> employee.getAge() > 8)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("----------------");

        employees.stream()
                .filter(employee -> employee.getAge() > 8)
                .skip(2)
                .forEach(System.out::println);

        System.out.println("----------------");

        employees.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println("----------------");

        employees.stream()
                .filter(employee -> employee.getAge() > 8)
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("----------------");

        employees.stream()
                .sorted((o1, o2) -> o1.getAge().intValue() - o2.getAge().intValue());
    }
}
