package com.testproject.testproject.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private MessageSource messageSource;

    @GetMapping(path = "hello")
    public String helloWorld(){
        return "hello world\n";
    }

    @GetMapping(path = "helloworldbean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");

    }

    @GetMapping(path = "helloworld/pathvar/{name}")
    public HelloWorldBean helloWorldPath(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldI18nized() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
