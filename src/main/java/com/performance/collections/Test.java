package com.performance.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Test {

  HashMap<Long, Employee> employeeMap = new HashMap<Long, Employee>();
  ArrayList<Employee> employeeList = new ArrayList<Employee>();
  
  
  Employee employee = new Employee(100L, "Harry");
  long iterations = 100000;
  
  public static void main(String[] args) {
    Test t = new Test();
//    t.addElementsToMap();
    t.addElementsToList();
  }

  private void addElementsToMap() {
    employeeMap.put(1L, new Employee(1L, "Harry"));
    employeeMap.put(2L, new Employee(2L, "Harry"));
    employeeMap.put(3L, new Employee(3L, "Harry"));
    employeeMap.put(4L, new Employee(4L, "Harry"));
    System.out.println(employeeMap.size());
    System.out.println(employeeMap.get(2L).getId());
  }

  private void addElementsToList() {
    employeeList.add(new Employee(1L, "Harry"));
    employeeList.add(new Employee(2L, "Harry"));
    employeeList.add(new Employee(3L, "Harry"));
    employeeList.add(new Employee(4L, "Harry"));
    System.out.println(employeeList.size());
    System.out.println(employeeList.get(employeeList.indexOf(new Employee(2L, "Harry"))).getId() );
  }

  
}
