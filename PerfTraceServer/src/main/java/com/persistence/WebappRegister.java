package com.persistence;

import com.boot.InvalidWebappStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * @author ania.pawelczyk
 * @since 21.11.2018.
 */
@Component
public class WebappRegister {

  @Autowired
  WebappRepository webappRepository;

  public void saveUnique(String url) throws InvalidWebappStateException {
    Webapp webapp = new Webapp(url);
    try {
      webappRepository.save(webapp);

    } catch (PersistenceException | DataIntegrityViolationException ex) {
      Webapp webappUnique = webappRepository.findByUrl(url);
      if (webappUnique != null) {
        throw new InvalidWebappStateException("Webapp already exists.");
      } else {
        throw new InvalidWebappStateException("Contact with the administrator. Cannot add your webapp " + url);
      }
    }
  }

  public Iterable<Webapp> getWebappList() {
    return webappRepository.findAll();
  }

  public void addWebapps(List webapps) {
    webappRepository.saveAll(webapps);
  }
}
