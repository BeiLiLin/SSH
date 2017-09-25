package service.impl;

import org.junit.Test;

import junit.framework.Assert;
import po.Users;

public class TestUsersDAOImpl
{
	@Test
	public void testUsersLogin()
	{
		Users u = new Users(1,"admin","1234");
		
		UsersDAOImpl udao = new UsersDAOImpl();
		Assert.assertEquals(true, udao.usersLogin(u));
	}
	@Test
	public void testUsersRegist()
	{
		Users u =new Users();
		u.setUsername("jason");
		u.setPassword("1234");
		UsersDAOImpl udao = new UsersDAOImpl();
		udao.usersRegist(u);
	}
}

