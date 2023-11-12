import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalary
{
  public static void main(String[] args)
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    String[] departments = {"Engineering", "Tool Design", "Marketing", "Information Services"};
    @SuppressWarnings("unchecked")
    List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.department.name = ?1 OR e.department.name = ?2" +
        "OR e.department.name = ?3 oR e.department.name = ?4")
        .setParameter(1, departments[0])
        .setParameter(2, departments[1])
        .setParameter(3, departments[2])
        .setParameter(4, departments[3])
        .getResultList();

    for (Employee e : employees) {
      e.setSalary(e.getSalary().add(e.getSalary().multiply(BigDecimal.valueOf(0.12))));
      System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
    }
    em.getTransaction().commit();
    em.close();
  }
}
