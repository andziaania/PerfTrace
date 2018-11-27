package com.boot;

import com.persistence.UserOnWebappRegister;
import com.persistence.Webapp;
import com.persistence.WebappRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ania.pawelczyk
 * @since 06.11.2018.
 */

@RestController
public class RestApiController {

  @Autowired
  private UserOnWebappRegister userOnWebappRegister;

  @Autowired
  private WebappRegister webappRegister;

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

  @RequestMapping("/initialLoad")
  public void initialLoad(HttpServletRequest request) {
//    userOnWebappRegister.registerVisit(webappId, request.getHeader("Origin"));
  }

  //jaki kod? 302?
  @RequestMapping("Location")
  public void redirect(HttpServletResponse res) {
    res.setHeader("Location", "perftrace.0.1.js");
    res.setStatus(302);
  }

  @RequestMapping("addWebapps")
  public void addWebapps() {
    List webapps = new ArrayList<Webapp>(){{
      add(new Webapp("pierwsza"));
      add(new Webapp("a.b.2"));
      add(new Webapp("d.wew.3"));
    }};
    webappRegister.addWebapps(webapps);
  }



  @RequestMapping("addWebapp")
  public void addWebapp(@RequestParam("url") String url) throws InvalidWebappStateException {
    webappRegister.saveUnique(url);
  }


  @RequestMapping("/getWebappList")
  public List<Long> getWebappList() {
    final List<Long> webappList = new ArrayList();
    webappRegister.getWebappList().forEach(webapp -> webappList.add(webapp.getId()));
    return webappList;
  }



  // Using GET as it's faster
  @RequestMapping(value = "/registerAjaxRequest")
  public void registerAjaxRequest(
          @RequestParam("webappId") int webappId,
          @RequestParam("ajaxRequestUrl") String ajaxRequestUrl,
          HttpServletRequest request,HttpServletResponse response) throws InvalidWebappStateException {

    userOnWebappRegister.registerVisit(webappId, request.getSession().getId(), ajaxRequestUrl);
    response.getHeader("Cookies");

  }

  @RequestMapping("/currentUsersNumber")
  int getCurrentUsersNumber(@RequestParam("webappId") long webappId) throws InvalidWebappStateException {
    return userOnWebappRegister.getCurrentWebappUsersNumber(webappId);
  }
}
