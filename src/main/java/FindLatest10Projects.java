import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class FindLatest10Projects
{
  public static void main(String[] args)
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    @SuppressWarnings("unchecked")
    List<Project> projects = em.createQuery("SELECT p FROM Project p ORDER BY  p.startDate DESC")
        .setMaxResults(10).getResultList();

    projects.sort(Comparator.comparing(Project::getName));
    for(Project p : projects){
      System.out.println("Project name: " + p.getName());
      System.out.println("\t\tProject Description: " + p.getDescription());
      System.out.println("\t\tProject Start Date: " + p.getStartDate());
      System.out.println("\t\tProject End Date: " + p.getEndDate());
    }

    em.getTransaction().commit();
    em.close();
  }
}
