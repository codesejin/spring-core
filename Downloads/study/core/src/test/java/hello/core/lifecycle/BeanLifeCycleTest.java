package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // ApplicationContext 을 상속받은 ConfigurableApplicationContext
        // ConfigurableApplicationContext 을 구현한 AnnotationConfigApplicationContext
        // ConfigurableApplicationContext가 AnnotationConfigApplicationContext의 상위 인터페이스이기 때문에 담을 수 있음
        // 부모는 자식을 담을 수 있음
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            // 객체를 생성한 다음에 설정이 들어올 수 있다
            // 자동 의존관계 주입에서도 보면 세터,필드로 인젝션할 수 있는데
            // 외부에서 의존관계를 세팅하고 나서 초기화를 호출해야할때가 많다
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
