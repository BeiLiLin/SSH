package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import po.PageParameter;
import po.SearchQuery;
import po.Students;
import service.StudentsDAO;
import util.MyHibernateSessionFactory;

//学生业务逻辑接口的实现类
public class StudentsDAOImpl implements StudentsDAO
{

	@Override
	public List<Students> queryAllStudents(int curpage)
	{
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<Students> list = null;
		String hql = "";
		//System.out.println("queryAllStudentscurpage"+curpage);
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			//System.out.println("1000");
			tx = session.beginTransaction();
			hql = "from Students ";
			//System.out.println("1100");
			Query query = session.createQuery(hql);
			//System.out.println(hql);
			//System.out.println("curpage45645656："+curpage);
			//数据开始位置
			query.setFirstResult((curpage-1)*10);						
			query.setMaxResults(10);//每页的数据量
			list = query.list();
			//System.out.println("1001");
			tx.commit();
			//System.out.println("1002");
			return list;

		} catch (Exception ex)
		{
			//System.out.println("shibai");
			ex.getStackTrace();
			tx.commit();
			tx=null;
			return list;
		} finally
		{
			if (tx != null)
			{
				tx = null;
			}
		}
	}	
	@Override
	//通过id查找学生
	public Students queryStudentsBySid(String stuid)
	{
		Transaction tx=null;
		Students s=null;
		try {
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			s=(Students)session.get(Students.class, stuid);
			tx.commit();
			return s;
		}catch(Exception ex)
		{
			ex.getStackTrace();
			tx.commit();
			return null;
		}finally {
			if(tx!=null)
			{
				tx=null;
			}
		}
		
	}

	@Override
	public boolean addStudents(Students s)
	{
		s.setStuID(getStuid());//设置学生的学号
		//System.out.println("s.setstuid"+s.getStuID());
		Transaction tx = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			session.save(s);
			tx.commit();
			return true;
		}catch(Exception ex)
		{
			ex.getStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx!=null) {
				tx=null;
			}
		}
		
	}

	// 学生主键生成策略
	public String getStuid()
	{
		Transaction tx = null;
		String hql = "";
		String stuid = null;
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();

			tx = session.beginTransaction();

			hql = "select max(stuID) from Students";
			
			Query query = session.createQuery(hql);

			
			stuid = (String) query.uniqueResult();
			
			tx.commit();

			if (stuid == null || "".equals(stuid))
			{
				stuid = "S00001";
			} else
			{
				String temp = stuid.substring(1);
				int i = Integer.parseInt(temp);
				i++;
				temp = String.valueOf(i);//还原为字符串
				int len = temp.length();
				for (int j = len; j < 5; j++)
				{
					temp = "0" + temp;

				}
				stuid = "S" + temp;
				//System.out.println("sid"+stuid);
			}
			return stuid;
		} catch (Exception ex)
		{
			tx.commit();
			ex.getStackTrace();		
			return null;
		} finally
		{
			if (tx != null)
			{
				tx = null;
			}
		}
	}
	@Override
	public boolean updateStudents(Students s)
	{
		Transaction tx = null;
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();	
			session.update(s);
			tx.commit();
			return true;
		} catch (Exception ex)
		{
			ex.getStackTrace();
			tx.commit();
			return false;
		} finally
		{
			if (tx != null)
			{
				tx = null;
			}
		}
	}

	@Override
	public boolean deleteStudents(String stuid)
	{
		Transaction tx = null;
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//System.out.println("id" + stuid);
			Students s = (Students) session.get(Students.class, stuid);// 获取该sid所对应的学生对象
			session.delete(s);
			//System.out.println("cg");
			tx.commit();
			return true;
		} catch (Exception ex)
		{
			ex.getStackTrace();
			tx.commit();
			return false;
		} finally
		{
			if (tx != null)
			{
				tx = null;
			}
		}

	}

	//学生查询
	@Override
	public List<Students> searchStudents(SearchQuery sq,int curpage)
	{
			Transaction tx=null;
			String hql="from Students ",query="";	
			//生成判断语句query
			//判断学生编号是否为空
		/*	System.out.println(sq.toString());
			System.out.println("sq.getStuid()"+sq.getStuid());
			System.out.println("sq.getBirthday1()"+sq.getBirthday1());
			System.out.println("sq.getBirthday2()"+sq.getBirthday2());
			System.out.println("sq.getSex()"+sq.getSex());
			System.out.println("sq.getStuname()"+sq.getStuname());		*/				
			if(sq.getStuid()!=null)
			{
				query="where stuID='"+sq.getStuid()+"' ";
			}else {
				//判断名字是否为空
				if(sq.getStuname()!=null)
				{
					query="where stuName='"+sq.getStuname()+"'  ";
				}
				
				//判断sex是否为空
				if(sq.getSex()!=null)
				{
					if(query.length()>0)
					{
						query=query+" and sex='"+sq.getSex()+"' ";
				
					}else {
						query="where sex='"+sq.getSex()+"'  ";
					
					}
				}

				//判断出生日期
				if(sq.getBirthday1()!=null&&sq.getBirthday2()==null)
				{
					if(query.length()>0)
					{
						query=query+"  and birhday>'"+sq.getBirthday1()+"' ";
					}else {
						query="where birthday>'"+sq.getBirthday1()+"'  ";
					}
				}else if(sq.getBirthday1()==null&&sq.getBirthday2()!=null)
				{
					if(query.length()>0)
					{
						query=query+"  and birhday<='"+sq.getBirthday2()+"' ";
					}else {
						query="where birthday<='"+sq.getBirthday2()+"' ";
					}
				}else if(sq.getBirthday1()!=null&&sq.getBirthday2()!=null)
				{
					if(query.length()>0)
					{
						query=query+"  and birthday between '"+sq.getBirthday1()+"' and '"+sq.getBirthday2()+"' ";
					}else {
						query=" where birthday between '"+sq.getBirthday1()+"' and '"+sq.getBirthday2()+"' ";
					}
				}

			}

			if(query.length()>0)
			{
				hql=hql+query;//合成查询语句
			}System.out.println("hql"+hql);
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			Query q = session.createQuery(hql);
			//设置分页每页的数据量
			q.setFirstResult(((curpage-1)*10));
			q.setMaxResults(10);//每页的数据量
			List<Students>list=q.list();
			tx.commit();
			//System.out.println("List<Students>list=q.list();:"+list.toString());			
			return list;
		}catch(Exception ex) {
			//System.out.println("sreach_failure");
			ex.getStackTrace();
			tx.commit();
			return null;
		}finally {
			if(tx!=null) 
			{
				tx=null;
			}
		}
	}
	//获取页面总数
	public int getpageCount() {
		
		int PageCount;
		Transaction tx=null;
		PageParameter pg =new PageParameter();
		
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			String hql=" from Students ";
			Query query = session.createQuery(hql);
			//三则表达式，？：true：error
			PageCount = (query.list().size() % pg.getPageSize() == 0) ? (query.list().size() / pg.getPageSize()) : (query.list().size() / pg.getPageSize() + 1);
			tx.commit();
			return PageCount;
		}catch(Exception ex)
		{
			tx.commit();
			ex.getStackTrace();
			return 0;
		}finally {
			if(tx!=null)
			{
				tx=null;
			}
		}		
	}
	
	//获取查询的页面总数
		public int getpageCount(SearchQuery sq) {
			
			int PageCount;
			Transaction tx=null;
			PageParameter pg =new PageParameter();
			//System.out.println("pagecount1");
			try {
				Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
				tx=session.beginTransaction();
				String hql="from Students ";
				String query="";
				System.out.println("pagecount2");
				//生成判断语句query
				//判断学生编号是否为空
				if(sq.getStuid()!=null)
				{
					query="where stuID='"+sq.getStuid()+"' ";
				}else {
					//判断名字是否为空
					if(sq.getStuname()!=null)
					{
						query="where stuName='"+sq.getStuname()+"' ";
					}
					
					//判断sex是否为空
					if(sq.getSex()!=null)
					{
						if(query.length()>0)
						{
							query=query+" and sex='"+sq.getSex()+"'";
						}else {
							query="where sex='"+sq.getSex()+"' ";
						}
					}
					//判断出生日期
					if(sq.getBirthday1()!=null&&sq.getBirthday2()==null)
					{
						if(query.length()>0)
						{
							query=query+" and birhday>'"+sq.getBirthday1()+"'";
						}else {
							query="where birthday>'"+sq.getBirthday1()+"' ";
						}
					}else if(sq.getBirthday1()==null&&sq.getBirthday2()!=null)
					{
						if(query.length()>0)
						{
							query=query+" and birhday<='"+sq.getBirthday2()+"'";
						}else {
							query="where birthday<='"+sq.getBirthday2()+"' ";
						}
					}else if(sq.getBirthday1()!=null&&sq.getBirthday2()!=null)
					{
						if(query.length()>0)
						{
							query=query+" and birthday between '"+sq.getBirthday1()+"' and '"+sq.getBirthday2()+"' ";
						}else {
							query="where birthday between '"+sq.getBirthday1()+"' and '"+sq.getBirthday2()+"' ";
						}
					}
					
				}
				if(query.length()>0)
				{
					hql=hql+query;//合成查询语句
					
				}
				System.out.println("pagecount hql:"+hql);
				Query q = session.createQuery(hql);
				PageCount = (q.list().size() % pg.getPageSize() == 0) ? (q.list().size() / pg.getPageSize()) : (q.list().size() / pg.getPageSize() + 1);
				tx.commit();
				//System.out.println("pagecount123456789 :"+PageCount);
				
				return PageCount;
			}catch(Exception ex)
			{
				//System.out.println("shibai");
				tx.commit();
				ex.getStackTrace();
				return 0;
			}finally {
				if(tx!=null)
				{
					tx=null;
				}
			}
		}
}
