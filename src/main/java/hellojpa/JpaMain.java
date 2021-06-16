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
            // 객체를 생성한 상태(비영속)
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 객체를 저장한 상태(영속)
            System.out.println("====BEFORE====");
            em.persist(member);
            System.out.println("====AFTER====");

            // 영속성 컨텍스트의 1차 캐시에서 조회하기 때문에 select 쿼리가 따로 안나간다!
            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            tx.commit(); // 이 시점에서 실제로 쿼리가 날아감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

    }

}
