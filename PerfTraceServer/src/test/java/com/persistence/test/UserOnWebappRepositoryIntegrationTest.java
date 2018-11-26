package com.persistence.test;

import com.persistence.UserOnWebapp;
import com.persistence.UserOnWebappRepository;
import com.persistence.Webapp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ania.pawelczyk
 * @since 16.11.2018.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserOnWebappRepositoryIntegrationTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserOnWebappRepository userOnWebappRepository;

  //TODO remove if findByWebapp not needed
  @Test
  public void findByWebapp_returnUsers() {
    Webapp webapp = new Webapp("http://test.url:1234");
    entityManager.persist(webapp);

    UserOnWebapp user1 = new UserOnWebapp(webapp, "ID1", "http://test.url:1234/index.html");
    entityManager.persist(user1);
    UserOnWebapp user2 = new UserOnWebapp(webapp, "ID2", "http://test.url:1234/hello");
    entityManager.persist(user2);
    entityManager.flush();

    //when
    List<UserOnWebapp> users = userOnWebappRepository.findByWebapp(webapp);

    //then
    assertThat(users).size().isEqualTo(2);
    assertThat(users).contains(user1).contains(user2);
  }

  @Test
  public void findByWebappAndTimestampGreaterThan_whenFreshUsers_returnTheFreshUsers() throws InterruptedException {
    Webapp webapp = new Webapp("http://test.url:1234");
    entityManager.persist(webapp);

    UserOnWebapp user1 = new UserOnWebapp(webapp, "sessionId1", "url1");
    UserOnWebapp user2 = new UserOnWebapp(webapp, "sessionid2", "url2");
    entityManager.persist(user1);
    entityManager.persist(user2);

    //when
    List<UserOnWebapp> found = userOnWebappRepository
            .findByWebappAndTimestampGreaterThan(webapp, LocalDateTime.now().minusHours(3));

    //then
    assertThat(found.size()).isEqualTo(2);
    assertThat(found)
            .contains(user1)
            .contains(user2);
  }

  @Test
  public void findByWebappAndTimestampGreaterThan_whenObsoleteUsers_returnNoUsers() throws InterruptedException {
    Webapp webapp = new Webapp("http://test.url:1234");
    entityManager.persist(webapp);

    UserOnWebapp user1 = new UserOnWebapp(webapp, "sessionId1", "url1");
    UserOnWebapp user2 = new UserOnWebapp(webapp, "sessionid2", "url2");
    entityManager.persist(user1);
    entityManager.persist(user2);
    entityManager.flush();

    //when: looking for entities from the future - shouldn't be any
    List<UserOnWebapp> found = userOnWebappRepository
            .findByWebappAndTimestampGreaterThan(webapp, LocalDateTime.now().plusSeconds(1));

    //then
    assertThat(found.size()).isEqualTo(0);
  }

  @Test
  public void findByWebappAndTimestampGreaterThan_whenUpdatedUser1AndObsoleteUser2_returnUpdatedUser()
          throws InterruptedException {
    Webapp webapp = new Webapp("http://test.url:1234");
    entityManager.persist(webapp);

    UserOnWebapp user1 = new UserOnWebapp(webapp, "sessionId1", "url1");
    UserOnWebapp user2 = new UserOnWebapp(webapp, "sessionid2", "url2");
    entityManager.persist(user1);
    entityManager.persist(user2);
    entityManager.flush();
    //We want to make sure, that persistence of user2 is older than 0.5 sec before user1 update.
    Thread.sleep(500);
    //Updating user2 timestamp in DB
    user1.setClickNumber(20);
    entityManager.persist(user1);
    entityManager.flush();

    //when: selecting users persisted from 0.1sec before user1 persisted.
    List<UserOnWebapp> found = userOnWebappRepository
            .findByWebappAndTimestampGreaterThan(webapp, user1.getTimestamp().minusNanos(100000));
    //then
    assertThat(found.size()).isEqualTo(1);
    assertThat(found).contains(user1);
  }
}
