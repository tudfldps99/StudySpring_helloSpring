// 2023-01-23
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA 회원 리포지토리 (스프링 데이터 JPA : JPA를 편리하게 사용하도록 도와주는 기술)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 스프링 데이터 JPA 제공 기능
    // - 인터페이스를 통한 기본적인 CRUD
    // - findByName(), findByEmail() 처럼 메서드 이름만으로 조회 기능 제공
    // - 페이징 기능 자동 제공

    // JPQL) select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
