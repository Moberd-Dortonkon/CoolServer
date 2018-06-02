package com.test3.test3;

import java.util.HashMap;

public class DisplayVolonteers {
 HashMap<String ,Volonteer>vGroup;

public DisplayVolonteers(HashMap<String, Volonteer> vGroup) {
	this.vGroup = vGroup;
}

public HashMap<String, Volonteer> getvGroup() {
	return vGroup;
}

public void setvGroup(HashMap<String, Volonteer> vGroup) {
	this.vGroup = vGroup;
}
 
}
