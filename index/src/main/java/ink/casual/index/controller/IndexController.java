package ink.casual.index.controller;

import ink.casual.common.model.CommonReturnCodeEnum;
import ink.casual.common.model.PreFixEnum;
import ink.casual.common.model.ResponseModel;
import ink.casual.common.util.CaptchaUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
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
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /*@GetMapping("/")
    public Mono<String> index(final Model model) {
        model.addAttribute("name", "lwt");
        return Mono.create(x -> x.success("index"));
    }*/

    @GetMapping("/picCode")
    @ResponseBody
    public ResponseModel<String> getPicCode(String mobile) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(CaptchaUtils.generatorCaptcha(mobile), "png", bao);
        byte[] bytes = bao.toByteArray();
        return new ResponseModel<>(CommonReturnCodeEnum.SUCCESS.getCode(), PreFixEnum.BASE64_PREFIX.getPreFix().concat(new String(Base64.encodeBase64(bytes), Charset.forName("UTF-8"))));
    }

    @PostMapping("/picCode")
    @ResponseBody
    public ResponseModel verifyPicCode(String mobile,String picCode){
        CaptchaUtils.validPic(mobile,picCode);
        return new ResponseModel(CommonReturnCodeEnum.SUCCESS.getCode());
    }

    @GetMapping("/smsCode")
    @ResponseBody
    public ResponseModel sendSmsCode(String mobile){
        CaptchaUtils.generateSMSCode(mobile);
        return new ResponseModel(CommonReturnCodeEnum.SUCCESS.getCode());
    }

    @PostMapping("/smsCode")
    @ResponseBody
    public ResponseModel verifyCode(String mobile,String smsCode){
        CaptchaUtils.validatorSMSCode(mobile,smsCode);
        return new ResponseModel(CommonReturnCodeEnum.SUCCESS.getCode());
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
