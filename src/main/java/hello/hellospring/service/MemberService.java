package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemoryMemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }



    public Long join(Member member){

        validateDuplicateMember(member);


        // 위와 아래가 같다.
//        // 요구사항 - 같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        // result 에서  존재하는 값이 있다면(ifPresent)
//        result.ifPresent(m->{
//            throw new IllegalStateException("이미 존재하는 회원입니다. ");
//        });

        // optional 부분이 지저분해보이기 때문에 그냥 바로 memberRepository(@@@).ifPresent 로 들어가버린다.
            /*
                memberRepository.findByName(member.getName()
                        .ifPresent(m->{
                               throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });

             */ // 이런건 따로 메서드를 추출하는 게 좋다! 리팩토링!!!

        memberRepository.save(member);

         return member.getId();
    }

    // 중복회원 여부 확인
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

    }
    
    // 전체 회원조회
    public List<Member> findMembers(){

        return memberRepository.findAll();
    }

    // 특정 회원을 id로 조회
    public Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }
    

}
