package kr.co._29cm.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * jpa 작성자, 수정자에 자동 데이터 삽입 설정
 */
@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        //todo 만약 security나 redis session을 사용한다면 해당 객체를 통해 유저 정보를 가지고 와서 세팅 해 줄 수 있다.
        return () -> Optional.of("이채경");
    }

}
