package ingress.flightms.config.client;

import ingress.common.config.JwtSessionData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.concurrent.CompletableFuture;


@FeignClient(name = "userClient", url = "${user-client.url}" + "/api/v1/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserResponseDto getUserDetailsById(@RequestHeader("Authorization") String token,@PathVariable Long id);
}
