package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDisocuntPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    // 생성자 주입 : 생성자를 통해 객체가 들어간다.
    // 역할과 그 역할에 대한 구현이 한 눈에 구분된다, 중복이 제거됨 : 애플리케이션 구성이 어떻게 되어있는지 빠르게 파악 가능
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDisocuntPolicy();
        return new RateDiscountPolicy();
    }
}
