package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    

    MemoryMemberRepository repository = new MemoryMemberRepository();


    // 끝날떄마다 Store를 쫙 비워준다.
        // 약간 aop같은 느낌이다.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test // org.Junit.Test 가 아니라
    public void save() {

        Member member = new Member();
        member.setName("doge");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 둘이 같은지
        // 첫번째 방법: Assertions.assertEquals(member, result); //
        // 두번째 방법. 요즘 많이 쓴다.
            // import static org.assertj.core.api.Assertions.*; 을 사용하면 앞부분 생략 가능
        assertThat(member).isEqualTo(result);


    }

    @Test
    public void findById() {


    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("sp");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sp1");
        repository.save(member2);

        Member result = repository.findByName("sp").get();

        assertThat(result).isEqualTo(member1);


    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("sp");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sp1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}