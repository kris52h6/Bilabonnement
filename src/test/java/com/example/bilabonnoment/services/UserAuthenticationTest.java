package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.User;
import com.example.bilabonnoment.repositories.LoginTestRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserAuthenticationTest {

    LoginTestRepository loginRepository = new LoginTestRepository();
    LoginService loginService = new LoginService(loginRepository);

    @Test
    void getUserFromUsernameAndPassword(){

        //Arrange
            // Valid Data
                String validUsername = "bennybooster";
                String validPassword = "dakkedak";

            // Invalid Data
                String invalidUsername = "bennysbiks";
                String invalidPassword = "kagemand";

            // User with same data
                User expectedUser = new User(1,"bennybooster","dakkedak", "DAMAGE");

        //Act
            // Valid username and password
                User actualUser = loginService.authenticateUser(validUsername, validPassword);

            // Invalid password
                User invalidUserWrongPassword = loginService.authenticateUser(validUsername, invalidPassword);

            // Invalid username
                User invalidUserWrongUsername = loginService.authenticateUser(invalidUsername, validPassword);

        //Assert
            // expected data with repository data

                assertEquals(expectedUser.getId(),actualUser.getId());
                assertEquals(expectedUser.getUsername(),actualUser.getUsername());
                assertEquals(expectedUser.getPassword(),actualUser.getPassword());
                assertEquals(expectedUser.getRole(),actualUser.getRole());

            // invalid password returns null
                assertEquals(null,invalidUserWrongPassword);

            // invalid username return null
                assertEquals(null, invalidUserWrongUsername);





    }




}
