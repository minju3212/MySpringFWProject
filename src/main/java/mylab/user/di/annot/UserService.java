package mylab.user.di.annot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("userService")
public class UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private SecurityService securityService;
	@Value("${user.id}") // 외부 프로퍼티 값
    private String userId;

    @Value("${user.name}") // 외부 프로퍼티 값
    private String name;

    @Value("${user.password}") // 외부 프로퍼티 값
    private String password;  
    
    public UserRepository getUserRepository() { return userRepository; }
    public SecurityService getSecurityService() { return securityService; }
    
    public boolean registerUser() {
        if (securityService.authenticate(userId, password)) {
            return userRepository.saveUser(userId, name);
        }
        return false;
    }
}