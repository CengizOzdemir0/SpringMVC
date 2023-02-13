package com.works.controllers;

import com.works.entities.Login;
import com.works.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    final LoginService loginService;

    @GetMapping("/register")
    public String register(Model model){
        // validasyonu yaparken elemanları göndermek
        model.addAttribute("login",new Login());
        return "register";
    }

    @PostMapping("/adminRegister")
    public String adminRegister(@Valid Login login,  BindingResult bindingResult,Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getFieldErrors());
            List<FieldError> fieldErrorList= bindingResult.getFieldErrors();
            for(FieldError error : fieldErrorList){
                System.out.println(error.getField() + " " +error.getDefaultMessage());
            }
            return "register";

        }
        System.out.println(login);
        boolean status = loginService.register(login);
        if(status){
            return "redirect:/";
        }
        return "register";
    }
}
