import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment
{
  public static void main(String[] args)
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    @SuppressWarnings("unchecked")
    List<Employee> employeesList = em.createQuery("SELECT e FROM Employee e " +
        "WHERE e.department.name = 'Research and Development' ORDER BY e.salary ASC, e.id ASC")
        .getResultList();

    for (Employee e : employeesList) {
      System.out.printf("%s %s from %s - $%.2f%n", e.getFirstName(), e.getLastName(),
          e.getDepartment().getName(),e.getSalary());
    }
    em.getTransaction().commit();
    em.close();
  }
}
