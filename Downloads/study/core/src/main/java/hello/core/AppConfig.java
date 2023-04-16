package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // @Configuration : 애플리케이션의 구성과 설정 정보
public class AppConfig {

    @Bean // 스프링 컨테이너에 등록 : Bean이 메서드 이름으로 등록됨
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDisocuntPolicy();
        return new RateDiscountPolicy();
    }

    /**
     * 실행시키면 순수 자바로 했을때와 다르게 로그가 찍힌다.
     * Creating shared instance of singleton bean 'appConfig'
     * Creating shared instance of singleton bean 'memberService'
     * Creating shared instance of singleton bean 'memberRepository'
     * Creating shared instance of singleton bean 'orderService'
     * Creating shared instance of singleton bean 'discountPolicy'
     */
}
