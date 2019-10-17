package com.performance.collections;

import java.util.ArrayList;
import java.util.TreeSet;

public class Test {

  TreeSet<Employee> employeeSet = new TreeSet<Employee>();
  Employee employee = new Employee(2L, "Harry");
  int iterations = 2;
  
  public static void main(String[] args) {
    new Test().crazyLoop();

  }

  private void crazyLoop() {
    for(long i = 0; i < iterations; i++) {
      System.out.println("size:"+employeeSet.size());
//      System.out.println("Result:"+employee.compareTo(new Employee(i, "John")));
      employeeSet.add(new Employee(i, "John"));
    }
    
    employeeSet.add(employee);
    System.out.println("total size:"+employeeSet.size());
  }
  
  
}
