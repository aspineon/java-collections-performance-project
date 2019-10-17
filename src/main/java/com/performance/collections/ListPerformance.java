package com.performance.collections;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5)
public class ListPerformance {

  @State(Scope.Thread)
  public static class InternalState {

//    ArrayList<Employee> employeeList = new ArrayList<Employee>();
    LinkedList<Employee> employeeList = new LinkedList<Employee>();

    
    long iterations = 1000000;

    Employee employee = new Employee(100L, "Harry");
    int employeeIndex = -1;
    
    @Setup(Level.Trial)
    public void setUp() {
      for (long i = 0; i < iterations; i++) {
        employeeList.add(new Employee(i, "John"));
      }
      employeeList.add(employee);
      employeeIndex = employeeList.indexOf(employee);
    }
  }

  
  /*
   * @Benchmark
   * 
   * @Fork(value = 1, warmups = 1) public void
   * testAdd(ListPerformance.InternalState state) { state.employeeList.add(new
   * Employee(state.iterations + 1, "Harry")); System.out.println(); }
   * 
   * @Benchmark
   * 
   * @Fork(value = 1, warmups = 1) public void
   * testAddAt(ListPerformance.InternalState state) { System.out.println();
   * state.employeeList.add((int) state.iterations, new Employee(state.iterations,
   * "John")); }
   * 
   * @Benchmark
   * 
   * @Fork(value = 1, warmups = 1) public int
   * testSize(ListPerformance.InternalState state) { System.out.println(); return
   * state.employeeList.size(); }
   * 
   * @Benchmark
   * 
   * @Fork(value = 1, warmups = 1) public Employee
   * testGet(ListPerformance.InternalState state) { System.out.println(); return
   * state.employeeList.get(state.employeeIndex); }
   * 
   * @Benchmark
   * 
   * @Fork(value = 1, warmups = 1) public int
   * testIndexOf(ListPerformance.InternalState state) { System.out.println();
   * return state.employeeList.indexOf(state.employee); }
   * 
   * @Benchmark
   * 
   * @Fork(value = 1, warmups = 1) public boolean
   * testContains(ListPerformance.InternalState state) { System.out.println();
   * return state.employeeList.contains(state.employee); }
   * 
   * @Benchmark
   * 
   * @Fork(value = 1, warmups = 1) public boolean
   * testRemove(ListPerformance.InternalState state) { System.out.println();
   * return state.employeeList.remove(state.employee); }
   */
  @Benchmark
  @Fork(value = 1, warmups = 1)
  public void testSearchWithIndexAndGet(ListPerformance.InternalState state) {
    int index = state.employeeList.indexOf(state.employee);
    System.out.println("found in index and get:"+ state.employeeList.get(index).getId());
  }
  
  @Benchmark
  @Fork(value = 1, warmups = 1)
  public void testSearchWithForEach(ListPerformance.InternalState state) {
    for(Employee e : state.employeeList) {
      if(e.equals(state.employee)) {
        System.out.println("found in for each:"+ e.getId());
      }
    }
  }
  
}
