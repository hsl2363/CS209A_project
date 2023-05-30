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
@RequestMapping("/api/numberofanswer")
public class NARestController {

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

  private class AQ {
    private int answered_question;
    private int unanswered_question;

    public AQ(int a, int tot) {
      this.answered_question = a;
      this.unanswered_question = tot - a;
    }
  }

  @GetMapping("/answered_question")
  public AQ Answered_Question() {
    int[] info = service.getAnsweredQuestionCnt();
    AQ res = new AQ(info[0], info[1]);
    // System.out.println(res.answered_question);
    return res;
  }

  private class Distribution {

    private double avg;
    private int max;
    private Map<Integer, Integer> num;

    public Distribution(List<Integer> info) {
      num = new HashMap<>();
      this.avg = 0;
      this.max = 0;
      for (int i = 0; i < info.size(); i++) {
        int cnt = info.get(i);
        if (cnt > this.max)
          this.max = cnt;
        this.avg += cnt;
        if (num.containsKey(cnt))
          num.put(cnt, num.get(cnt) + 1);
        else
          num.put(cnt, 1);
      }
      this.avg /= info.size();
    }
  }

  @GetMapping("/distribution")
  public Distribution distribution() {
    List<Integer> info = service.getAnswerDistrubution();
    Distribution res = new Distribution(info);
    return res;
  }

}
