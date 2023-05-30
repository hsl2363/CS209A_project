package cse.java2.project.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cse.java2.project.model.*;

@Repository
public class DAO {

  private Connection con = null;
  private ResultSet resultSet;

  private String host = "localhost";
  private String dbname = "project_java";
  private String user = "checker";
  private String pwd = "123456";
  private String port = "5432";

  public void openDatasource() {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
      System.err.println("Cannot find the PostgreSQL driver. Check CLASSPATH.");
      System.exit(1);
    }
    try {
      String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
      con = DriverManager.getConnection(url, user, pwd);

    } catch (SQLException e) {
      System.err.println("Database connection failed");
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  public Connection getCon() {
    return this.con;
  }

  public void closeDatasource() {
    if (con != null) {
      try {
        con.close();
        con = null;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void addQuestion(String[] Info) {
    // openDatasource();
    String sql = "insert into Questions (accepted_answer_id, answer_count,comment_count,creation_date,down_vote_count,favorite_count,is_answered,owner_id,question_id,title,up_vote_count,view_count) "
        + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
      int cnt = 0, cnt_1 = 1;
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setBoolean(cnt_1++, (Info[cnt++] == "true"));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setString(cnt_1++, Info[cnt++]);
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      // System.out.println(preparedStatement.toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      return;
    }
  }

  public void addAnswer(String[] Info) {
    // openDatasource();
    String sql = "insert into Answers (answer_id,comment_count,creation_date,down_vote_count,is_accepted,owner_id,question_id,up_vote_count)"
        + " values (?,?,?,?,?,?,?,?)";
    try {
      int cnt = 0, cnt_1 = 1;
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setBoolean(cnt_1++, (Info[cnt++] == "true"));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      // System.out.println(preparedStatement.toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      return;
    }
  }

  public void addComment(String[] Info) {
    // openDatasource();
    String sql = "insert into Comments (comment_id,creation_date,owner_id,post_id,reply_to_user)"
        + " values (?,?,?,?,?)";
    try {
      int cnt = 0, cnt_1 = 1;
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setBoolean(cnt_1++, (Info[cnt++] == "true"));
      // System.out.println(preparedStatement.toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      return;
    }
  }

  public void addUser(String[] Info) {
    // openDatasource();
    String sql = "insert into Users (accepted_rate,account_id,display_name,reputation,user_id)"
        + " values (?,?,?,?,?)";
    try {
      int cnt = 0, cnt_1 = 1;
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setString(cnt_1++, Info[cnt++]);
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      preparedStatement.setInt(cnt_1++, Integer.parseInt(Info[cnt++]));
      // System.out.println(preparedStatement.toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      return;
    }
  }

  public void addTag(String str) {
    // openDatasource();
    String sql = "insert into Tags (content)"
        + " values (?)";
    // String[] Info = str.split(";");
    try {
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setString(1, str);
      // System.out.println(preparedStatement.toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      return;
    }
  }

  public void addtag(int question_id, int tagid) {
    // openDatasource();
    String sql = "insert into tag (question_id,tagid)"
        + " values (?,?)";
    // String[] Info = str.split(";");
    try {
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setInt(1, question_id);
      preparedStatement.setInt(2, tagid);
      // System.out.println(preparedStatement.toString());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      return;
    }
  }

  public int[] getAnsweredQuestionCnt() {
    String sql = "select sum(case answer_count when 0 then 1 else  0 end) cnt,count(*) tot from questions";
    int[] result = new int[2];
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        result[0] = resultSet.getInt("cnt");
        result[1] = resultSet.getInt("tot");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int[] getSolvedQuestionCnt() {
    String sql = "select sum(case accepted_answer_id when 0 then 0 else 1 end) cnt,count(*) tot from questions";
    int[] result = new int[2];
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        result[0] = resultSet.getInt("cnt");
        result[1] = resultSet.getInt("tot");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public int[] getNotACMostUpvoteQuestionCnt() {
    String sql = "select sum(case Q.accepted_answer_id!=M.answer_id when true then 1 else 0 end) cnt,count(*) tot from questions Q join mostupvote M on Q.question_id=M.question_id";
    int[] result = new int[2];
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        result[0] = resultSet.getInt("cnt");
        result[1] = resultSet.getInt("tot");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  public List<Integer> getThreadUsers() {
    String sql = "select question_id,count(*) cnt from answerUser group by question_id";
    List<Integer> res = new ArrayList<>();
    Map<Integer, Integer> users = new HashMap<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        users.put(resultSet.getInt("question_id"), resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    sql = "select question_id,count(*) cnt from commentUser group by question_id";
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        if (users.containsKey(resultSet.getInt("question_id"))) {
          users.put(resultSet.getInt("question_id"),
              users.get(resultSet.getInt("question_id")) + resultSet.getInt("cnt"));
        }
        users.put(resultSet.getInt("question_id"), resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    sql = "select question_id,count(*) cnt from answerandcommentUser group by question_id";
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        users.put(resultSet.getInt("question_id"),
            users.get(resultSet.getInt("question_id")) - resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    users.forEach((key, value) -> {
      res.add(value);
    });
    return res;
  }

  public List<Integer> getAnswerUsers() {
    String sql = "select question_id,count(*) cnt from answerUser group by question_id";
    List<Integer> res = new ArrayList<>();
    Map<Integer, Integer> users = new HashMap<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        users.put(resultSet.getInt("question_id"), resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    users.forEach((key, value) -> {
      res.add(value);
    });
    return res;
  }

  public List<Integer> getCommentUsers() {
    String sql = "select question_id,count(*) cnt from commentUser group by question_id";
    List<Integer> res = new ArrayList<>();
    Map<Integer, Integer> users = new HashMap<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        users.put(resultSet.getInt("question_id"), resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    users.forEach((key, value) -> {
      res.add(value);
    });
    return res;
  }

  public List<Integer> getAnswerDistrubution() {
    String sql = "select count(*) cnt from questions Q join Answers A on Q.question_id = A.question_id group by Q.question_id";
    List<Integer> res = new ArrayList<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        res.add(resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public Map<String, Integer> getCombUpvote() {
    String sql = "select sum(up_vote_count) cnt,tag1,tag2 from comb group by (tag1,tag2)";
    Map<String, Integer> res = new HashMap<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        if (resultSet.getInt("cnt") > 0)
          res.put(resultSet.getString("tag1") + "&" + resultSet.getString("tag2"), resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public Map<String, Integer> getTags() {
    String sql = "select content as tag, cnt from tags join (select tagid,count(*) cnt from tag group by tagid) X on tags.id=X.tagid";
    Map<String, Integer> res = new HashMap<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        res.put(resultSet.getString("tag"), resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public Map<String, Integer> getActivity() {
    String sql = "select display_name,cnt from Users join (select owner_id,count(*) cnt from commentonquestions group by owner_id) X on Users.account_id=X.owner_id";
    Map<String, Integer> res = new HashMap<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        res.put(resultSet.getString("display_name"), resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    sql = "select display_name,cnt from Users join (select owner_id,count(*) cnt from answeronquestions group by owner_id) X on Users.account_id=X.owner_id";
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        if (!res.containsKey(resultSet.getString("display_name")))
          res.put(resultSet.getString("display_name"), resultSet.getInt("cnt"));
        else
          res.put(resultSet.getString("display_name"),
              res.get(resultSet.getString("display_name")) + resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public Map<String, Integer> getCombView() {
    String sql = "select sum(view_count) cnt,tag1,tag2 from comb group by (tag1,tag2)";
    Map<String, Integer> res = new HashMap<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        if (resultSet.getInt("cnt") > 0)
          res.put(resultSet.getString("tag1") + "&" + resultSet.getString("tag2"), resultSet.getInt("cnt"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public List<Integer> gettimeDistribution() {
    String sql = "select a.creation_date-q.creation_date tim from answers a join questions q on a.answer_id = q.accepted_answer_id";
    List<Integer> res = new ArrayList<>();
    try {
      Statement statement = con.createStatement();
      resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        res.add(resultSet.getInt("tim"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  // public int Test(Strng username) {
  // openDatasource();
  // String sql = "select * from replies where author = '" +
  // username + "'";
  // List<Reply> replies = new ArrayList<>();
  // try {
  // Statement statement = con.createStatement();
  // resultSet = statement.executeQuery(sql);

  // while (resultSet.next()) {
  // int postID = resultSet.getInt("postid");
  // Reply r = new Reply();
  // r.setAuthor(author);
  // replies.add(r);
  // }
  // } catch (SQLException e) {
  // e.printStackTrace();
  // }

  // return replies;
  // }

}
