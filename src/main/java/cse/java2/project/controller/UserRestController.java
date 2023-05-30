package cse.java2.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse.java2.project.service.WebService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

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

  @GetMapping("/threads")
  public int Thread() {
    int info = service.getThreadUsers();
    // System.out.println(res.answered_question);
    return info;
  }

  private class Users {
    private int comment_users;
    private int answer_users;

    public Users(int a, int c) {
      this.comment_users = c;
      this.answer_users = a;
    }
  }

  @GetMapping("/answercomment")
  public Users AnswerComment() {
    Users info = new Users(service.getAnswerUsers(), service.getCommentUsers());
    // System.out.println(res.answered_question);
    return info;
  }

  @GetMapping("/activity")
  public Map<String, Integer> Activity() {
    Map<String, Integer> info = service.getactivity();
    // System.out.println(res.answered_question);
    return info;
  }

}
