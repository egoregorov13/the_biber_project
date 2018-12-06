package the_biber_project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import the_biber_project.NameCheck;
import the_biber_project.User;
import the_biber_project.repos.MemRepInMemoryMessageRepository;
//import org.springframework.in

@Controller
public class MainController {

    MemRepInMemoryMessageRepository usrs = new MemRepInMemoryMessageRepository();


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexGet(HttpServletRequest request, Model model) {
        model.addAttribute("users", usrs.usersStr);
        return "chat";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
        username = username.trim();

        if (username.isEmpty()) {
            return "login";
        }

        if (username == null || username.isEmpty() || !(NameCheck.checkName(username))) {
            return "redirect:/login";
        }

        request.getSession().setAttribute("username", username);
        System.out.println(username);
        User us = new User();
        us.setUserName(username);
        usrs.save(us);


        return "redirect:/";
    }

    @RequestMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();


        return "redirect:/login";
    }

}