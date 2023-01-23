// 2023-01-23
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// JAP 회원 리포지토리
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // 아래 두줄만 작성하면 JPA가 Insert query 만들어서 DB에 집어넣음.
        em.persist(member);      // persist : 영구저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // PK 기반이 아닌 나머지는 JPQL 작성 필요
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)       // 객체(Member 엔티티)를 대상으로 쿼리를 날림 -> SQL로 번역됨
                .getResultList();

    }
}
