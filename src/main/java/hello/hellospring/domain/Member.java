// 2023-01-22
package hello.hellospring.domain;

import javax.persistence.*;

// 회원 객체
@Entity     // 2023-01-23) JPA 엔티티 매핑
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)     // 2023-01-23) JPA
    private Long id;        // 시스템이 지정

    //@Column(name = "username")      // 2023-01-23) JPA
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
