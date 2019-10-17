package com.performance.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
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
public class MapPerformance {

  @State(Scope.Thread)
  public static class InternalState {

//    HashMap<Long, Employee> employeeMap = new HashMap<Long, Employee>();
//    LinkedHashMap<Long, Employee> employeeMap = new LinkedHashMap<Long, Employee>();
    TreeMap<Long, Employee> employeeMap = new TreeMap<Long, Employee>();

    Employee employee = new Employee(1000001L, "Harry");

    @Param({"100", "10000", "1000000"})
    long iterations;

    @Setup(Level.Trial)
    public void setUp() {
      for (long i = 0; i < iterations; i++) {
        employeeMap.put(i, new Employee(i, "John"));
      }
      employeeMap.put(iterations, employee);
    }

  }

  @Benchmark
  public Employee testPut(MapPerformance.InternalState state) {
    System.out.println();
    return state.employeeMap.put(state.iterations + 1, new Employee(state.iterations + 1, "Harry"));
  }

  @Benchmark
  public Employee testGet(MapPerformance.InternalState state) {
    System.out.println();
    return state.employeeMap.get(state.employee.getId());
  }

  @Benchmark
  public boolean testContainsKey(MapPerformance.InternalState state) {
    System.out.println();
    return state.employeeMap.containsKey(state.employee.getId());
  }

  @Benchmark
  public Employee testRemove(MapPerformance.InternalState state) {
    System.out.println();
    return state.employeeMap.remove(state.employee.getId());
  }

  @Benchmark
  public int testSize(MapPerformance.InternalState state) {
    System.out.println();
    return state.employeeMap.size();
  }

}
