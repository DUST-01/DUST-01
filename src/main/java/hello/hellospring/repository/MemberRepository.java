package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    // optional은 자바 8부터 나오는 것인데, 혹시 null이 나올 수 있어서 이에 감싸서 진행
    Optional<Member> findById(Long Id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
