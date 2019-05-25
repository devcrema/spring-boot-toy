package devcrema.spring_boot_toy.controller;

import devcrema.spring_boot_toy.chef.SignUpChefRequest;
import devcrema.spring_boot_toy.chef.SignUpChefService;
import devcrema.spring_boot_toy.user.SignUpUserRequest;
import devcrema.spring_boot_toy.user.service.SignUpUserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/chefs")
@AllArgsConstructor
public class SignUpChefController {

    private SignUpChefService signUpChefService;
    private ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity signUpUser(@RequestBody @Valid SignUpChefRequest request){

        signUpChefService.signUp(request.toChef(modelMapper));

        return new ResponseEntity(HttpStatus.OK);
    }
}