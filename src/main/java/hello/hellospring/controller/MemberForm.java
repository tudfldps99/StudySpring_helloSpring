// 2023-01-22
package hello.hellospring.controller;

// 회원 등록 컨트롤러
// 웹 등록 화면에서 데이터를 전달 받을 폼 객체
public class MemberForm {

    private String name;        // createMemberForm.html 의 "name" 과 매칭

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
