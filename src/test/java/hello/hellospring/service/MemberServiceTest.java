package hello.hellospring.service;

import hello.hellospring.domain.member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
     void signUp() {
        //given
        member newMember = new member();
        newMember.setName("name");

        //when
        Long newMemberId = memberService.signUp(newMember);

        //then
        member foundMember = memberService.findOne(newMemberId).get();
        assertThat(newMember.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    public void duplicatedMember() {
        //given
        member member1 = new member();
        member1.setName("name");

        member member2 = new member();
        member2.setName("name");

        //when
        memberService.signUp(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.signUp(member2));

        assertThat(e.getMessage()).isEqualTo("Member " + member2.getName() + " already exists.");
        // assertThrows(NullPointerException.class, () -> memberService.signUp(member2)); // error!!
/*
        try {
            memberService.signUp(member2);
            Assertions.fail("Exception must be occur.");
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Member " + member2.getName() + " already exists.");
            // assertThat(e.getMessage()).isEqualTo("Member already exists."); // error!!
        }
*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}