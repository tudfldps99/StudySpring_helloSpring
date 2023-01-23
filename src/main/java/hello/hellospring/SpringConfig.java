// 2023-01-22
package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 2023-01-23
    // 순수 Jdbc 구현 -> 스프링 설정 변경
    /*
    private DataSource dataSource;      // DataSource 는 데이터베이스 커넥션을 획득할 때 사용하는 객체이다.
                                        // 스프링 부트는 데이터베이스 커넥션 정보를 바탕으로 DataSource 를 생성하고, 스프링 빈으로 만들어둔다. 그래서 DI 를 받을 수 있다.
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    // JPA 사용 -> 스프링 설정 변경
    /*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    // 스프링 JPA 사용 -> 스프링 설정 변경
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        //return new MemberService(memberRepository());

        // 2023-01-23
        // 4. memberRepository() 주석 + 스프링 JPA 사용 -> 스프링 설정 변경
        // 스프링 데이터 JPA가 SpringDataJpaMemberRepository를 스프링 빈으로 자동 등록
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public MemberRepository memberRepository() {        // MemberRepository : interface
        //return new MemoryMemberRepository();            // MemoryMemberRepository : 구현체 (interface 에는 new 안됨)

        // 2023-01-23
        // 1. 기존 return 문 주석 + 순수 Jdbc 구현 -> 스프링 설정 변경
        //return new JdbcMemberRepository(dataSource);        // 개방-폐쇄 원칙 (OCP, Open-Closed Principle) : 확장에는 열려있고, 수정&변경에는 닫혀있다.
                                                            // 스프링의 DI(Dependencies Injection) 을 사용하면, 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경 가능
        // 2. 기존 return 문 주석 + JDBCTemplate 사용 -> 스프링 설정 변경
        //return new JdbcTemplateMemberRepository(dataSource);

        // 3. 기존 return 문 & dataSource 변수 선언 주석 + JPA 사용 -> 스프링 설정 변경
        return new JpaMemberRepository(em);
    }
     */

    // AOP 등록
    // SpringConfig.java 에 Bean 등록 or TimeTraceAop 에 @Component 어노테이션 사용
    /*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
     */
}
