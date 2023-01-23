// 2023-01-23
package hello.hellospring.aop;

// AOP: Aspect Oriented Programming
//      = 공통 관심사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 시간 측정 AOP 등록     + SpringConfig.java 에 Bean 등록 or TimeTraceAop 에 @Component 어노테이션 사용
@Component
@Aspect
public class TimeTraceAop {

    /*
        AOP 사용
        - 회원가입, 회원 조회 등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리
        - 시간을 측정하는 로직을 별도의 공통 로직으로 만듦.
        - 핵심 관심 사항을 깔끔하게 유지 가능
        - 변경이 필요하면 이 로직만 변경하면 됨
        - 원하는 적용 대상 선택 가능 (@Around 이용)
     */

    @Around("execution(* hello.hellospring..*(..))")    // 현재 패키지(hello.hellospring) 하위에 모두 적용해라
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();     // 다음 메소드로 진행 : 프록시(가짜) memberService 이용 후 실제 memberService 이용
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
