package pro_area.test_task.havriushenko.internet_market.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pro_area.test_task.havriushenko.internet_market.converter.UserConverter;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;
import pro_area.test_task.havriushenko.internet_market.exception.UserExistException;
import pro_area.test_task.havriushenko.internet_market.model.Role;
import pro_area.test_task.havriushenko.internet_market.model.UserModel;
import pro_area.test_task.havriushenko.internet_market.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pro_area.test_task.havriushenko.internet_market.util.Constans.MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION;
import static pro_area.test_task.havriushenko.internet_market.util.Constans.MESSAGE_USER_WITH_THIS_EMAIL_ALREADY_EXISTS;
import static pro_area.test_task.havriushenko.internet_market.utils.ConstansForTest.*;

@RunWith(JUnit4.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserModel userModel1;
    private UserModel userModel2;
    private UserDto userDto1;
    private UserDto userDto2;

    private UserService tested;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        createTestedUserModels();
        createTestedUserDtos();

        tested = new UserService(userRepository, bCryptPasswordEncoder, userConverter);
    }

    private void createTestedUserModels() {
        userModel1 = new UserModel();
        userModel1.setId(FIRST_TEST_ID);
        userModel1.setName(FIRST_TEST_USER_NAME);
        userModel1.setSurname(FIRST_TEST_USER_SURNAME);
        userModel1.setEmail(FIRST_TEST_USER_EMAIL);
        userModel1.setPassword(TEST_USER_PASSWORD);
        userModel1.setRole(Collections.singleton(Role.USER));

        userModel2 = new UserModel();
        userModel2.setId(SECOND_TEST_ID);
        userModel2.setName(SECOND_TEST_USER_NAME);
        userModel2.setSurname(SECOND_TEST_USER_SURNAME);
        userModel2.setEmail(SECOND_TEST_USER_EMAIL);
        userModel2.setPassword(TEST_USER_PASSWORD);
        userModel2.setRole(Collections.singleton(Role.USER));
    }

    private void createTestedUserDtos() {
        userDto1 = new UserDto();
        userDto1.setId(FIRST_TEST_ID);
        userDto1.setName(FIRST_TEST_USER_NAME);
        userDto1.setSurname(FIRST_TEST_USER_SURNAME);
        userDto1.setEmail(FIRST_TEST_USER_EMAIL);
        userDto1.setPassword(TEST_USER_PASSWORD);
        userDto1.setRole(Collections.singleton(Role.USER));

        userDto2 = new UserDto();
        userDto2.setId(SECOND_TEST_ID);
        userDto2.setName(SECOND_TEST_USER_NAME);
        userDto2.setSurname(SECOND_TEST_USER_SURNAME);
        userDto2.setEmail(SECOND_TEST_USER_EMAIL);
        userDto2.setPassword(TEST_USER_PASSWORD);
        userDto2.setRole(Collections.singleton(Role.USER));
    }

    @Test
    public void singUpNewUser() {
        when(userRepository.findByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode(userDto1.getPassword())).thenReturn(userDto1.getPassword());
        when(userConverter.convertToModel(userDto1)).thenReturn(userModel1);

        tested.singUp(userDto1);

        verify(userRepository).save(any());
    }

    @Test
    public void throwExceptionWhenUserIsAlreadyExists() {
        when(userRepository.findByEmail(SECOND_TEST_USER_EMAIL)).thenReturn(Optional.of(userModel2));

        try {
            tested.singUp(userDto2);
        } catch (UserExistException ex) {
            String result = String.format(MESSAGE_USER_WITH_THIS_EMAIL_ALREADY_EXISTS, ex.getMessage());

            assertEquals(MESSAGE_USER_WITH_THIS_EMAIL_ALREADY_EXISTS, result);
        }
    }

    @Test
    public void findUserByEmail() {
        when(userRepository.findByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(Optional.of(userModel1));
        when(userConverter.convertToDto(userModel1)).thenReturn(userDto1);

        UserDto result = tested.findByEmail(FIRST_TEST_USER_EMAIL);

        assertEquals(userDto1.getEmail(), result.getEmail());
        assertEquals(userDto1, result);
    }

    @Test
    public void throwExceptionWhenFindUserWitchNotExist() {
        when(userRepository.findByEmail(NON_EXIST_USER_EMAIL)).thenReturn(Optional.empty());

        try {
            tested.findByEmail(NON_EXIST_USER_EMAIL);
        } catch (UsernameNotFoundException ex) {
            String result = ex.getMessage();

            assertEquals(MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION, result);
        }
    }

    @Test
    public void getUserModelByEmail() {
        when(userRepository.findByEmail(SECOND_TEST_USER_EMAIL)).thenReturn(Optional.ofNullable(userModel2));

        UserModel result = tested.getUserModelByEmail(SECOND_TEST_USER_EMAIL);

        assertEquals(userModel2.getEmail(), result.getEmail());
        assertEquals(userModel2, result);
    }

    @Test
    public void throwExceptionWhenFindUserModelWitchNotExist() {
        when(userRepository.findByEmail(NON_EXIST_USER_EMAIL)).thenReturn(Optional.empty());

        try {
            tested.getUserModelByEmail(NON_EXIST_USER_EMAIL);
        } catch (UsernameNotFoundException ex) {
            String result = ex.getMessage();

            assertEquals(MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION, result);
        }
    }

    @Test
    public void loadUserByEmail() {
        when(userRepository.findByEmail(FIRST_TEST_USER_EMAIL)).thenReturn(Optional.of(userModel1));

        UserDetails result = tested.loadUserByUsername(FIRST_TEST_USER_EMAIL);

        assertEquals(TEST_USER_PASSWORD, result.getPassword());
        assertEquals(FIRST_TEST_USER_EMAIL, result.getUsername());
    }

    @Test
    public void throwExceptionWhenLoadUserByEmailWitchNonExist() {
        when(userRepository.findByEmail(NON_EXIST_USER_EMAIL)).thenReturn(Optional.empty());

        try {
            tested.loadUserByUsername(NON_EXIST_USER_EMAIL);
        } catch (UsernameNotFoundException ex) {
            String result = ex.getMessage();

            assertEquals(MESSAGE_USER_WAS_NOT_FOUND_EXCEPTION, result);
        }
    }
}