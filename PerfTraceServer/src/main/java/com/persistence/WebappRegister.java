package com.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ania.pawelczyk
 * @since 21.11.2018.
 */
@Component
public class WebappRegister {

  @Autowired
  WebappRepository webappRepository;

  public void saveUnique(String url) throws Exception {
    Webapp webapp = webappRepository.findByUrl(url);
    if (webapp != null) {
      throw new Exception("Webapp already exists.");
    }
    webappRepository.save(webapp);
  }

  public Iterable<Webapp> getWebappList() {
    return webappRepository.findAll();
  }

  public void addWebapps(List webapps) {
    webappRepository.saveAll(webapps);
  }
}
