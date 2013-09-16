package navymail.modules;

public class User {

	public int userID;
	public int signatureColor;
	public String name;
	public String jobName;

	public User(int id, int color, String name, String job) {
		userID = id;
		signatureColor = color;
		this.name = name;
		jobName = job;
	}

	public User() {
	}

}
