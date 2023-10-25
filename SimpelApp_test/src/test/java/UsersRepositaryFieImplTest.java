import de.ait.shop.models.User;
import de.ait.shop.repositories.impl.UsersRepositaryFieImpl;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UsersRepositaryFieImpl is works....")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class UsersRepositaryFieImplTest {
    private static final String TEMP_USERS_FILE_NAME = "Users_test.txt";
    private UsersRepositaryFieImpl usersRepositary;

    @BeforeEach
    public void setUp() throws Exception {
        File file = new File(TEMP_USERS_FILE_NAME);
        createNewFileForTest(TEMP_USERS_FILE_NAME);
        usersRepositary = new UsersRepositaryFieImpl(TEMP_USERS_FILE_NAME);

    }

    @AfterEach
    public void tearDown() throws Exception {
        deleteAfterTest(TEMP_USERS_FILE_NAME);
    }

    @DisplayName("save():")
    @Nested
    class Save {
        @Test
        public void users_writes_correct_line_to_file() throws IOException {
            User user = new User("utkina.ga@gmail.com", "123654;");
            usersRepositary.save(user);

            String expected = "1#utkina.ga@gmail.com#123654;";
            BufferedReader reader = new BufferedReader(new FileReader(TEMP_USERS_FILE_NAME));
            String actual = reader.readLine();
            reader.close();
            assertEquals(expected, actual);
        }
    }

    @DisplayName("findAll():")
    @Nested
    class FindAll {
        @Test
        public void returns_correct_list_of_user() throws Exception {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(TEMP_USERS_FILE_NAME));//открыли файл для записи
            //записали строки для тестированния метода
            bufferedWriter.write("1#utkina.ga@gmail.com#123654;");
            bufferedWriter.newLine();
            bufferedWriter.write("2#sidakovMa@gmail.com#hhjjkkugfdt112");
            bufferedWriter.newLine();
            bufferedWriter.close();
            //формируем ожидаемый список пользователей
            List<User> expected = Arrays.asList(
                    new User(1L,"utkina.ga@gmail.com", "123654;"),
                    new User(2L,"sidakovMa@gmail.com", "hhjjkkugfdt112")
            );
            List<User> actual = usersRepositary.findAll();//получаем фактический список пользователей
            assertEquals(expected, actual);
        }

    }


    private static void createNewFileForTest(String fileName) throws IOException {
        File file = new File(fileName);
        deleteIfExists(file);
        boolean result = file.createNewFile();
        if (!result) {
            throw new IllegalStateException("Problems with file creat");
        }
    }

    private static void deleteIfExists(File file) {
        if (file.exists()) {
            boolean resalt = file.delete();
            if (!resalt) {
                throw new IllegalStateException("Problems with file delite");
            }
        }
    }

    private void deleteAfterTest(String fileName) {
        File file = new File(fileName);
        deleteIfExists(file);

    }
}