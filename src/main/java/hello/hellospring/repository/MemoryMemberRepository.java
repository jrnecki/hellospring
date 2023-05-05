package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>(); // 공유되는 변수일 때 동시성 문제가 생길 수 있음.
    private static long sequence = 0l; // 시퀀스로 일단 해결. 0,1,2 키 값을 생성해줌.
    @Override
    public Member save(Member member) {
         member.setId(++sequence);
         store.put(member.getId(), member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
       // optional로 반환해줌.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
