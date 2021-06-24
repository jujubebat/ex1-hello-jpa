package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임 ex) mysql 의 auto_increment
    // @GeneratedValue(strategy = GenerationType.AUTO) // db 방언에 따라 자동 지정
    // @GeneratedValue(strategy = GenerationType.SEQUENCE) // 데이터베이스 시퀀스 오브젝트 사용, 숫자만 가능
    // @GeneratedValue(strategy = GenerationType.TABLE) // 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략 (모든 데이터베이스에 적용 가능, but 테이블을 추가로 사용하므로 성능이슈가 있음)
    private Long id;

    @Column
    private String username;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}