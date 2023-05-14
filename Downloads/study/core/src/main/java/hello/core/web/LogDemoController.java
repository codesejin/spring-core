package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController { // 로거가 잘 작동하는지 확인하는 테스트용 컨트롤러
    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider; // MyLogger을 주입받는게 아니라 찾을 수 있음 - Dependency lookup

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(100); // 요청의 속도 구분, 각 요청마다 스프링 빈 할당
        logDemoService.logic("testId");
        return "OK";
    }
}