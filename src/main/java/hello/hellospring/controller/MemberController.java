// 2023-01-22
package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 컴포넌트 스캔과 자동 의존관계 설정
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired      // 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
                    // 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라고 한다.
                    // ==> 오류 발생. 해결) MemberService.java 에 @Service 추가
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 참고) 생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다.
    //       생성자가 1개만 있으면 @Autowired 는 생략 가능
}
