import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    String[] input = scanner.nextLine().split("\\s+");
    String firstname = input[0];
    String lastName = input[1];
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    @SuppressWarnings("unchecked")
    List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE ?1 = e.firstName and ?2 = e.lastName")
        .setParameter(1, firstname)
        .setParameter(2, lastName)
        .getResultList();

    if (employees.size() == 1) {
      System.out.println("Yes");
    }
    else {
      System.out.println("No");
    }

    em.getTransaction().commit();
    em.close();
  }
}
