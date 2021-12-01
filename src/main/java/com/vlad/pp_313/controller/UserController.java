package com.vlad.pp_313.controller;

import com.vlad.pp_313.model.User;
import com.vlad.pp_313.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping("/admin/all_users")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", service.index());
        model.addAttribute("thisUser", service.getUserByName(principal.getName()));
        model.addAttribute("newUser", new User());
        return "/admin/all_users";
//        .html
    }

    @GetMapping("/user_info/{id}")
    public String show(@PathVariable("id") Long id, Model model, Principal principal) {
        if (!service.isAllowed(id, principal)) {
            id = service.getUserByName(principal.getName()).getId();
            model.addAttribute(service.getUserByName(principal.getName()));
        } else {
            model.addAttribute(service.getUser(id));
        }
        return "/user_info";
    }

    @GetMapping("/user_info")
    public void userInfo(Principal principal, Model model) {
        User user = service.getUserByName(principal.getName());
        model.addAttribute(user);
        show(user.getId(), model, principal);
    }

    @GetMapping("/admin/new_user")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin";
    }

    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/admin/all_users";
    }

    @GetMapping("/user_info/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", service.getUser(id));
        return "/admin/update_user";
    }

    @PatchMapping("/user_info/update")
    public String update(@ModelAttribute("user") User newUser) {
        service.update(newUser);
        return "redirect:/admin/all_users";
    }

    @DeleteMapping("/user_info/delete")
    public String delete(@ModelAttribute("user") User user) {
        service.delete(user.getId());
        return "redirect:/admin/all_users";
    }

}
