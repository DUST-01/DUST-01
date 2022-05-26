package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 얘네들은 공유되는 값들. 실습하면서 값을 담기위한 용도이다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
         member.setId(++sequence); // auto increment 역할
         // id는 시스템에서 자동적으로 정해준다. 이에 따라 멤버를 map형태로 저장.
         store.put(member.getId(), member);

         return member;
    }

    @Override
    public Optional<Member> findById(Long Id) {
        
        // 그냥 store.get(Id) 로 할수도 있겠으나 null값이 나오는 경우, 
            // 클라이언트에서 아무것도 못하는 것을 방지하기위해
        return Optional.ofNullable(store.get(Id)); 
    }

    @Override
    public Optional<Member> findByName(String name) {

        // store 에 담긴 값들을 돌면서 filter 안에 있는 조건에 해당하는 것 아무거나 반환.
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();

    }

    // 실무에서는 List 형태를 쓴다.
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    
    // 메모리에서 꺼내는 저장소이기 때문에 가능한것
    public void clearStore(){
        store.clear();

    }


}
