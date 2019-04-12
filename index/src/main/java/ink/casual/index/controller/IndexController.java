package ink.casual.index.controller;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lwt
 * @date 2019/3/28 10:28
 */
@Controller
public class IndexController {

    private static TestEventSource testEventSource = new TestEventSource();

    @GetMapping("/")
    public Mono<String> index(final Model model) {
        model.addAttribute("name", "lwt");
        return Mono.create(x -> x.success("index"));
    }

    @GetMapping(value = "/one", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<ServerSentEvent<TestEventSource.TestEven>> one() {
        return Flux.create(x ->
            testEventSource.register(new TestEventListener() {
                @Override
                public void onNewEvent(TestEventSource.TestEven event) {
                    x.next(event);
                }

                @Override
                public void onEventStopped() {
                    x.complete();
                }
            })
        ).map(x-> ServerSentEvent.<TestEventSource.TestEven>builder().data((TestEventSource.TestEven)x).build());
    }

    @GetMapping("/two")
    @ResponseBody
    public void two() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            testEventSource.newEvent(new TestEventSource.TestEven(String.valueOf(random.nextInt())));
            TimeUnit.SECONDS.sleep(1);
        }
        testEventSource.eventStopped();
    }



}
