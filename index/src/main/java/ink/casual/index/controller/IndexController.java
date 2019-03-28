package ink.casual.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * @author lwt
 * @date 2019/3/28 10:28
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public Mono<String> index(final Model model){
        model.addAttribute("name","lwt");
        return Mono.create(x->x.success("index"));
    }

}
