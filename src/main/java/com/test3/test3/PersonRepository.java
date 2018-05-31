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
    public int createLeader(String leaderid)
    {
    	int count = jdc.queryForObject("select count(*) from leaderid where leaderid=?", new Object[] {leaderid},Integer.class);
    	if(count==0) {return jdc.update("insert into leaderid(leaderid)values(?)",leaderid);}
    	else return 0;
    }
	public int createGroup(String leaderid,String groupid,String grouptype,String leadername,String groupName)
	{
		
		int count = jdc.queryForObject("select count(*) from volonteerGroups where groupid=?", new Object[] {groupid},Integer.class);
		if(count>0)return 0;
		else 
		{          jdc.update("insert into coordinates(groupid,latlng)values(?,?);",groupid,null);
			return jdc.update("insert into volonteerGroups(leaderid,groupid,grouptype,leadername,groupname)values(?,?,?,?,?)"
					,leaderid,groupid,grouptype,leadername,groupName);
		}
		
	}
	public int insertIntoGroup(String volonteerName,String groupid)
	{
		return jdc.update("insert into volonteers(volonteername,groupid,come,eat)values(?,?,false,false)",volonteerName,groupid);
		
	}
	public int setCome(String volonteerName,String groupid,boolean come)
	{
		return jdc.update("update volonteers set come=? where volonteerName=? and groupid=?",come,volonteerName,groupid);
		
	}
	public int setEat(String volonteerName,String groupid,boolean eat)
	{
		return jdc.update("update volonteers set eat=? where volonteerName=? and groupid=?",eat,volonteerName,groupid);
		
	}
	public HashMap<String,Group>displayGroup()
	{
		HashMap<String,Group>leaders=new HashMap<String, Group>();
	    List<Group>lgroup=jdc.query("select * from volonteerGroups", new GroupMapper());
	    if(!lgroup.isEmpty())
	    	for(Group g:lgroup)
	    	{
	    		leaders.put(g.groupType, g);
	    	}
		return leaders;
	}
	public List<Group> displayMyGroups(String leaderid)
	{
		return jdc.query("select * from volonteerGroup where groupid=?", new GroupMapper(),leaderid);
	}
	public int insertCoordinates(String groupid,String latlng)
	{
		  return jdc.update("update coordinates set latlng = ? where groupid =?;",latlng,groupid);
		
	}
	public String getCoordiantes(String groupid)
	{
		return jdc.queryForObject("select latlng from coordinates where grouppassword=?;",new Object[] {groupid},String.class);
		
	}
	
    public HashMap<String,Volonteer> display(String groupid)
    {
    	List<Volonteer> volonteers=jdc.query("Select * from volonteers where grouppassword=? and type=false;",new VolonteerMapper(),groupid);
    
    	HashMap<String,Volonteer> vGroup = new HashMap<String,Volonteer>();
    	if(!volonteers.isEmpty())
    	for(Volonteer v:volonteers)
    	{
    		vGroup.put(v.getName(),v);
    	}
    	return vGroup;
    }
	
}
