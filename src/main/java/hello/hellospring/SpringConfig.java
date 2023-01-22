// 2023-01-22
package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
// @Service, @Repository, @Autowired 애노테이션 제거

@Configuration
public class SpringConfig {

    /*
     * MemberService 와 MemberRepository 를 모두 Spring Bean 으로 등록하고,
     * Spring Bean 에 등록되어있는 memberRepository 를 MemberService 에 넣어줌
     */
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {        // MemberRepository : interface
        return new MemoryMemberRepository();            // MemoryMemberRepository : 구현체 (interface 에는 new 안됨)
    }
}
