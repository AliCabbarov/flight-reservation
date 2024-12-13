package ingress.userms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class UserMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserMsApplication.class, args);
    }

}
