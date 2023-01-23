// 2023-01-22
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// 회원 서비스 테스트 (순수한 단위 테스트 - 단위 테스트를 잘 만드는게 더 좋은 테스트)
class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // -> 변경
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트 동작 전마다 memberRepository 를 넣어줌 (MemberService.java 의 DI 와 관련)
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 테스트가 끝날때마다 repository (저장소)나 공용데이터를 지워줘야 함
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

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

        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertEquals("이미 존재하는 회원입니다.", e.getMessage());
        }
         */
        // 위의 try-catch 문을 간략하게 표현
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 예외가 발생해야 한다.

        // then
        Assertions.assertEquals("이미 존재하는 회원입니다.", e.getMessage());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}