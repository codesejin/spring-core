package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    // final을 사용하면 초기화 단계에서 무조건 값이 할당되어야 한다.(기본으로 하던, 생성자로 하던)
    // 생성자 만들때 누락되어도 컴파일 오류로 알 수 있다
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
        /**
         * 설계가 잘 된 이유?
         * OrderService 입장에서는 할인에 대해서는 모르겠고 DiscountPolicy 너가 알아서하고 결과만 던져줘
         * 단일체계원칙을 잘 지킨 것
         * 할인쪽 변경이 생기면 할인만 고치면 되고, 주문쪽은 건드리지 않아도 된다
         */
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
