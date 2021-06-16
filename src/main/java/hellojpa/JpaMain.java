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
            // 쿼리가 최초 한 번만 날아간다. DB에서 member를 가져온다.
            Member findMember1 = em.find(Member.class, 101L);
            // 1차 캐시에서 member를 가져온다.
            Member findMember2 = em.find(Member.class, 101L);

            // 영속 엔티티 동일성 보장
            System.out.println("result = " + (findMember1 == findMember2));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }

}
