package hello.hellospring.repository;

import hello.hellospring.domain.member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    member save(member member);

    // Optional: handling null
    Optional<member> findById(Long id);

    Optional<member> findByName(String name);

    List<member> findAll();
}