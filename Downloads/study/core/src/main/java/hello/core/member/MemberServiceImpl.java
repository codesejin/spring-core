package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        /**
         * join에서 save를 호출하면 다형성에 의해
         * MemberRepository인터페이스가 아니라 MemoryMemberRepository에 있는 @오버라이드한 save가 호출됌
         */
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
