package com.lys.user.controller;

import com.lys.user.entity.User;
import com.lys.user.UserNotFoundException;
import com.lys.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping(value = "/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping(value = "/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping(value = "/users/save")
    public String saveUer(User user) {
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return "redirect:/users";
        }
    }

    @GetMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }
}
