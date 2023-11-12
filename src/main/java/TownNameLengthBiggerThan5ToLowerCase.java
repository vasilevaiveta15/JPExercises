import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TownNameLengthBiggerThan5ToLowerCase
{
  public static void main(String[] args)
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    @SuppressWarnings("unchecked")
    List<Town> towns = em.createQuery("SELECT t FROM Town t WHERE LENGTH(t.name) > 5").getResultList();

    for(Town t : towns){
      em.detach(t);
      t.setName(t.getName().toLowerCase());
      em.merge(t);
    }
    em.getTransaction().commit();
    em.close();

  }
}
