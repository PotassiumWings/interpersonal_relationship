package com.relation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relation.pojo.User;
import com.relation.service.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/user")
public class Login {

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("key") String key,
                        HttpSession session, Model model) throws SQLException, JsonProcessingException {
        User searchUser = Service.UserService.searchUser(username);
        if (!searchUser.getKey().equals(key)) {
            model.addAttribute("msg", "密码错误");
            System.out.println("wrong password!");
            return "redirect:/login.jsp";
        }
        session.setAttribute("user", searchUser);
        ObjectMapper mapper=new ObjectMapper();
        model.addAttribute("msg","登陆成功！" + mapper.writeValueAsString(searchUser));
        return "redirect:/index.jsp";
    }
}
