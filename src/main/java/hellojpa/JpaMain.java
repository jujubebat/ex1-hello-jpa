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
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            // 영속성 컨텍스트에 member1, member2 객체가 저장된다. 그리고 2개의 insert 문에 대한 쿼리문도 저장된다.
            em.persist(member1);
            em.persist(member2);
            System.out.println("=============");

            // 이떄 영속성 컨텍스트에 있던 2개의 insert 쿼리문을 한 방에 날릴 수 있다. db 접근을 1번만 할 수 있다는 이점이 있다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }

}
