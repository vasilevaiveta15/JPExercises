import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressWithEmployeeCount
{
  public static void main(String[] args)
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    @SuppressWarnings("unchecked")
    List<Address> addresses = em.createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC, a.town.id ASC")
        .setMaxResults(10).getResultList();

      for(Address a : addresses){
        System.out.printf("%s, %s - %d employees", a.getText(), a.getTown().getName(), a.getEmployees().size());
      }
    em.getTransaction().commit();
    em.close();
  }
}
