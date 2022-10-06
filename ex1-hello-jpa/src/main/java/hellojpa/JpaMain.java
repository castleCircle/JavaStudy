package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    final EntityTransaction tx = em.getTransaction();

    tx.begin();

    try{

      Movie movie = new Movie();
      movie.setDirector("aaa");
      movie.setActor("bbb");
      movie.setName("바람과함께");
      movie.setPrice(1000);

      em.persist(movie);

      tx.commit();
    }catch(Exception e){
      tx.rollback();
    }finally {
      em.close();
    }
    emf.close();

  }

}
