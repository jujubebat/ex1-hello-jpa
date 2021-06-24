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
            Member member = new Member();
            member.setUsername("C");

            System.out.println("================");

            em.persist(member); // commit 시점에 쿼리가 나가는 것이 아니라, 여기서 쿼리가 나감. 왜지?
            // 설명
            // 현재 member의 Id는 GenerationType.IDENTITY 옵션을 사용하고 있다. 즉, DB에서 만들어주는 pk를 사용하고 있다.
            // 영속성 컨텍스트는 pk를 키로 entity를 밸류로 map 형태로 관리하기 떄문에 pk가 필요하다.
            // pk가 없으면 영속성 컨텍스트에서 엔티티를 관리할 수 없다. 그래서 DB에서 만들어주는 pk를 사용해야한다. 그렇기에 쿼리를 commit 전에 날려서 pk를 얻어오는 것이다.
            System.out.println("member.getId() = " + member.getId());
            System.out.println("================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

}
