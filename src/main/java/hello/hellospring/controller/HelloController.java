package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        // http://localhost:8080/hello
        // Using ViewResolver
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        // http://localhost:8080/hello-mvc?name=john
        // Using ViewResolver
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("not-html")
    @ResponseBody
    public String notHtml(@RequestParam("name") String name) {
        // http://localhost:8080/not-html?name=john, returns String
        // @ResponseBody -> Using StringHttpMessageConverter
        return "I'm not HTML, " + name;
    }

    @GetMapping("api-call")
    @ResponseBody
    public Person apiCall(@RequestParam("name") String name) {
        // http://localhost:8080/api-call?name=john, returns JSON object
        // @ResponseBody -> Using MappingJackson2HttpMessageConverter
        // Jackson : Library converting Java Object to JSON
        Person person = new Person();
        person.setName(name);
        return person;
    }

    static class Person {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;


    }
}