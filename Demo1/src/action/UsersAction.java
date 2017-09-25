package action;
import com.opensymphony.xwork2.ModelDriven;

import po.Users;
import service.UsersDAO;
import service.impl.UsersDAOImpl;


public class UsersAction extends SuperAction implements ModelDriven<Users> {
	
	private static final long serialVersionUID = 1L;
	
	private Users user = new Users();			

	@Override
	public Users getModel()
	{
		return this.user;
	}

	// 登录处理
	public String login() throws Exception {
		session.setAttribute("tip", "");
		UsersDAO udao = new UsersDAOImpl();
		if(udao.usersLogin(user))
		{
			session.setAttribute("user", user.getUsername());
			return "login_success";
		}
		else {
			request.setAttribute("tip","抱歉,您登录失败！<br>请检查您的输入信息是否正确" );
			return "login_failure";
		}
	}
	
	
	public String regist() throws Exception {
		//初始化
		UsersDAO udao = new UsersDAOImpl();		
		if (udao.usersRegist(user)) {
			request.setAttribute("ruser", user.getUsername());
			request.setAttribute("rpass", user.getPassword());
			return "regist_success";	
			}
		request.setAttribute("tip","抱歉,您注册注册失败！<br>请检查您的输入信息是否正确" );
		return "regist_failure";
	}


}
