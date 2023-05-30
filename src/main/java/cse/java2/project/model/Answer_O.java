package cse.java2.project.model;

import java.util.List;

public class Answer_O {
	public int answer_id;
	public int comment_count;
	public List<Comment_O> comments;
	public int creation_date;
	public int down_vote_count;
	public boolean is_accepted;
	public User_O owner;
	public int question_id;
	public int up_vote_count;

	public String[] Info() {
		String[] info = new String[8];
		info[0] = String.valueOf(answer_id);
		info[1] = String.valueOf(comment_count);
		info[2] = String.valueOf(creation_date);
		info[3] = String.valueOf(down_vote_count);
		info[4] = String.valueOf(is_accepted);
		info[5] = String.valueOf(owner.account_id);
		info[6] = String.valueOf(question_id);
		info[7] = String.valueOf(up_vote_count);
		return info;
	}
}
