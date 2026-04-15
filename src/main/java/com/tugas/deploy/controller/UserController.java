package com.tugas.deploy.controller;

import com.tugas.deploy.model.User; // Pastikan import class model-nya benar
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final String USERNAME = "gibran";
    private final String PASSWORD = "20230140175";

    // untuk menyimpan data mahasiswa secara temporary
    private List<User> userList = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login (@RequestParam String username,
                         @RequestParam String password,
                         Model model) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau Password salah!");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("users", userList);
        model.addAttribute("myNim", PASSWORD); // Bisa dipakai untuk nampilin NIM di header
        return "home";
    }


    @GetMapping("/form")
    public String formPage() {
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(User user) {

        userList.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}