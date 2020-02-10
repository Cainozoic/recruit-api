package top.wy.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wy.base.context.WebContext;

@RestController
@RequestMapping("test")
@Slf4j
public class TestService {

    @GetMapping
    public String test() {
        log.info("current-user = {}", WebContext.getCurrentUserId());
        log.info("current-request = {}", WebContext.getRequest());
        return Thread.currentThread().getName();
    }

}
