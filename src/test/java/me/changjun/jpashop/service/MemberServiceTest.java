package me.changjun.jpashop.service;

import me.changjun.jpashop.domain.Member;
import me.changjun.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setUsername("changjun");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.find(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 회원가입_중복_회원() {
        //given
        Member member1 = new Member();
        member1.setUsername("changjun");

        Member member2 = new Member();
        member2.setUsername("changjun");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");
    }
}