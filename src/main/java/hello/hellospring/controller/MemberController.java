// 2023-01-22
package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 컴포넌트 스캔과 자동 의존관계 설정
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired      // 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
                    // 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라고 한다.
                    // ==> 오류 발생. 해결) MemberService.java 에 @Service 추가
    public MemberController(MemberService memberService) {      /* DI 의 3가지 방법 ) 1. 생성자 주입 */
        this.memberService = memberService;
    }

    /* DI 의 3가지 방법 ) 2. 필드 주입 */
    // @Autowired private MemberService memberService;

    /* DI 의 3가지 방법 ) 3. setter 주입 = setter 가 public 하게 노출이 됨 -> 중간에 잘못 바꾸면 문제가 됨 */
    // 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장
    // private MemberService memberService;
    // @Autowired
    // public void setMemberService(MemberService memberService) {
        // this.memberService = memberService;
    // }

    // 참고) 생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다.
    //       생성자가 1개만 있으면 @Autowired 는 생략 가능

    @GetMapping("/members/new")     // home.html
    public String createForm() {
        return "members/createMemberForm";      // templates/members/createMemberForm.html
    }

    @PostMapping("/members/new")        // createMEmberForm.html 의 method="post"
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";        // 회원가입 후 홈 화면으로 이동
    }
}
