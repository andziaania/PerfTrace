package com.persistence.test;

import com.persistence.Webapp;
import com.persistence.WebappRegister;
import com.persistence.WebappRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ania.pawelczyk
 * @since 21.11.2018.
 */
@RunWith(SpringRunner.class)
public class WebappRegisterTest {

  @TestConfiguration
  static class WebappRegisterImplTestContextConfiguration {
    @Bean
    public WebappRegister webappRegister() {
      return new WebappRegister();
    }
  }

  @Autowired
  WebappRegister webappRegister;

  @MockBean
  WebappRepository webappRepositoryMock;


  @Test(expected = Exception.class)
  public void whenSavingWebappThatExists_throwsEx() throws Exception {
    String url = "http://testowe:123";
    Webapp webapp = new Webapp(url);
    Mockito.when(webappRepositoryMock.findByUrl(url))
            .thenReturn(webapp);

    webappRegister.saveUnique(url);
  }
}
