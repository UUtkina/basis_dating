package de.ait.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


        App app = context.getBean(App.class);
        app.start();

        //Scanner scanner = context.getBean(Scanner.class);
        //UserController controller = context.getBean(UserController.class);


        //UserRepository repository = new repositories.UserRepositoryListImpl();
        //UserRepository repository = new UserRepositoryFileImpl("users.txt");
        //UserService service = new UserServiceImpl(repository);
        //UserController controller = new UserControllerConsoleImp(service);



    }

}
