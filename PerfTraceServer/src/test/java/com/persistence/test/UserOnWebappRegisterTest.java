package com.persistence.test;

import com.boot.InvalidWebappStateException;
import com.persistence.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author ania.pawelczyk
 * @since 26.11.2018.
 */
@RunWith(SpringRunner.class)
public class UserOnWebappRegisterTest {

  @TestConfiguration
  static class UserOnWebappRegisterImplTestContextConfiguration {
    @Bean
    public UserOnWebappRegister userOnWebappRegister() { return new UserOnWebappRegister(); }
  }

  @Autowired
  UserOnWebappRegister userOnWebappRegister;

  //REMEMBER: Use @org.springframework.boot.test.mock.mockito.MockBean, no @org.mockito.Mock
  @MockBean
  UserOnWebappRepository userOnWebappRepositoryMock;

  @MockBean
  WebappRepository webappRepositoryMock;

  @Test
  public void registerVisit_whenNewUser_thenRegister() throws InvalidWebappStateException {
    Mockito.when(userOnWebappRepositoryMock.findBySessionId(Mockito.anyString())).thenReturn(Optional.empty());
    Mockito.when(webappRepositoryMock.findById(Mockito.anyLong()))
            .thenReturn(Optional.of(Mockito.mock(Webapp.class)));

    userOnWebappRegister.registerVisit(123, "SessionId", "Url");

    verify(userOnWebappRepositoryMock, times(1)).save(Mockito.any(UserOnWebapp.class));
  }

  @Test(expected = Exception.class)
  public void registerVisit_whenNewUserAndNoWebappInRepo_throwEx() throws InvalidWebappStateException {
    Mockito.when(userOnWebappRepositoryMock.findBySessionId(Mockito.anyString())).thenReturn(Optional.empty());
    Mockito.when(webappRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    userOnWebappRegister.registerVisit(123, "SessionId", "Url");
  }

  @Test
  public void registerVisit_whenUserExists_update() throws InvalidWebappStateException {
    UserOnWebapp userOnWebapp = new UserOnWebapp(Mockito.mock(Webapp.class), "JSessionId", "url/test:12");

    Mockito.when(userOnWebappRepositoryMock.findBySessionId(Mockito.anyString()))
            .thenReturn(Optional.of(userOnWebapp));

    userOnWebappRegister.registerVisit(1234, userOnWebapp.getSessionId(), "newUrl");

    Mockito.verify(userOnWebappRepositoryMock, times(1)).save(userOnWebapp);
  }

  @Test
  public void registerVisit_whenUserExists_userDataIsChanged() throws InvalidWebappStateException {
    UserOnWebapp userOnWebapp = new UserOnWebapp(Mockito.mock(Webapp.class), "JSessionId", "url/test:12");

    Mockito.when(userOnWebappRepositoryMock.findBySessionId(Mockito.anyString()))
            .thenReturn(Optional.of(userOnWebapp));

    String newUrl = "newUrl";
    userOnWebappRegister.registerVisit(1234, userOnWebapp.getSessionId(), newUrl);

//    userOnWebapp.setCurrentUrl(newUrl);
    Mockito.verify(userOnWebappRepositoryMock, times(1)).save(userOnWebapp);
  }
}
