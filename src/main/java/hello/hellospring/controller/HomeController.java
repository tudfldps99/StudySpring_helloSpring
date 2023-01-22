// 2023-01-22
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 회원 웹 기능 - 홈 화면 추가 (홈 컨트롤러 추가)
@Controller
public class HomeController {
    @GetMapping("/")        // localhost:8080
    public String home() {
        return "home";
    }
}
