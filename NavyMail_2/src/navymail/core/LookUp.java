package navymail.core;

import java.util.HashMap;

import navymail.modules.User;
import android.graphics.Color;

public class LookUp {
	private static LookUp instance = new LookUp();
	public static LookUp getInstance(){
		return instance;
	}

	public LookUp() {
		fillHash();
	}
	
	
	//======================= LookUp Functions ==============================
	//=======================    Users Hash    ==============================
	public HashMap<Integer, User> usersHash = new HashMap<Integer, User>();
	private void fillHash(){
		User Qa2d = new User(1, Color.RED, "فريق / أسامة أحمد الجندى", "قائد القوات البحرية");
		User RA2ES_ARKAN = new User(2, Color.RED, "لواء بحرى أ.ح/ محمد مجدى عبدالسميع","رئيس أركان القوات البحرية");
		User RA2ES_ELFAR3 = new User(3, Color.RED, "عميد بحرى أ.ح / ياسر فاروق", "رئيس فرع سكرتارية قيادة القوات البحرية");
		usersHash.put(Qa2d.userID, Qa2d);
		usersHash.put(RA2ES_ARKAN.userID, RA2ES_ARKAN);
		usersHash.put(RA2ES_ELFAR3.userID, RA2ES_ELFAR3);
	}

}
