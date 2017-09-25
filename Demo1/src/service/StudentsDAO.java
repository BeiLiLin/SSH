package service;

import java.util.List;

import po.SearchQuery;
import po.Students;

//学生的业务逻辑接口
public interface StudentsDAO
{
	//查询所有学生的资料
	public List<Students>queryAllStudents(int curpage);
	
	//查询学生信息
	public List<Students>searchStudents(SearchQuery sq,int curpage);
	
	//根据学生编号查询学生资料
	public Students queryStudentsBySid(String stuid);
	
	//添加学生资料
	public boolean addStudents(Students s);
	
	//修改学生资料
	public boolean updateStudents(Students s);
	
	//删除学生资料
	public boolean deleteStudents(String stuid);
}
