package hiber;

import hiber.model.Car;
import hiber.model.User;
import hiber.config.AppConfig;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.util.List;

public class MainApp {
   public static void main(String[] args){
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("Lada", 1111);
      Car car2 = new Car("Uaz", 2222);
      Car car3 = new Car("Liaz", 3333);
      Car car4 = new Car("Paz", 4444);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println(user.getCar());
      }

      System.out.println(userService.getUserByCar("Lada", 1111));

      try {
         User user = userService.getUserByCar("Lada", 2222);
      } catch (NoResultException e) {
         System.out.println( "Пользователей с такими машинами не обнаружено!");
      }

      context.close();
   }
}
