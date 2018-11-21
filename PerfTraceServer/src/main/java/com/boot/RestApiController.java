package com.boot;

import com.persistence.UserOnWebappRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @author ania.pawelczyk
 * @since 06.11.2018.
 */

@RestController
public class RestApiController {

  @Autowired
  MetricsRegistry metricsRegistry;

  @Autowired
  UserOnWebappRegister userOnWebappRegister;

  @RequestMapping("/api/hi")
  public String hi() {
    return "Hello world!";
  }

  @RequestMapping("/api/cookie")
  public String getCookie(HttpServletResponse response) {
    response.addCookie(new Cookie("id", new Integer(new Random().nextInt()).toString()));
    return "my-page";
  }

//  @RequestMapping("/bz")
//  public String bz(HttpServletResponse response) {
//    response.setHeader("Access-Control-Allow-Origin", "*");
//    return "bzzz";
//  }

  @RequestMapping("/currentUsersNumber")
  int getCurrentUsersNumber() {
    return 144;//(Integer) metricsRegistry.get(MetricsEnum.ACTIVE_USERS_COUNT);
  }

  @RequestMapping("/initialLoad")
  public void initialLoad(HttpServletRequest request) {
    userOnWebappRegister.registerVisit(request.getHeader("Origin"));
  }
}