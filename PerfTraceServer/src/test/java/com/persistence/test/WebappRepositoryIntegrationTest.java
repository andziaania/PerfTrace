package com.persistence.test;

import java.util.List;
import com.persistence.Webapp;
import com.persistence.WebappRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;


/**
 * @author ania.pawelczyk
 * @since 21.11.2018.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class WebappRepositoryIntegrationTest {

  @Autowired
  TestEntityManager entityManager;

  @Autowired
  WebappRepository webappRepository;

  @Test
  public void whenFindByUrl_thenReturnWebapp() {
    Webapp webapp = new Webapp("http://testowe:123");
    webappRepository.save(webapp);

    //when
    Webapp webappFound = webappRepository.findByUrl("http://testowe:123");

    //then
    assertThat(webapp).isEqualTo(webappFound);
  }

}
