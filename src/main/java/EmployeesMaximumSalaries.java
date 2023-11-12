import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeesMaximumSalaries
{
  public static void main(String[] args)
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    @SuppressWarnings("unchecked")
    List<Employee> employees =
        em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(salary) FROM Employee em GROUP BY em.department HAVING e.department = em.department)GROUP BY e.department")
            .getResultList();

    List<Employee> employeeList = new ArrayList<>();
    for (Employee e : employees) {
      if (e.getSalary().intValue() < 30000 || e.getSalary().intValue() > 70000) {
        employeeList.add(e);
      }
    }

    for (Employee e : employeeList) {
      System.out.println(e.getDepartment().getName() + " - " + e.getSalary());
    }
    em.getTransaction().commit();
    em.close();
  }
}
