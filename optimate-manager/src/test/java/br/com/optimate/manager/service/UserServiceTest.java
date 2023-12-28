package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.type.StatusType;
import br.com.optimate.manager.domain.user.PersonalInformation;
import br.com.optimate.manager.domain.user.User;
import br.com.optimate.manager.dto.UserDto;
import br.com.optimate.manager.dto.UserMapper;
import br.com.optimate.manager.repository.UserRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

@QuarkusTest
class UserServiceTest {

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @InjectMock
    UserRepository userRepository;
    @Inject
    JsonWebToken jwt;

    User user;
    List<User> users;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        List<String> roles = List.of("admin", "user");
        PersonalInformation personalInformation = new PersonalInformation("Fulano", "de Tal", "fulano", "password", "fulano@test.com", true );
        PersonalInformation personalInformation1 = new PersonalInformation("Fulano 1", "de Tal 1", "fulano1", "password1", "fulano1@test.com", true);
        this.user = new User(personalInformation, true, StatusType.ACTIVE, roles, null, null);
        User user1 = new User(personalInformation1, true, StatusType.ACTIVE, roles, null, null);
        this.users = List.of(user, user1);
        this.userDto = userMapper.toDto(user);
    }

    @Test
    void saveNonexistentUserTest() {
        Mockito.when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(userService.saveUser(userMapper.toDto(user))).thenAnswer(invocationOnMock -> {
            User user = (User) invocationOnMock.getArguments()[0];
            user.setId(1L);
            return user;
        });
        Assertions.assertEquals(1L, userService.saveUser(userMapper.toDto(user)).getId());
        Assertions.assertEquals(this.user.getPersonalInformation().getFirstName(), userService.saveUser(userMapper.toDto(user)).getPersonalInformation().getFirstName());
    }

    @Test
    void saveWithExistentUserTest() {
        Mockito.when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(user);
        Assertions.assertThrows(WebApplicationException.class, () -> userService.saveUser(userDto));
    }

    @Test
    void listAllTest() {
        Mockito.when(userRepository.listAll()).thenReturn(users);
        Assertions.assertEquals(2, userService.listAll().size());
    }

    @Test
    void findUserByUsernameTest() {
        Mockito.when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(user);
        Assertions.assertEquals("Fulano", userService.findUserByUsername(userMapper.toDto(user)).getPersonalInformation().getFirstName());
    }

    @Test
    void findUserByUsernameNotFoundUserTest() {
        Mockito.when(userRepository.findUserByUsername(Mockito.any())).thenReturn(null);
        Assertions.assertThrows(WebApplicationException.class, () -> userService.findUserByUsername(userDto));
    }

    @Test
    void editExistentUserTest() {
        userDto.setId(1L);
        user.setId(1L);
        Mockito.when(userRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(user));
        Assertions.assertEquals("Fulano", userService.editUser(userDto).getPersonalInformation().getFirstName());
        Mockito.verify(userRepository).persist(user);
    }

    @Test
    void editNonexistentUserTest() {
        Mockito.when(userRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.empty());
        userDto.setId(1L);
        Assertions.assertThrows(WebApplicationException.class, () -> userService.editUser(userDto));
    }

    @Test
    void deleteExistentUserTest() {
        user.setId(1L);
        Mockito.when(userRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(user));
        Assertions.assertEquals( "Usuário excluído com sucesso!", userService.deleteUser(user.getId()));
        Mockito.verify(userRepository).delete(user);
    }
    @Test
    void deleteNonExistentUserTest() {
        user.setId(1L);
        Long id = user.getId();;
        Mockito.when(userRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(WebApplicationException.class, () -> userService.deleteUser(id));
    }

    @Test
    @TestSecurity(user = "fulano", roles = "admin")
    @JwtSecurity(claims = {
            @Claim(key = "email", value = "user@gmail.com")})
    void getCurrentUserTest() {
        Mockito.when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(user);
        Assertions.assertEquals(jwt.getName(), userService.getCurrentUser().getPersonalInformation().getUsername());
    }

    @Test
    void changePassword() {
    }

    @Test
    void uploadAvatar() {
    }

}