package action;

import com.opensymphony.xwork2.ActionSupport;

public class LinksAction extends ActionSupport{
	public  String query()
	{
		return "query";
	}
	public String search()
	{
		return "search";
	}
	public String add() 
	{
		return "add";
	}
	public String delete() 
	{
		return "delete";
	}
	public String update()
	{
		return "update";
	}
	public String loginregist()
	{
		return "loginregist";
	}
}
