package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // executing sequence of methods are not same as code
    // @AfterEach: callback function which is executed after each method finish
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
        // assertThat(member).isEqualTo(null); // error!!
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("name1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("name2");
        repository.save(member2);

        Member result = repository.findByName("name1").get();
        assertThat(result).isEqualTo(member1);
        // assertThat(result).isEqualTo(member2); // error!!
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("name1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("name2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        // assertThat(result.size()).isEqualTo(3); // error!!
    }
}
