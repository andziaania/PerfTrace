package boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ania.pawelczyk
 * @since 06.11.2018.
 */
@Controller
public class PageController {

  @Autowired
  MetricsRegistry metricsRegistry;

  @RequestMapping("/")
  @ResponseBody
  public String getMainPage(HttpServletRequest request) {
    return "Hello World:0)!" + metricsRegistry.get(MetricsEnum.ACTIVE_USERS_COUNT)  + "  " + request.getSession().getId();
  }
}
