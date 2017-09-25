package entity;



import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import po.Students;
import service.StudentsDAO;
import service.impl.StudentsDAOImpl;

public class TestStudents
{
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	
	@Test
	public void testSchemaExport() {
		//创建配置对象
		Configuration config = new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.applySettings(config.getProperties()).build();
		//创建会话工厂
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		session = sessionFactory.openSession();
		//生成表结构
		SchemaExport export= new SchemaExport(config);	
		export.create(true,true);	
	}	
	
	@Test
	public void testSaceStudents() {
		//配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册工厂
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.applySettings(config.getProperties()).build();
		//创建会话工厂
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建会话对象
		session = sessionFactory.openSession();
		//生成事务
		transaction = session.beginTransaction();
		
		Students s4 = new Students("S00004","林泽洪","男",new Date());
		Students s2 = new Students("S00002","林泽洪","男",new Date());
		Students s3 = new Students("S00003","林泽洪","男",new Date());
		session.save(s2);
		session.save(s3);
		session.save(s4);
		transaction.commit();
		
	}	
	
}
