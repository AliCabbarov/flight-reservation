package ingress.flightms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableFeignClients(basePackages = "ingress.flightms.config.client")
public class FlightMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlightMsApplication.class, args);
    }

}
