import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    String firstName = scanner.nextLine();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    @SuppressWarnings("unchecked")                                                        //CONCAT(?1,'%') the other way
    List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE ?1||'%'")
        .setParameter(1, firstName).getResultList();
    for (Employee e : employees) {
      System.out.printf("%s %s - %s - ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary());
    }
    em.getTransaction().commit();
    em.close();
  }
}
