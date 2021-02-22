package service;

import dto.LoginDto;
import entity.Client;
import entity.Login;
import entity.Profile;
import mappers.LoginMapper;
import repository.ClientRepo;
import repository.LoginRepo;
import validator.LoginValidator;
import validator.ProfileValidator;

public class LoginService {

    private final LoginRepo loginRepo;
    private final ClientRepo clientRepo = new ClientRepo();

    public LoginService() {
        this.loginRepo = new LoginRepo();
    }

    public Login getLogin(String idCredentials) {
        return loginRepo.findLoginById(idCredentials);
    }

    public void modifyUserName(String idCredentials, String newUserName) {
        LoginValidator.validateExistingUserName(newUserName);
        loginRepo.updateLoginUsername(getLogin(idCredentials), newUserName);
    }

    public void modifyUserNameTrainer(String idTrainer, String idCredentials, String newUserName) {
        LoginValidator.validateNewUserName(idTrainer, newUserName);
        loginRepo.updateLoginUsername(getLogin(idCredentials), newUserName);
    }

    public void modifyPassword(String idCredentials, String newPassword) {
        LoginValidator.validatePassword(getLogin(idCredentials), newPassword);
        loginRepo.updateLoginPassword(getLogin(idCredentials), newPassword);
    }

    public void modifyPasswordTrainer(String idCredentials, String newPassword, String reNewPass) {
        LoginValidator.validatePasswordTrainer(reNewPass, newPassword);
        loginRepo.updateLoginPassword(getLogin(idCredentials), newPassword);
    }

    public LoginDto registerUser(Client clientToBeRegistered, Profile clientProfile) {
        Login clientLogin = clientToBeRegistered.getClientCredentials();
        LoginDto loginDto = LoginMapper.entityToDto.apply(clientLogin);

        ProfileValidator.validateProfile(clientProfile);

        clientToBeRegistered.setProfile(clientProfile);

        clientRepo.insertNewClient(clientToBeRegistered);

        return loginDto;
    }

    public boolean existentCredentialsFound(String userName, String password) {
        return loginRepo.findByUsername(userName).getPassword().equals(password);
    }
}
