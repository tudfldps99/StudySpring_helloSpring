// 2023-01-22
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 회원 서비스 개발
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 회원 서비스가 메모리 회원 리포지토리를 직접 생성 -> 문제) MemberRepository , MemoryMemberRepository = 다른 리포지터리
    // --> 회원 서비스 코드를 DI (Dependency Injection) 가능하게 변경

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {       // 외부에서 넣어주도록 변경 (DI)    --> MemberServiceTest.java
        this.memberRepository = memberRepository;
    }

    /*
     *   회원가입
     */
    public Long join(Member member) {

        // 같은 이름이 있는 중복 회원 X
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {     // 값이 있으면 아래 throw.     --> Optional 로 감싸져있기때문에 ifPresent 작성 가능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // 위의 코드 정리 1
        /*
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {     // 값이 있으면 아래 throw.     --> Optional 로 감싸져있기때문에 ifPresent 작성 가능
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        */

        // 위의 코드 정리2 - 메소드로 분리
        validateDuplicateMember(member);        // 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {     // 값이 있으면 아래 throw.     --> Optional 로 감싸져있기때문에 ifPresent 작성 가능
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     *   전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
