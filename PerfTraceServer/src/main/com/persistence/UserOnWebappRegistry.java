package com.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;

/**
 * @author ania.pawelczyk
 * @since 16.11.2018.
 */
@Component
public class UserOnWebappRegistry {

  @Autowired
  private WebappRepository webappRepository;

  @Autowired
  private UserOnWebappRepository userOnWebappRepository;

  public void registerVisit(String originUrl) {
    List<Webapp> webapps = webappRepository.findByUrl(originUrl);
    if (webapps.size() != 1) {
      webappRepository.save(new Webapp(originUrl));
      webapps = webappRepository.findByUrl(originUrl);
//      throw new InvalidStateException(
//              String.format("Webapp of url %s should be exacly one, but found %d", originUrl, webapps.size()));
    }

    UserOnWebapp user;
    Webapp webapp = webapps.get(0);
    List<UserOnWebapp> users = userOnWebappRepository.findByWebapp(webapp);
    switch (users.size()) {
      case 0 : { user = new UserOnWebapp(webapp); break; }
      case 1 : { user = users.get(0); break; }
      default: throw new InvalidStateException("Multiple users not supported yet");
    }
    user.setClickNumber(user.getClickNumber() + 1);
    userOnWebappRepository.save(user);
  }

}
