package cse.java2.project.model;

import java.util.List;

public class Question_O {
	public int accepted_answer_id;
	public int answer_count;
	public List<Answer_O> answers;
	public int comment_count;
	public List<Comment_O> comments;
	public int creation_date;
	public int down_vote_count;
	public int favorite_count;
	public boolean is_answered;
	public User_O owner;
	public int question_id;
	public List<String> tags;
	public String title;
	public int up_vote_count;
	public int view_count;

	public String[] Info() {
		String[] info = new String[12];
		info[0] = String.valueOf(accepted_answer_id);
		info[1] = String.valueOf(answer_count);
		info[2] = String.valueOf(comment_count);
		info[3] = String.valueOf(creation_date);
		info[4] = String.valueOf(down_vote_count);
		info[5] = String.valueOf(favorite_count);
		info[6] = String.valueOf(is_answered);
		info[7] = String.valueOf(owner.account_id);
		info[8] = String.valueOf(question_id);
		info[9] = String.valueOf(title);
		info[10] = String.valueOf(up_vote_count);
		info[11] = String.valueOf(view_count);
		return info;
	}
}
