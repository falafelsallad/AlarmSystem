package summerprojects2025.alarmsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import summerprojects2025.alarmsystem.model.Customer;
import summerprojects2025.alarmsystem.repository.CustomerRepository;
import summerprojects2025.alarmsystem.utility.HashUtility;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginService = new LoginService(customerRepository);
    }

    @Test
    void loginWithValidCredentials_ShouldReturnCustomer() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String hashedPassword = HashUtility.SHA256(password);

        Customer mockCustomer = new Customer();
        mockCustomer.setEmail(email);
        mockCustomer.setPasswordHash(hashedPassword);

        when(customerRepository.findByEmail(email.toLowerCase())).thenReturn(Optional.of(mockCustomer));

        // Act
        Customer result = loginService.login(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    void loginWithNonExistentEmail_ShouldThrowNoSuchElementException() {
        // Arrange
        String email = "nonexistent@example.com";
        String password = "password123";

        when(customerRepository.findByEmail(email.toLowerCase())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () ->
                loginService.login(email, password)
        );
    }

    @Test
    void loginWithIncorrectPassword_ShouldThrowRuntimeException() {
        // Arrange
        String email = "test@example.com";
        String correctPassword = "password123";
        String incorrectPassword = "wrongPassword";
        String hashedPassword = HashUtility.SHA256(correctPassword);

        Customer mockCustomer = new Customer();
        mockCustomer.setEmail(email);
        mockCustomer.setPasswordHash(hashedPassword);

        when(customerRepository.findByEmail(email.toLowerCase())).thenReturn(Optional.of(mockCustomer));

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                loginService.login(email, incorrectPassword)
        );
    }
}