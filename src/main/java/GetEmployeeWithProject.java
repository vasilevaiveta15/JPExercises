import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class GetEmployeeWithProject
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    int id = Integer.parseInt(scanner.nextLine());
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Employee employee = (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1 ORDER BY e.firstName ASC")
        .setParameter(1, id).setMaxResults(1).getSingleResult();

    System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getJobTitle());

    List<Project> projectList = new ArrayList<>(employee.getProjects());
    projectList.sort(Comparator.comparing(Project::getName));
    for (Project p : projectList) {
      System.out.println("\t\t" + p.getName());

    }

    em.getTransaction().commit();
    em.close();
  }
}
