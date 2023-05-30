package cse.java2.project;

import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cse.java2.project.model.*;
import cse.java2.project.repository.DAO;

public class Data_Import {

	private static Map<String, Integer> ID = new HashMap<>();

	private static int tagcnt = 0;

	public static void main(String[] args) {

		try {
			BufferedReader in = new BufferedReader(
					new FileReader("D:\\Sources\\2B\\JAVA2\\project\\2023-Spring-Java2-Project-Demo\\data\\data.json"));
			StringBuilder sb = new StringBuilder();
			String Str;
			while ((Str = in.readLine()) != null) {
				sb.append(Str).append("\n");
			}
			String jsonStrings = sb.toString();
			List<Question_O> questions = JSON.parseArray(jsonStrings, Question_O.class);
			in.close();

			try {
				DAO dm = new DAO();
				dm.openDatasource();

				for (int i = 0; i < questions.size(); i++) {
					Question_O question = questions.get(i);
					addComment(dm, question.comments, false);
					addAnswer(dm, question.answers);
					addUser(dm, question.owner);
					List<String> tags = question.tags;
					for (int j = 0; j < tags.size(); j++) {
						if (!ID.containsKey(tags.get(j))) {
							dm.addTag(tags.get(j));
							ID.put(tags.get(j), ++tagcnt);
						}
						dm.addtag(question.question_id, ID.get(tags.get(j)));
					}
					dm.addQuestion(question.Info());
				}

				dm.closeDatasource();
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void addUser(DAO dm, User_O user) {
		if (user == null)
			return;
		dm.addUser(user.Info());
	}

	private static void addAnswer(DAO dm, List<Answer_O> answers) {
		if (answers == null)
			return;
		for (int i = 0; i < answers.size(); i++) {
			Answer_O answer = answers.get(i);
			addUser(dm, answer.owner);
			addComment(dm, answer.comments, true);
			dm.addAnswer(answer.Info());
		}
	}

	private static void addComment(DAO dm, List<Comment_O> comments, boolean reply_to_user) {
		if (comments == null)
			return;
		for (int i = 0; i < comments.size(); i++) {
			Comment_O comment = comments.get(i);
			comment.reply_to_user = reply_to_user;
			addUser(dm, comment.owner);
			dm.addComment(comment.Info());
		}
	}
}