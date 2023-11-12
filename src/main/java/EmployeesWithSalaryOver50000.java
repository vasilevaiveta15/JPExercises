import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000
{
  public static void main(String[] args)
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    @SuppressWarnings("unchecked")
    List<Employee> employeesList = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 50000").getResultList();
    for(Employee e : employeesList){
      System.out.println(e.getFirstName());
    }
    em.getTransaction().commit();
    em.close();
  }
}
