package devcrema.spring_boot_toy.controller;

import devcrema.spring_boot_toy.Api;
import devcrema.spring_boot_toy.user.SignUpUserRequest;
import devcrema.spring_boot_toy.user.service.SignUpUserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Api.User.RESOURCE)
@AllArgsConstructor
public class SignUpUserController {

    private SignUpUserService signUpUserService;
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void signUpUser(@RequestBody @Valid SignUpUserRequest request){
        signUpUserService.signUp(request.toUser(modelMapper));
    }
}