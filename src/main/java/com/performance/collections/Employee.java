package com.performance.collections;

public class Employee implements Comparable<Employee>{
  private Long id;
  private String name;
  
  public Employee(Long id, String name) {
    super();
    this.id = id;
    this.name = name;
  }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + name.hashCode();
    return result;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    Employee employee = (Employee) o;
    
    if (!id.equals(employee.id)) return false;
    return name.equals(employee.name);
  }

  public int compareTo(Employee o) {
    if(id.equals(o.id)) 
      return 0;
    else 
      return 1;
  }
  
}
