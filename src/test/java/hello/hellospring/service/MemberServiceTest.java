package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;



public class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);

    }

    @AfterEach
    public void afterEach(){

        repository.clearStore();
    }


    @Test
    public void 회원가입() {
        // given
        Member m = new Member();
        m.setName("hi");

        // when
        Long saveId = memberService.join(m); // join 하고나서 해당 멤버의 id 리턴된다.

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(m.getName()).isEqualTo(findMember.getName());
    }

    // 여기서의 test passed 는 예외 성공했다는 소리다.
    @Test
    public void 중복회원예외(){
        //given
        Member m1 = new Member();
        m1.setName("yes");

        Member m2 = new Member();
        m2.setName("yes");

        // when
        memberService.join(m1);
//        try{
//            memberService.join(m2);
//            fail(); // 의도한대로 되지않았으니
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");       // catch가 됬으면 성공한 것!
//        }

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }


    @Test
    public void 전체멤버조회() {
    }

    @Test
    public void 특정멤버조회() {
    }
}