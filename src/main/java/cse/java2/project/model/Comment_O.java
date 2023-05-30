package cse.java2.project.model;

public class Comment_O {
	public int comment_id;
	public int creation_date;
	public User_O owner;
	public int post_id;
	public boolean reply_to_user;

	public String[] Info() {
		String[] info = new String[5];
		info[0] = String.valueOf(comment_id);
		info[1] = String.valueOf(creation_date);
		info[2] = String.valueOf(owner.account_id);
		info[3] = String.valueOf(post_id);
		info[4] = String.valueOf(reply_to_user);
		return info;
	}
}
