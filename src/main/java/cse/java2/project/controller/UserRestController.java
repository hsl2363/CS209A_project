package cse.java2.project.controller;

import cse.java2.project.service.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  private class Distribution {
    public Map<Integer, Integer> num;

    public Distribution(List<Integer> info) {
      num = new HashMap<>();
      for (int i = 0; i < info.size(); i++) {
        int cnt = info.get(i);
        if (num.containsKey(cnt)) {
          num.put(cnt, num.get(cnt) + 1);
        } else {
          num.put(cnt, 1);
        }
      }
    }
  }

  @GetMapping("/threads")
  public Distribution Thread() {
    List<Integer> info = service.getThreadUsers();
    Distribution res = new Distribution(info);
    // System.out.println(res.answered_question);
    return res;
  }

  private class Distribution2 {
    public Map<Integer, Integer> answerUsers;
    public Map<Integer, Integer> commentUsers;

    public Distribution2(List<Integer> A, List<Integer> C) {
      answerUsers = new HashMap<>();
      commentUsers = new HashMap<>();
      for (int i = 0; i < A.size(); i++) {
        int cnt = A.get(i);
        if (answerUsers.containsKey(cnt)) {
          answerUsers.put(cnt, answerUsers.get(cnt) + 1);
        } else {
          answerUsers.put(cnt, 1);
        }
      }
      for (int i = 0; i < C.size(); i++) {
        int cnt = C.get(i);
        if (commentUsers.containsKey(cnt)) {
          commentUsers.put(cnt, commentUsers.get(cnt) + 1);
        } else {
          commentUsers.put(cnt, 1);
        }
      }
    }
  }

  @GetMapping("/answercomment")
  public Distribution2 AnswerComment() {
    List<Integer> answerusers = service.getAnswerUsers();
    List<Integer> commentusers = service.getCommentUsers();
    Distribution2 res = new Distribution2(answerusers, commentusers);
    return res;
  }

  @GetMapping("/activity")
  public Map<String, Integer> Activity() {
    Map<String, Integer> info = service.getActivity();
    Map<String, Integer> filtered = new HashMap<>();
    info.forEach((key, value) -> {
      if (value > 1) {
        filtered.put(key, value);
      }
    });
    return filtered;
  }

}
