package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){

        long start = System.currentTimeMillis();

        // 같은 이름이 있는 중복 회원 x

        validateDuplicateMember(member); // 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }


    // 메소드 추출하기 shift + alt +m
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw  new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){
        // 네이밍의 경우 각 상황에 맞게 구성을 하자
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}


