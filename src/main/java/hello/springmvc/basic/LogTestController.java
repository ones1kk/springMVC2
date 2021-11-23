package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        log.trace("trace log = {}, {}", name, "name2");
        log.debug("debug log ={} ", name);
        log.info("info log ={} ", name);
        log.warn("warn log ={} ", name);
        log.error("error log ={} ", name);

        return "ok";
    }

}
