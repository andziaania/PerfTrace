package com.persistence.test;

import com.Main;
import com.persistence.UserOnWebapp;
import com.persistence.UserOnWebappRepository;
import com.persistence.Webapp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

/**
 * @author ania.pawelczyk
 * @since 16.11.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={Main.class})
@DataJpaTest
public class UserOnWebappRepositoryIntegrationTest {

  @Autowired
  TestEntityManager entityManager;

  @Autowired
  UserOnWebappRepository userOnWebappRepository;

  @Test
  public void whenFindByWebapp_thenReturnUser() {
    Webapp webapp = new Webapp("http://test.url:1234");
    entityManager.persist(webapp);

    UserOnWebapp user1 = new UserOnWebapp(webapp);
    entityManager.persist(user1);
    UserOnWebapp user2 = new UserOnWebapp(webapp);
    entityManager.persist(user2);
    entityManager.flush();

    //when
    List<UserOnWebapp> found = userOnWebappRepository.findByWebapp(webapp);

    //then
    assertThat(found).size().isEqualTo(2);
    assertThat(found).contains(user1).contains(user2);
  }
}
