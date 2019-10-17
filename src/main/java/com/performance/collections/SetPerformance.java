package com.performance.collections;

import java.util.Iterator;
import java.util.TreeSet;
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
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 1)
public class SetPerformance {

  @State(Scope.Thread)
  public static class InternalState {

//    HashSet<Employee> employeeSet = new HashSet<Employee>();
//    LinkedHashSet<Employee> employeeSet = new LinkedHashSet<Employee>();
    TreeSet<Employee> employeeSet = new TreeSet<Employee>();

    Employee employee = new Employee(100L, "Harry");
    long iterations = 1000000;

    @Setup(Level.Trial)
    public void setUp() {
      for (long i = 0; i < iterations; i++) {
        employeeSet.add(new Employee(i, "John"));
      }
      employeeSet.add(employee);
      System.out.println("size:" + employeeSet.size());
    }
  }

  /*
   * @Benchmark public boolean testAdd(SetPerformance.InternalState state) {
   * System.out.println(); return state.employeeSet.add(new
   * Employee(state.iterations + 1, "Harry")); }
   * 
   * @Benchmark public boolean testContains(SetPerformance.InternalState state) {
   * System.out.println(); return state.employeeSet.contains(state.employee); }
   * 
   * @Benchmark public int testSize(SetPerformance.InternalState state) {
   * System.out.println(); return state.employeeSet.size(); }
   * 
   * @Benchmark public Iterator<Employee>
   * testIterator(SetPerformance.InternalState state) { System.out.println();
   * return state.employeeSet.iterator(); }
   * 
   * @Benchmark public boolean testRemove(SetPerformance.InternalState state) {
   * System.out.println(); return state.employeeSet.remove(state.employee); }
   */
  @Benchmark
  public void testSearchWithForEach(SetPerformance.InternalState state) {
    for(Employee e : state.employeeSet) {
      if(e.equals(state.employee)) {
        System.out.println("found at for each:"+ e.getId());
      }
    }
  }
  
  
}
