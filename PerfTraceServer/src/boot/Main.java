package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ania.pawelczyk
 * @since 06.11.2018.
 */
@EnableAutoConfiguration
@ComponentScan
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}