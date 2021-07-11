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
            member.setUsername("hello");
            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getUsername() = " + findMember.getUsername());

            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getClass()); // 프록시 클래스임을 확인할 수 있다.
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getUsername() = " + findMember.getUsername());
            System.out.println("findMember.getUsername() = " + findMember.getUsername());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

}
