package cse.java2.project.controller;

import cse.java2.project.service.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acceptedanswer")
public class AAController {

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
    public int solved_question;
    public int unsolved_question;
    public double percentage;

    public AQ(int a, int tot) {
      this.solved_question = a;
      this.unsolved_question = tot - a;
      this.percentage = (double) a / tot;
    }
  }

  @RequestMapping("/solved_question")
  public String Solved_Question(Model model) {
    int[] info = service.getSolvedQuestionCnt();
    AQ res = new AQ(info[0], info[1]);
    // System.out.println(res.answered_question);
    model.addAttribute("res", res);
    return "solved_question";
  }

  private class NACUQ {
    public int nacu_question;
    public int acu_question;
    public double percentage;

    public NACUQ(int a, int tot) {
      this.nacu_question = a;
      this.acu_question = tot - a;
      this.percentage = (double) a / tot;
    }
  }

  @RequestMapping("/non-acceptedupvote_question")
  public String NACU_Question(Model model) {
    int[] info = service.getNotACMostUpvoteQuestionCnt();
    NACUQ res = new NACUQ(info[0], info[1]);
    model.addAttribute("res", res);
    return "non-acceptedupvote_question";
  }

  private class Distribution {
    public Map<String, Integer> num;

    public Distribution(List<Integer> info) {
      num = new HashMap<>();
      num.put("15min", 0);
      num.put("30min", 0);
      num.put("1hour", 0);
      num.put("5hour", 0);
      num.put("12hour", 0);
      num.put("24hour", 0);
      num.put("3days", 0);
      num.put("1weeks", 0);
      num.put("1month", 0);
      num.put("6month", 0);
      num.put("1year", 0);
      num.put("more", 0);
      for (int i = 0; i < info.size(); i++) {
        int tim = info.get(i);
        if (tim <= 15 * 60)
          num.put("15min", num.get("15min") + 1);
        else if (tim <= 30 * 60)
          num.put("30min", num.get("30min") + 1);
        else if (tim <= 60 * 60)
          num.put("1hour", num.get("1hour") + 1);
        else if (tim <= 5 * 60 * 60)
          num.put("5hour", num.get("5hour") + 1);
        else if (tim <= 12 * 60 * 60)
          num.put("12hour", num.get("12hour") + 1);
        else if (tim <= 24 * 60 * 60)
          num.put("24hour", num.get("24hour") + 1);
        else if (tim <= 3 * 24 * 60 * 60)
          num.put("3days", num.get("3days") + 1);
        else if (tim <= 7 * 24 * 60 * 60)
          num.put("1weeks", num.get("1weeks") + 1);
        else if (tim <= 30 * 24 * 60 * 60)
          num.put("1month", num.get("1month") + 1);
        else if (tim <= 6 * 30 * 24 * 60 * 60)
          num.put("6month", num.get("6month") + 1);
        else if (tim <= 12 * 30 * 24 * 60 * 60)
          num.put("1year", num.get("1year") + 1);
        else
          num.put("more", num.get("more") + 1);
      }
    }
  }

  @RequestMapping("/time_distribution")
  public String distribution(Model model) {
    List<Integer> info = service.gettimeDistribution();
    Distribution res = new Distribution(info);
    List<Integer> cnt = new ArrayList<>();
    cnt.add(res.num.get("15min"));
    cnt.add(res.num.get("30min"));
    cnt.add(res.num.get("1hour"));
    cnt.add(res.num.get("5hour"));
    cnt.add(res.num.get("12hour"));
    cnt.add(res.num.get("24hour"));
    cnt.add(res.num.get("3days"));
    cnt.add(res.num.get("1weeks"));
    cnt.add(res.num.get("1month"));
    cnt.add(res.num.get("6month"));
    cnt.add(res.num.get("1year"));
    cnt.add(res.num.get("more"));
    model.addAttribute("res", cnt);
    return "time_distribution";
  }

}
