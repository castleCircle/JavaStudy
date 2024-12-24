package sample.reactorproject.section03;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.net.URI;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import sample.reactorproject.utils.Logger;

/**
 * Mono 기본 개념 예제
 */
@Slf4j
public class MonoExample3 {

  public static void main(String[] args) {
    URI worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Asia/Seoul")
        .build()
        .encode()
        .toUri();

    RestTemplate restTemplate = new RestTemplate();
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    Mono.just(
        restTemplate.exchange(worldTimeUri, HttpMethod.GET,new HttpEntity<>(httpHeaders), String.class)
    )
        .map(
            response -> {
              DocumentContext jsonContext = JsonPath.parse(response.getBody());
              String dateTime = jsonContext.read("$.datetime", String.class);
              return dateTime;
            })
        .subscribe(
            data -> Logger.info("# emitted data:{}",data),
            error -> {
              Logger.onError(error);
            },
            () -> Logger.info("# emitted onComplete signal")
        );

  }

}
