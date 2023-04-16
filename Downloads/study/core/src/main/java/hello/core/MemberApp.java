package hello.core;

import hello.core.member.*;

public class MemberApp {
    // psvm
    public static void main(String[] args) {
        // 기존에는 MemberServiceImpl을 직접 메인 메소드에서 생성해줌
        // 그 다음 MemberServiceImpl내에서는 또 MemoryMemberReposity를 생성해줌
        // 순차적으로 생성되듯이.
        // 이제는 AppConfig에서 결정한다.
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl(memberRepository);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
