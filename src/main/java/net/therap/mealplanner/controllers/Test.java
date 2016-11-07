package net.therap.mealplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rumman
 * @since 11/7/16
 */
@Controller
public class Test {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hellow() {
        return "yello";
    }
}
