package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach // 각 테스트를 실행하기 전에 무조건 실행
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
       memberService = appConfig.memberService();
    }

    /**
     * 회원 도메인 설계의 문제점
     *
     * 이 코드의 설계상 문제점은 무엇일까요?
     * 다른 저장소로 변경할 때 OCP 원칙을 잘 준수 할까요?
     * DIP를 잘 지키고 있을까요? NO ( MemberServiceImpl이 추상화(MemberRepository)에도 구현체(MemoryMemberRepository)에도 의존함
     * 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
     *  -> 주문까지 만들고나서 문제점과 해결 방안을 설명
     *
     */
    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMeber = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMeber);

    }
}