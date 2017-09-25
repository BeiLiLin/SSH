package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import po.PageParameter;
import po.Students;
import service.StudentsDAO;

public class TestStudentsDAOImpl
{
	@Test
	public void testQueryAllStudnts() {
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students>list = new ArrayList<Students>();
		PageParameter pg = new PageParameter();
		int count = pg.getPageCount();
		list=sdao.queryAllStudents(pg.getCurPage());
		System.out.println("总页数"+pg.getPageCount());
		for(Students s :list)
		{
			System.out.println(s.toString());
		}
	}
	@Test
	public void testGetsid() {
		StudentsDAOImpl sdao = new StudentsDAOImpl();
		String s=sdao.getStuid();
		System.out.println(s);
	}
	@Test
	public void testAddStudents() {
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s= new Students("","阿明","男",new Date());
		sdao.addStudents(s);
		
	}
	
	/*		@Test
public void testSearchStudents() {
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students>list= sdao.searchStudents("", "","","2016-10-01","2018-01-01");
		
		for(Students stu : list)
		{
			System.out.println(stu.getStuID());
			System.out.println(stu.getSex());
			System.out.println(stu.getStuName());
			System.out.println(stu.getBirthday());
			System.out.println(stu.toString());
		}
	}*/
}
