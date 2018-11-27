package com.persistence;

import com.boot.InvalidWebappStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author ania.pawelczyk
 * @since 16.11.2018.
 */
@Component
public class UserOnWebappRegister {

  private static int PRESENT_TIME_INTERVAL_MINUTES = 5;

  @Autowired
  private WebappRepository webappRepository;

  @Autowired
  private UserOnWebappRepository userOnWebappRepository;

  // TODO userId != sessionId
  public void registerVisit(int webappId, String userSessionId, String visitUrl) throws InvalidWebappStateException {
    Optional<UserOnWebapp> userO = userOnWebappRepository.findBySessionId(userSessionId);
    UserOnWebapp user;
    if (userO.isPresent()) {
      user = userO.get();
      user.setClickCount(user.getClickCount() + 1);
      user.setCurrentUrl(visitUrl);
    } else {
      Optional<Webapp> webapp = webappRepository.findById((long) webappId);
      if (!webapp.isPresent()) {
        throw new InvalidWebappStateException("No webapp of id = " + webappId);
      }
      //Disclaimer: could be from factory
      user = new UserOnWebapp(webapp.get(), userSessionId, visitUrl);
    }
    userOnWebappRepository.save(user);
  }

  public int getCurrentWebappUsersNumber(long webappId) throws InvalidWebappStateException {
    Optional<Webapp> webapp = webappRepository.findById(webappId);
    if (!webapp.isPresent()) {
      throw new InvalidWebappStateException("No webapp of id = " + webappId);
    }
    return userOnWebappRepository.findByWebappAndTimestampGreaterThan(
                webapp.get(), LocalDateTime.now().minusMinutes(PRESENT_TIME_INTERVAL_MINUTES))
            .size();
  }


//  Webapp webapp = webappRepository.findByUrl(originUrl);
//    if (webapps.size() != 1) {
//      webappRepository.save(new Webapp(originUrl));
//      webapps = webappRepository.findByUrl(originUrl);
////      throw new InvalidStateException(
////              String.format("Webapp of url %s should be exacly one, but found %d", originUrl, webapps.size()));
//    }
//
//  UserOnWebapp user;
//  //    Webapp webapp = webapps.get(0);
//  List<UserOnWebapp> users = userOnWebappRepository.findByWebapp(webapp);
//    switch (users.size()) {
//    case 0 : { user = new UserOnWebapp(webapp); break; }
//    case 1 : { user = users.get(0); break; }
//    default: throw new InvalidStateException("Multiple users not supported yet");
//  }
//    user.setClickCount(user.getClickCount() + 1);
//    userOnWebappRepository.save(user);

}
