package com.test3.test3;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public String createGroup(@RequestParam(value="leaderid",defaultValue="")String leaderid,
    		@RequestParam(value="grouptype",defaultValue="")String grouptype
    		,@RequestParam(value="leadername",defaultValue="")String leadername,
            @RequestParam(value="groupname",defaultValue="")String groupName,
            @RequestParam(value="description",defaultValue="")String description)
    {
    	while(true)
    	{
    	  String groupid = createPassword()	;
    	  if(jdc.createGroup(leaderid, groupid, grouptype, leadername, groupName,description)==1)return "complete";
    	}
    	
    }
    @RequestMapping("/insertIntoVolonteers")
    public String insertIntoGroup(@RequestParam(value="volonteerName",defaultValue="")String volonteerName,
    		@RequestParam(value="groupid",defaultValue="")String groupid)
    {
    	if(jdc.insertIntoGroup(volonteerName, groupid)==1)return "complete";
    	else return "not complete";
    	
    }
    @RequestMapping("/set/come")
    public String setCome(@RequestParam(value="volonteerName",defaultValue="")String volonteerName,
    		@RequestParam(value="groupid",defaultValue="")String groupid,
    		@RequestParam(value="come",defaultValue="")String come) 
    {
    	if(come.equals("true")) {jdc.setCome(volonteerName, groupid, true);return "complete";}
    	if(come.equals("false")) {jdc.setCome(volonteerName, groupid, false);return "complete";}
    	else return "notcomplete";
    
    	
    }
    @RequestMapping("/set/eat")
    public String setEat(@RequestParam(value="volonteerName",defaultValue="")String volonteerName,
    		@RequestParam(value="groupid",defaultValue="")String groupid,
    		@RequestParam(value="come",defaultValue="")String eat) 
    {
    	if(eat.equals("true")) {jdc.setEat(volonteerName, groupid, true);return "complete";}
    	if(eat.equals("false")) {jdc.setEat(volonteerName, groupid, false);return "complete";}
    	else return "notcomplete";
    
    	
    }
    @RequestMapping("/display/groups")
    public List<Group>displayGroups()
    {
    	return jdc.displayGroup();
    }
    @RequestMapping("/display/myGroup")
    public List<Group>displayMyGroups(@RequestParam(value="leaderid",defaultValue="")String leaderid)
    {
    	return jdc.displayMyGroups(leaderid);
    }
    @RequestMapping("/display/volonteers")
    public DisplayVolonteers display(@RequestParam(value="groupid",defaultValue="")String groupid)
    {
    	return new DisplayVolonteers(jdc.display(groupid));
    	
    }
    @RequestMapping("/display/me")
    public String getMyInformation(String groupid,String name)
    {
    	
    	 Volonteer v =jdc.getInformation(groupid, name);
	       Gson gson = new Gson();
	       return gson.toJson(v);
    }
    @RequestMapping("/coordinates/set")
	public String insertCoordinates(@RequestParam(value="groupid",defaultValue="")String groupid,@RequestParam(value="latlng",defaultValue="")String latlng)
	{
    	  jdc.insertCoordinates(groupid, latlng);
		  return latlng;
	}
    @RequestMapping("/coordinates/get")
    
	public String getCoordiantes(@RequestParam(value="groupid",defaultValue="")String groupid)
	{
		return jdc.getCoordiantes(groupid);
		
	}
    
  
    
    
    
    
    
    
    
    
    
    
    @RequestMapping("")
    public List<Group> start()
    
    {
            List<Group>groups=new ArrayList<Group>();
            groups.add(new Group("test","test","test","test","test"));
            groups.add(new Group("test","test","test","test","test"));
            groups.add(new Group("test","test","test","test","test"));
            groups.add(new Group("test","test","test","test","test"));
    	return groups;}
        
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
