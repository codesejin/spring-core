package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDisocuntPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDisocuntPolicy();

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
}
