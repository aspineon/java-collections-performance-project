package com.performance.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5)
@Fork(value = 1, warmups = 1)
public class ListPerformance {

  @State(Scope.Thread)
  public static class InternalState {

//    ArrayList<Employee> employeeList = new ArrayList<Employee>();
    LinkedList<Employee> employeeList = new LinkedList<Employee>();

    @Param({ "100", "10000", "1000000" })
    long iterations;

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
   * @Benchmark public void testAdd(ListPerformance.InternalState state) {
   * System.out.println(); state.employeeList.add(new Employee(state.iterations +
   * 1, "Harry with addition")); }
   * 
   * @Benchmark public void testAddAt(ListPerformance.InternalState state) {
   * System.out.println(); state.employeeList.add((int) state.iterations, new
   * Employee(state.iterations, "John add at")); }
   * 
   * @Benchmark public int testSize(ListPerformance.InternalState state) {
   * System.out.println(); return state.employeeList.size(); }
   * 
   * @Benchmark public Employee testGet(ListPerformance.InternalState state) {
   * System.out.println(); return state.employeeList.get(state.employeeIndex); }
   * 
   * @Benchmark public int testIndexOf(ListPerformance.InternalState state) {
   * System.out.println(); return state.employeeList.indexOf(state.employee); }
   * 
   * @Benchmark public boolean testContains(ListPerformance.InternalState state) {
   * System.out.println(); return state.employeeList.contains(state.employee); }
   * 
   * @Benchmark public boolean testRemove(ListPerformance.InternalState state) {
   * System.out.println(); return state.employeeList.remove(state.employee); }
   */
  @Benchmark
  public void testSearchWithForEach(ListPerformance.InternalState state) {
    for (Employee e : state.employeeList) {
      if (e.getId().equals(state.employee.getId())) {
        System.out.println("found in for each:" + e.getName());
      }
    }
  }

  @Benchmark
  public void testSearchWithJava8ForEach(ListPerformance.InternalState state) {
    state.employeeList.forEach(L -> {
      if (L.getId().equals(state.employee.getId())) {
        System.out.println("found in java 8 for each:" + L.getName());
      }
    });
  }

  @Benchmark
  public void testSearchWithJava8Streams(ListPerformance.InternalState state) {
    state.employeeList.stream().filter(L -> L.getId().equals(state.employee.getId()))
        .forEach(F -> System.out.println("found in java 8 streams:" + F.getName()));
  }

  @Benchmark
  public void testSearchWithJava8ParallelStreams(ListPerformance.InternalState state) {
    state.employeeList.parallelStream().filter(L -> L.getId().equals(state.employee.getId()))
        .forEach(F -> System.out.println("found in java 8 parallel streams" + F.getName()));
  }

}
