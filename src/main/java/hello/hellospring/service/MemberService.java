package hello.hellospring.service;

import hello.hellospring.domain.member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        // dependency injection
        this.memberRepository = memberRepository;
    }

    /**
     * Sign up
     */
    public Long signUp(member member) {
        // not allow same member name
        validateDuplicatedMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("Member " + member.getName() + " already exists.");
                });
    }

    /**
     * Find all members
     */
    public List<member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
