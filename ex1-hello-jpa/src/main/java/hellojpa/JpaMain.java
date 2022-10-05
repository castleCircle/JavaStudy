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

      Team team = new Team();
      team.setName("teamA");
      em.persist(team);

      Member member = new Member();
      member.setUsername("member1");
      em.persist(member);

      em.flush();
      em.clear();

      System.out.println("=====1");
      final Member member1 = em.find(Member.class, 2L);

      tx.commit();
    }catch(Exception e){
      tx.rollback();
    }finally {
      em.close();
    }
    emf.close();

  }

}
