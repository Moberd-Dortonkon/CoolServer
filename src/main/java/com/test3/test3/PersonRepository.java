package com.test3.test3;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonRepository {
	@Autowired
	private JdbcTemplate jdc;
    public int createLeader(String leaderid,String name)
    {
    	int count = jdc.queryForObject("select count(*) from leaderid where leaderid=?", new Object[] {leaderid},Integer.class);
    	if(count==0) {return jdc.update("insert into leaderid(leaderid,name)values(?,?)",leaderid,name);}
    	else return 0;
    }
	public int createGroup(String leaderid,String groupid,String grouptype,String leadername,String groupName,String description)
	{
		
		int count = jdc.queryForObject("select count(*) from volonteerGroups where groupid=?", new Object[] {groupid},Integer.class);
		if(count>0)return 0;
		else 
		{          jdc.update("insert into coordinates(groupid,latlng)values(?,?);",groupid,null);
			return jdc.update("insert into volonteerGroups(leaderid,groupid,grouptype,leadername,groupname,description)values(?,?,?,?,?,?)"
					,leaderid,groupid,grouptype,leadername,groupName,description);
		}
		
	}
	public int insertIntoGroup(String volonteerid,String groupid)
	{
		int count = jdc.queryForObject("select count(*) from volonteers where volonteername = ? ",new Object[] {volonteerid}, Integer.class);
		if(count==0)
		return jdc.update("insert into volonteers(volonteername,groupid,come,eat)values(?,?,false,false)",volonteerid,groupid);
		if(count==1)
			return jdc.update("update volonteers set groupid=? where voloneername=?",groupid,volonteerid);
		else return 0;
		
	}
	public int setCome(String volonteerid,String groupid,boolean come)
	{
		return jdc.update("update volonteers set come=? where volonteerName=? and groupid=?",come,volonteerid,groupid);
		
	}
	public int setEat(String volonteerName,String groupid,boolean eat)
	{
		return jdc.update("update volonteers set eat=? where volonteerName=? and groupid=?",eat,volonteerName,groupid);
		
	}
	public List<Group>displayGroup()
	{
		HashMap<String,Group>leaders=new HashMap<String, Group>();
	    return jdc.query("select * from volonteerGroups", new GroupMapper());
	 //   if(!lgroup.isEmpty())
	 //   	for(Group g:lgroup)
	 //   	{
	   // 		leaders.put(g.groupType, g);
	   // 	}
		//return leaders;
	}
	public List<Group> displayMyGroups(String leaderid)
	{
		return jdc.query("select * from volonteerGroups where leaderid=?", new GroupMapper(),leaderid);
	}
	public int insertCoordinates(String groupid,String latlng)
	{
		  return jdc.update("update volonteergroups set coordinates = ? where groupid =?;",latlng,groupid);
		
	}
	public String getCoordiantes(String groupid)
	{
		return jdc.queryForObject("select coordinates from volonteergroups where groupid=?;",new Object[] {groupid},String.class);		
	}
	
    public HashMap<String,Volonteer> display(String groupid)
    {
    	List<Volonteer> volonteers=jdc.query("Select * from volonteers where groupid=?;",new VolonteerMapper(),groupid);
    	HashMap<String,Volonteer> vGroup = new HashMap<String,Volonteer>();
    	if(!volonteers.isEmpty())
    	for(Volonteer v:volonteers)
    	{
    	    String name = jdc.queryForObject("select name from leaderid where leaderid=?",new Object[] {v.getName()} ,String.class);
    	    v.setName(name);
    		vGroup.put(name,v);
    	}
    	return vGroup;
    }
    public Volonteer getInformation(String groupid,String name)
    {
    	return jdc.queryForObject("select * from volonteers where groupid =? and volonteername =?", new VolonteerMapper(),groupid,name);
		
    }
	
}
