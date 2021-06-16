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
            Member member = em.find(Member.class, 150L);
            member.setName("A");

            // 영속성 컨텍스트에서 memeber를 제거한다. member 객체는 준영속 상태가 된다.
            em.detach(member);

            // 준영속 상태를 만드는 다른 방법 2가지 
            // em.close(); // 영속성 컨텍스트를 완전히 초기화
            // em.close(); // 영속성 컨텍스트를 종료

            tx.commit(); // 업데이트가 되지 않음.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

}
