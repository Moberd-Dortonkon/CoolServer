package com.test3.test3;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class TestContoller {
	Gson gson;
    @Autowired
    private PersonRepository jdc;
    @RequestMapping("/create/leader")
    public String createLeader()
    {	
        while(true)
        {
        	String s = createPassword();
        	if(jdc.createLeader(s)==1)return s;
        }
    }
    @RequestMapping("/create/group")
    public String createGroup(@RequestParam(value="lName",defaultValue="")String leaderid,
    		@RequestParam(value="grouptype",defaultValue="")String grouptype
    		,@RequestParam(value="leadername",defaultValue="")String leadername,
            @RequestParam(value="groupname",defaultValue="")String groupName)
    {
    	while(true)
    	{
    	  String groupid = createPassword()	;
    	  if(jdc.createGroup(leaderid, groupid, grouptype, leadername, groupName)==1)return "complete";
    	}
    	
    }
    @RequestMapping("/insertIntoBolonteers")
    public String insertIntoGroup(@RequestParam(value="volonteerName",defaultValue="")String volonteerName,
    		@RequestParam(value="groupid",defaultValue="")String groupid)
    {
    	if(jdc.insertIntoGroup(volonteerName, groupid)==1)return "complete";
    	else return "not complete";
    	
    }
    
    
  
    
    
    
    
    
    
    
    
    
    
    @RequestMapping("")
    public String start() {return "HELLO";}
        
    private String createPassword() 
	{
		   SecureRandom secureRandom = new SecureRandom();
	        String s="Aaa";
	        String summ="";
	        for(int i =0;i<s.length();i++)
	        {
	        char c=(char) (s.charAt(i)+secureRandom.nextInt(25));
	         summ=summ+c;

	        }
	        summ+=secureRandom.nextInt(999);
	       return summ;
	}
}
