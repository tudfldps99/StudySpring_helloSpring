// 2023-01-22
package hello.hellospring.domain;

// 회원 객체
public class Member {

    private Long id;        // 시스템이 지정
    private String name;    // 사용자가 입력

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
