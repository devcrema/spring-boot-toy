package devcrema.spring_boot_toy.controller;

import devcrema.spring_boot_toy.Api;
import devcrema.spring_boot_toy.chef.SignUpChefRequest;
import devcrema.spring_boot_toy.chef.SignUpChefService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Api.Chef.CHEFS)
@AllArgsConstructor
public class SignUpChefController {

    private SignUpChefService signUpChefService;
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void signUpChef(@RequestBody @Valid SignUpChefRequest request){
        signUpChefService.signUp(request.toChef(modelMapper));
    }
}