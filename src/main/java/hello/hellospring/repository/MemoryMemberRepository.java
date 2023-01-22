// 2023-01-22
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 회원 리포지토리 메모리 구현체
public class MemoryMemberRepository implements MemberRepository{

    /*
        동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;      // key 값을 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));      // null 이 반환될 가능성이 있기때문에 Optional.ofNullable 로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();     // 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
