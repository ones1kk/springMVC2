package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("massageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("userName={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-json-v2")
    @ResponseBody
    public String requestBodyJsonV2(@RequestBody String messageBody)
        throws IOException {

        log.info("massageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("userName={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }


    @PostMapping("/request-body-json-v3")
    @ResponseBody
    public String requestBodyJsonV3(@RequestBody HelloData helloData)
        throws IOException {
        log.info("userName={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @PostMapping("/request-body-json-v4")
    @ResponseBody
    public String requestBodyJsonV4(HttpEntity<HelloData> data)
        throws IOException {
        HelloData helloData = data.getBody();
        log.info("userName={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @PostMapping("/request-body-json-v5")
    @ResponseBody
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData)
        throws IOException {
        log.info("userName={}, age={}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }

}
