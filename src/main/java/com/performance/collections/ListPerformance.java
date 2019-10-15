package com.performance.collections;

import java.io.IOException;
import java.util.ArrayList;
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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5)
public class ListPerformance {

  @State(Scope.Thread)
  public static class InternalState {

    ArrayList<Employee> employeeList = new ArrayList<Employee>();

    long iterations = 1000000;

    Employee employee = new Employee(100L, "Harry");
    
    @Setup(Level.Trial)
    public void setUp() {
      for (long i = 0; i < iterations; i++) {
        employeeList.add(new Employee(i, "John"));
      }
      employeeList.add(employee);
//      System.out.println("List size after setUp:"+ employeeList.size());
    }
  }

  @Benchmark
  @Fork(value = 1, warmups = 1)
  public void testAdd(ListPerformance.InternalState state) {
    state.employeeList.add(new Employee(state.iterations + 1, "Harry"));
//    System.out.println("List size after testAdd:"+ state.employeeList.size());
    System.out.println();
  }

  
  public static void main(String[] args) throws RunnerException, IOException {
    org.openjdk.jmh.Main.main(args);
  }
  
}
