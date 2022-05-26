package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setId(1L);
        member.setName("name one");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member, result);
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("one");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("two");
        repository.save(member2);

        Member result = repository.findByName("one").get();
        org.assertj.core.api.Assertions.assertThat(member1).isEqualTo(result);

    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size() == 2);
    }
}