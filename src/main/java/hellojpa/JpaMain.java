package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 영속
            Member member = new Member(1L, "A");
            em.persist(member);
            tx.commit(); // 업데이트가 되지 않음.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

}
