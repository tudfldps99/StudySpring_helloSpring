// 2023-01-22
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 회원 서비스 개발
//@Service        // MemberController.java 에서 발생한 오류 해결
// -> 자바 코드로 직접 스프링 빈을 등록하기 위해 @Service 주석처리
@Transactional      // 2023-01-23) JPA 사용하여 데이터를 저장&수정하려면 Service 계층에 @Transactional 필요
                    // 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋한다. 만약 런타임 예외가 발생하면 롤백한다.
                    // JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 회원 서비스가 메모리 회원 리포지토리를 직접 생성 -> 문제) MemberRepository , MemoryMemberRepository = 다른 리포지터리
    // --> 회원 서비스 코드를 DI (Dependency Injection) 가능하게 변경

    private final MemberRepository memberRepository;

    //@Autowired      // 생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다
    // -> 자바 코드로 직접 스프링 빈을 등록하기 위해 @Autowired 주석처리
    public MemberService(MemberRepository memberRepository) {       // 외부에서 넣어주도록 변경 (DI - 의존성주입)    --> MemberServiceTest.java
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

        // 2023-01-23) AOP가 필요한 상황: 모든 메소드의 호출 시간을 측정하고 싶다면?
        // -> 시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵고, 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.
        /*
             // 위의 코드 정리2 - 메소드로 분리
            validateDuplicateMember(member);        // 중복회원 검증

            memberRepository.save(member);
            return member.getId();
        */
        long start = System.currentTimeMillis();
        try {
            // 위의 코드 정리2 - 메소드로 분리
            validateDuplicateMember(member);        // 중복회원 검증

            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
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
        // return memberRepository.findAll();

        // 2023-01-23) AOP가 필요한 상황: 모든 메소드의 호출 시간을 측정하고 싶다면?
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
