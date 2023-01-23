// 2023-01-23
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

// 회원 서비스 스프링 통합 테스트 (스프링 컨테이너와 DB까지 연결한 통합 테스트)
@SpringBootTest     // 스프링 컨테이너와 테스트를 함께 실행
@Transactional      // 테스트 케이스에 이 어노테이션이 있으면, 각 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백.
                    // 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않음
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(findMember.getName(), member.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 예외가 발생해야 한다.

        // then
        Assertions.assertEquals("이미 존재하는 회원입니다.", e.getMessage());
    }
}