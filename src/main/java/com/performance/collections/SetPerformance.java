package com.performance.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;
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
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
public class SetPerformance {

  @State(Scope.Thread)
  public static class InternalState {

//    HashSet<Employee> employeeSet = new HashSet<Employee>();
//    LinkedHashSet<Employee> employeeSet = new LinkedHashSet<Employee>();
    TreeSet<Employee> employeeSet = new TreeSet<Employee>();

    Employee employee = new Employee(100L, "Harry");

    @Param({ "100", "10000", "1000000" })
    long iterations;

    @Setup(Level.Trial)
    public void setUp() {
      for (long i = 0; i < iterations; i++) {
        employeeSet.add(new Employee(i, "John"));
      }
      employeeSet.add(employee);
      System.out.println("size:" + employeeSet.size());
    }
  }

  @Benchmark
  public boolean testAdd(SetPerformance.InternalState state) {
    System.out.println();
    return state.employeeSet.add(new Employee(state.iterations + 1, "Harry during add"));
  }

  @Benchmark
  public boolean testContains(SetPerformance.InternalState state) {
    System.out.println();
    return state.employeeSet.contains(state.employee);
  }

  @Benchmark
  public int testSize(SetPerformance.InternalState state) {
    System.out.println();
    return state.employeeSet.size();
  }

  @Benchmark
  public boolean testRemove(SetPerformance.InternalState state) {
    System.out.println();
    return state.employeeSet.remove(state.employee);
  }

  @Benchmark
  public void testSearchWithForEach(SetPerformance.InternalState state) {
    for (Employee e : state.employeeSet) {
      if (e.getId().equals(state.employee.getId())) {
        System.out.println("found at for each:" + e.getName());
      }
    }
  }

  @Benchmark
  public void testSearchWithJava8Streams(SetPerformance.InternalState state) {
    state.employeeSet.stream().filter(L -> L.getId().equals(state.employee.getId()))
        .forEach(F -> System.out.println("found in java 8 streams:" + F.getName()));
  }

  @Benchmark
  public void testSearchWithJava8ParallelStreams(SetPerformance.InternalState state) {
    state.employeeSet.parallelStream().filter(L -> L.getId().equals(state.employee.getId()))
        .forEach(F -> System.out.println("found in java 8 parallel streams:" + F.getName()));
  }

}
