package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repo = new MemoryMemberRepository();

    //메소드 실행끝나면 실행.
    @AfterEach
    public void afterEach(){
        //테스트 하나 끝날때마다 저장소를 삭제.
        repo.clearStore();
    }
    //테스트는 순서상관없이 성공해야함.
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repo.save(member);

        Member result = repo.findById(member.getId()).get();
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repo.save(member2);

        Member result = repo.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repo.save(member2);

        List<Member> result = repo.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
