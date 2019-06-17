package devcrema.spring_boot_toy.user;

import devcrema.spring_boot_toy.validation.ValidNickname;
import devcrema.spring_boot_toy.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserRequest {

    @NotNull(message = "email이 존재하지 않습니다.")
    @Email(message = "잘못된 이메일형식입니다.")
    private String email;

    @ValidNickname
    private String nickname;

    @ValidPassword
    private String password;

    public User toUser(ModelMapper modelMapper){
        return modelMapper.map(this, User.class);
    }
}
