package dika.springweb.service;

import dika.springweb.model.User;
import dika.springweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        User userUpdate = userOptional.get();
        updateIfNotNull(user.getName(), userUpdate::setName);
        updateIfNotNull(user.getSurname(), userUpdate::setSurname);
        updateIfNotNull(user.getEmail(), userUpdate::setEmail);
        userRepository.save(userUpdate);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void updateIfNotNull(String newParam, Consumer<String> oldParam) {
        if (newParam != null) {
            oldParam.accept(newParam);
        }
    }

}
