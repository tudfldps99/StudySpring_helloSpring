// 2023-01-21
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 컨텐츠
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";     // main/resources/templates/hello.html <-- 'hello' 이름 동일
                            // 컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver 가 화면을 찾아서 처리
                            // resources/templates/ {viewName} + .html
    }

    // MVC 와 템플릿 엔진 (파라미터를 전달받음)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API (ResponseBody 문자 반환)
    // 실행 : http://localhost:8080/hello-string?name=spring      --> name 뒤의 파라미터 값이 name 값
    @GetMapping("hello-string")
    @ResponseBody           // (http 통신의 body 부에 return 내용을 직접 넣겠다. 즉, html 파일이 필요하지 않다)
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;     // "hello spring"
    }

    // API (ResponseBody 객체 반환) : 객체를 반환하면 객체가 JSON 으로 변환됨
    // 실행 : http://localhost:8080/hello-api?name=spring      --> name 뒤의 파라미터 값이 name 값
    @GetMapping("hello-api")
    @ResponseBody       // HTTP BODY 에 문자 내용을 직접 반환, viewResolver 대신에 HttpMessageConverter 가 동작
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;       // {"name":"spring"}
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
