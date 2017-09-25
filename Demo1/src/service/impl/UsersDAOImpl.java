package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import po.Users;
import service.UsersDAO;
import util.MyHibernateSessionFactory;

public class UsersDAOImpl implements UsersDAO
{
	public boolean usersLogin(Users u )
	{
		Transaction tx = null;
		String hql = "";
		try {
			
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where username=? and password=?";
			Query query = session.createQuery(hql);	
			query.setParameter(0, u.getUsername());
			query.setParameter(1, u.getPassword());
			List list = query.list();
			tx.commit();//提交事务
			if(list.size()>0)
			{
				return true;
			}else {
				return false;
			}
		}catch(Exception ex)
		{
			ex.getStackTrace();
			return false;
		}finally {
			if(tx!=null)
			{
			
				tx = null;
			}
		}
	}

	@Override
	public boolean usersRegist(Users u)
	{
		
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();			
			tx=session.beginTransaction();
			Users user = new Users();
			user.setUid(u.getUid());
			user.setUsername(u.getUsername());
			user.setPassword(u.getPassword());
			session.save(user);			
			tx.commit();		
			
		}catch(Exception ex)
		{
			//System.out.println("shibai");
			ex.getStackTrace();
			return false;
		}finally {
			if(tx!=null)
			{
				tx=null;
			}
		}
		
		return true;
	}
	
}
