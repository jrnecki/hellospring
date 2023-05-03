package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "nickname") String nickname, Model model){
        model.addAttribute("name",nickname);
        return "hello-template";
    }
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloSpring(@RequestParam("name") String name){
        return "hello"+name;
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // inner class
    static class Hello{
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        private String name;
    }
}
