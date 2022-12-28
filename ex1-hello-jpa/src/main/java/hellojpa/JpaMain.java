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

      Atest a = new Atest();
      a.setName("a");

      Atest aa = new Atest();
      aa.setName("aaa");

      Btest b = new Btest();

      b.getAtestList().add(a);
      b.getAtestList().add(aa);


      em.persist(a);
      em.persist(aa);

      em.persist(b);

      em.flush();
      em.clear();

      System.out.println("==================");
      final Btest btest = em.find(Btest.class, 3L);
      System.out.println(btest.getAtestList().size());

      tx.commit();
    }catch(Exception e){
      tx.rollback();
    }finally {
      em.close();
    }
    emf.close();

  }

}
