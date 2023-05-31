package cse.java2.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cse.java2.project.service.WebService;

@Controller
@RequestMapping("/numberofanswer")
public class NAController {

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
    public int answered_question;
    public int unanswered_question;
    public double percentage;

    public AQ(int a, int tot) {
      this.answered_question = a;
      this.unanswered_question = tot - a;
      this.percentage = (double) a / tot;
    }
  }

  @RequestMapping("/answered_question")
  public String Answered_Question(Model model) {
    int[] info = service.getAnsweredQuestionCnt();
    AQ res = new AQ(info[0], info[1]);
    model.addAttribute("res", res);
    return "answered_question";
  }

  private class Distribution {

    public double avg;
    public int max;
    public Map<Integer, Integer> num;

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

  @RequestMapping("/answer_distribution")
  public String distribution(Model model) {
    List<Integer> info = service.getAnswerDistrubution();
    Distribution res = new Distribution(info);
    model.addAttribute("res", res);
    return "Answer_distribution";
  }

}
