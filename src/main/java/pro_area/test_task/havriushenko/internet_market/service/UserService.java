package pro_area.test_task.havriushenko.internet_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pro_area.test_task.havriushenko.internet_market.converter.UserConverter;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;
import pro_area.test_task.havriushenko.internet_market.exception.UserExistException;
import pro_area.test_task.havriushenko.internet_market.model.Role;
import pro_area.test_task.havriushenko.internet_market.model.UserModel;
import pro_area.test_task.havriushenko.internet_market.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static pro_area.test_task.havriushenko.internet_market.util.Constans.MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION;
import static pro_area.test_task.havriushenko.internet_market.util.Constans.MESSAGE_USER_WITH_THIS_EMAIL_ALREADY_EXISTS;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserConverter userConverter;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userConverter = userConverter;
    }

    public void singUp(UserDto user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserExistException(MESSAGE_USER_WITH_THIS_EMAIL_ALREADY_EXISTS + " (" + user.getEmail() + ")" );
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(Collections.singleton(Role.USER));
        userRepository.save(userConverter.convertToModel(user));
    }

    public UserDto findByEmail(String email){
        Optional<UserModel> model = userRepository.findByEmail(email);
        if(model.isPresent()){
               return userConverter.convertToDto(model.get());
        }
        throw new UsernameNotFoundException(MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION);
    }

    public UserModel getUserModelByEmail(String email){
        Optional<UserModel> model = userRepository.findByEmail(email);
        if(model.isPresent()){
            return model.get();
        }
        throw new UsernameNotFoundException(MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION);
        }
        return new User(user.get().getEmail(), user.get().getPassword(), emptyList());
    }
}
