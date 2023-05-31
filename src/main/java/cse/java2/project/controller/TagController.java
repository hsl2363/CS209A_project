package cse.java2.project.controller;

import cse.java2.project.service.WebService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
public class TagController {

  /**
   * This method is called when the user requests the root URL ("/") or "/demo".
   * In this demo, you can visit localhost:9090 or localhost:9090/demo to see the
   * result.
   * 
   * @return the name of the view to be rendered
   *         You can find the static HTML file in
   *         src/main/resources/templates/demo.html
   */

  @Autowired
  private WebService service;

  @RequestMapping("/cnt")
  public String Tags(Model model) {
    Map<String, Integer> info = service.getTags();
    // System.out.println(res.answered_question);
    model.addAttribute("res", info);
    info.put("java", 0);
    return "tagcnt";
  }

  @RequestMapping("/combupvote")
  public String combupvote(Model model) {
    Map<String, Integer> info = service.getCombUpvote();
    // System.out.println(res.answered_question);
    model.addAttribute("res", info);
    return "combupvote";
  }

  @RequestMapping("/combview")
  public String combview(Model model) {
    Map<String, Integer> info = service.getCombView();
    // System.out.println(res.answered_question);
    model.addAttribute("res", info);
    return "combview";
  }

}
