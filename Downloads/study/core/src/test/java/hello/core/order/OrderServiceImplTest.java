package hello.core.order;

import hello.core.discount.FixDisocuntPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test// 순수한 자바 코드로 테스트하기
    void createOrder() {
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDisocuntPolicy());
        // 수정자 주입으로 할 경우 createOrder() 메소드만 테스트하고 싶어도 다른 repository가 필요하므로 DUMMY 필요
        // 생성자 주입으로 할 경우 new OrderServiceImpl() 객체 생성 시 컴파일 오류나서 임의로 넣거나 MOCK 객체 사용
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}