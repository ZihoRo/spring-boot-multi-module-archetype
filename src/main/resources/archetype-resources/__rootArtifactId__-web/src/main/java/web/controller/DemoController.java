#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller;

import java.util.Date;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${userName} on ${today}.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET, params = {"!name"})
    public String helloWorld() {
        return "hello world";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET, params = {"name"})
    public String hello(@RequestParam("name") String name) {
        return String.format("%s, 你好", name);
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public Date date() {
        return new Date();
    }
}
