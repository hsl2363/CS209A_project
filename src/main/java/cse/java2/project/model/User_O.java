package cse.java2.project.model;

public class User_O {
	public int accepted_rate;
	public int account_id;
	public String display_name;
	public int reputation;
	public int user_id; // may be null

	public String[] Info() {
		String[] info = new String[5];
		info[0] = String.valueOf(accepted_rate);
		info[1] = String.valueOf(account_id);
		info[2] = String.valueOf(display_name);
		info[3] = String.valueOf(reputation);
		info[4] = String.valueOf(user_id);
		return info;
	}
}
