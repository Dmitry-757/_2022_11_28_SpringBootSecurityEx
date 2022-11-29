package org.dng.springbootsecurityex.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class AuthController {

    @RequestMapping("/hello")
    public ModelAndView home(Principal principal) {
        int i=0;
        return (new ModelAndView("helloPage")).addObject("principal", principal);
    }
    @GetMapping("/mylogin")
    public String login() {
        return "myloginPage";
    }

    //***** не перехватывается (( хотя пост-запрос из myloginPage отправляется именно сюда
    @PostMapping("/myLoginProcessing")
//    public String myloginPage() {
    public void myloginPage() {
        System.out.println("catch '/myLoginProcessing' ");
//        return "myloginPage";
    }

}
