package de.ait.shop.servis.impl;

import de.ait.shop.models.User;
import de.ait.shop.repositories.UsersRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("UsersServiceImpl is works....")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class UsersServiceImplTest {

    private  static final String NOT_EXIST_EMAIL= "volodymyrU1312@gmail.com";
    private  static final String EXIST_EMAIL= "volodymyr1312@gmail.com";

    private static final String DEFAULT_PASSWORD = "qwerty007";
    private  static final User NOT_EXIST_USER= new User("volodymyrU1312@gmail.com", "qwenteti0606");
    private  static final User EXIST_USER= new User("volodymyr1312@gmail.com", "qwenteti006");
    private UsersServiceImpl usersService;
    private UsersRepository usersRepository;

    @BeforeEach
    public void setUp() {

        usersRepository = Mockito.mock(UsersRepository.class); // Use the class-level variable
        when(usersRepository.findOneByEmail(EXIST_EMAIL)).thenReturn(EXIST_USER);
        when(usersRepository.findOneByEmail(NOT_EXIST_EMAIL)).thenReturn(null);
        this.usersService = new UsersServiceImpl(usersRepository);
    }

    @Test
    public void add_user_on_incorrect_email_thows_exception() {
        //указіваем тип ожидаемой ошибки
        assertThrows(IllegalArgumentException.class, () -> usersService.addUser(null,DEFAULT_PASSWORD));
    }
    @Test
    public void add_user_on_incorrect_password_thows_exception() {
        assertThrows(IllegalArgumentException.class, () -> usersService.addUser(EXIST_EMAIL, DEFAULT_PASSWORD));
    }

    @Test
    public void add_user_returns_created_user() {
        User actual = usersService.addUser(NOT_EXIST_EMAIL, "qwenteti0606");
              Assertions.assertAll(
                ()->verify(usersRepository).save(NOT_EXIST_USER),
                ()->assertNotNull(actual));
    }

    @Test
    public void add_existed_returns_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> usersService.addUser(EXIST_EMAIL,DEFAULT_PASSWORD));
    }


}