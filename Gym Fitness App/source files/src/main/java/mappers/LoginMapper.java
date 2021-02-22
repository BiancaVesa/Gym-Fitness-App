package mappers;

import dto.LoginDto;
import entity.Login;

import java.util.function.Function;

public class LoginMapper {
    public static Function<Login, LoginDto> entityToDto = login -> new LoginDto(login.getPassword(), login.getUserName());
    public static Function<LoginDto, Login> DtoToEntity = login -> null;
}
