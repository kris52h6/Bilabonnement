package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.User;
import com.example.bilabonnoment.repositories.LoginTestRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
/**
 * @author Oliver
 */
public class UserAuthenticationTest {

    @Test
    void getUserFromUsernameAndPassword(){
        //Arrange
        // Init
        LoginTestRepository loginRepository = new LoginTestRepository();
        LoginService loginService = new LoginService(loginRepository);
        // Valid Data
        String validUsername = "damageuser";
        String validPassword = "damagepassword";
        // Invalid Data
        String invalidUsername = "adminuser";
        String invalidPassword = "adminpassword";
        // User with same data
        User expectedUser = new User(1,"damageuser","damagepassword", "DAMAGE");

        //Act
        // Valid username and password
        User actualUser = loginService.authenticateUser(validUsername, validPassword);
        // Invalid password
        User invalidUserWrongPassword = loginService.authenticateUser(validUsername, invalidPassword);
        // Invalid username
        User invalidUserWrongUsername = loginService.authenticateUser(invalidUsername, validPassword);

        //Assert
        // expected data compared to repository data
        assertEquals(expectedUser.getId(),actualUser.getId());
        assertEquals(expectedUser.getUsername(),actualUser.getUsername());
        assertEquals(expectedUser.getPassword(),actualUser.getPassword());
        assertEquals(expectedUser.getRole(),actualUser.getRole());
        // invalid password returns null
        assertNull(invalidUserWrongPassword);
        // invalid username return null
        assertNull(invalidUserWrongUsername);

    }
}
