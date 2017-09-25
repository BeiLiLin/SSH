package service;

import po.Users;

//用户业务逻辑接口
public interface UsersDAO
{
//用户登录、
	public boolean usersLogin(Users u);
//用户注册
	public boolean usersRegist(Users u);
}
