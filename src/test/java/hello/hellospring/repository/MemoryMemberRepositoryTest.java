package hello.hellospring.repository;

import hello.hellospring.domain.member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        member member = new member();
        member.setName("spring");

        repository.save(member);

        hello.hellospring.domain.member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
        // assertThat(member).isEqualTo(null); // error!!
    }

    @Test
    public void findByName() {
        member member1 = new member();
        member1.setName("name1");
        repository.save(member1);

        member member2 = new member();
        member2.setName("name2");
        repository.save(member2);

        member result = repository.findByName("name1").get();
        assertThat(result).isEqualTo(member1);
        // assertThat(result).isEqualTo(member2); // error!!
    }

    @Test
    public void findAll() {
        member member1 = new member();
        member1.setName("name1");
        repository.save(member1);

        member member2 = new member();
        member2.setName("name2");
        repository.save(member2);

        List<member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        // assertThat(result.size()).isEqualTo(3); // error!!
    }
}
