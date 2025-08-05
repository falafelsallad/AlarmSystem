package summerprojects2025.alarmsystem.service.user;

import org.springframework.stereotype.Service;
import summerprojects2025.alarmsystem.DTO.UserRegistrationDTO;
import summerprojects2025.alarmsystem.model.User;
import summerprojects2025.alarmsystem.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public void saveUserToCentralUnit(User user) {
        userRepository.save(user);
    }

    public User createUserForCustomer(Long customerId, UserRegistrationDTO userDTO) {
        return null;
    }
}
