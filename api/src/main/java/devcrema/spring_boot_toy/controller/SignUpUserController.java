package devcrema.spring_boot_toy.controller;

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
@RequestMapping("/api/users")
@AllArgsConstructor
public class SignUpUserController {

    private SignUpUserService signUpUserService;
    private ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity signUpUser(@RequestBody @Valid SignUpUserRequest request){

        signUpUserService.signUp(request.toUser(modelMapper));

        return new ResponseEntity(HttpStatus.OK);
    }
}