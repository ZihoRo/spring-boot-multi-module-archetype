#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${userName} on ${today}.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWorld() {
        return "hello world";
    }
}
