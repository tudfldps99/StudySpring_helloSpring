// 2023-01-22
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원 리포지토리 인터페이스
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);     // Optional - id 값이 없으면 null 을 반환하는 대신, Optional 으로 감싸서 반환하는 방법을 선호
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
