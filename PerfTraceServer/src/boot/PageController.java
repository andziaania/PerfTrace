package boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ania.pawelczyk
 * @since 06.11.2018.
 */
@Controller
public class PageController {
  @RequestMapping("/")
  @ResponseBody
  public String getMainPage() {
    return "Hello World:0)!";
  }
}
