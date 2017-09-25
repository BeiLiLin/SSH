package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MyHibernateSessionFactory
{
	private static SessionFactory sessionFactory;
	//构造方法私有化，保证单例模式
	private MyHibernateSessionFactory()
	{
		
	}
	//公有的静态方法，获得工厂对象
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{
			//创建配置对象
			Configuration config = new Configuration().configure();
			//StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
			//创建服务注册对象
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			//创建会话工厂
			 sessionFactory = config.buildSessionFactory(serviceRegistry);			
			return sessionFactory;
		}else{
			return sessionFactory;
		}
		
	}
}
