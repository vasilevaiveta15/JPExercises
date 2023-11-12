import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingANewAddressAndUpdatingEmployee
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    String lastname = scanner.nextLine().trim();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Address newAddress = new Address();
    newAddress.setText("Vitoshka 15");
    em.persist(newAddress);
    @SuppressWarnings("unchecked")
    Employee found = (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.lastName = ?1")
        .setParameter(1, lastname)
        .setMaxResults(1).getSingleResult();

    found.setAddress(newAddress);
    em.getTransaction().commit();
    em.close();
  }
}
