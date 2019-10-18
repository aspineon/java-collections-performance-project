package com.performance.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Test {

  HashSet<Employee> employeeSet = new HashSet<Employee>();
  
  Employee employee = new Employee(100L, "Harry");
  long iterations = 100000;
  
  public static void main(String[] args) {
    Test t = new Test();
    t.crazyLoop();
    t.searchEmployee();
  }

  private void crazyLoop() {
    for(long i = 0; i < iterations; i++) {
      employeeSet.add(new Employee(i, "John"));
    }
    employeeSet.add(employee);
    System.out.println("total size:"+employeeSet.size());
  }
  
  
  private void searchEmployee() {
    employeeSet.parallelStream().filter(L -> L.getId().equals(employee.getId()))
    .forEach(F -> System.out.println("found in java 8 streams:" + F.getName()));
  }
  
  
}
