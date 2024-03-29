package cse.java2.project.controller;

import cse.java2.project.service.WebService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagRestController {

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

  @GetMapping("/cnt")
  public Map<String, Integer> Tags() {
    Map<String, Integer> info = service.getTags();
    // System.out.println(res.answered_question);
    return info;
  }

  @GetMapping("/combupvote")
  public Map<String, Integer> combupvote() {
    Map<String, Integer> info = service.getCombUpvote();
    // System.out.println(res.answered_question);
    return info;
  }

  @GetMapping("/combview")
  public Map<String, Integer> combview() {
    Map<String, Integer> info = service.getCombView();
    // System.out.println(res.answered_question);
    return info;
  }

}
