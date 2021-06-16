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
            Member member = new Member(201L, "member200");
            em.persist(member); // member와 쿼리문이 영속성 컨텍스트에 저장된다.

            // 영속성 컨텍스트에 저장된 쿼리문이 DB에 반영된다.
            // 플러시를 한다고 1차캐시가 지워지는 것은 아님!
            // 오직 엔티티 변화를 감지하고 영속성 컨텍스트에 있는 쓰지 전용 SQL 저장소에 있는 쿼리를 날린다.
            em.flush();

            System.out.println("===========");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }

}
