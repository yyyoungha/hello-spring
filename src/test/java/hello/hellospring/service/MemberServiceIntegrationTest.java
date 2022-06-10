package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
     void signUp() {
        //given
        Member newMember = new Member();
        newMember.setName("name");

        //when
        Long newMemberId = memberService.signUp(newMember);

        //then
        Member foundMember = memberService.findOne(newMemberId).get();
        assertThat(newMember.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    public void duplicatedMember() {
        //given
        Member member1 = new Member();
        member1.setName("name");

        Member member2 = new Member();
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