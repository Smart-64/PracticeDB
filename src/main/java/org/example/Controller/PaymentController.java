package org.example.Controller;

import org.example.Utils.PaymentRequest;
import org.example.Utils.BaseResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final String sharedKey = "test";

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 100;
    private static final int AUTH_FAILURE = 102;
    @GetMapping
    public BaseResponse showStatus() {
        return new BaseResponse(SUCCESS_STATUS, 777);
    }

    @GetMapping("/Greetings")
    public String showName(@RequestParam String name) {
        return "Hello, " + name + " !!!";
    }

    @PostMapping(value = "/pay", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String pay(@RequestParam String key, @RequestBody PaymentRequest request) {
        return request.toString();

    }
}
