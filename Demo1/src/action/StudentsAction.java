package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import po.PageParameter;
import po.SearchQuery;
import po.Students;
import service.StudentsDAO;
import service.impl.StudentsDAOImpl;

public class StudentsAction extends SuperAction
{
	private static final long serialVersionUID = 1L;

	// 查询所有学生的动作
	public String query() throws Exception
	{
		session.setAttribute("query", 1);
		session.removeAttribute("search");
		StudentsDAO sdao = new StudentsDAOImpl();
		// 获取分页信息
		PageParameter pg = new PageParameter();
		try
		{
			pg = (PageParameter) session.getAttribute("pg");
		} catch (Exception ex)
		{
			System.out.println("query1出错");
			ex.getStackTrace();
			return "error";
		}
		List<Students> list = new ArrayList<Students>();
		//try
		//{
			if (session.getAttribute("delete") != null)
			{
				session.removeAttribute("delete");

				if (pg.getCurPage() > pg.getPageCount())
				{
					pg.setCurPage(pg.getPageCount());
					session.setAttribute("curpage", pg.getCurPage());
				}
				session.setAttribute("pagecount", pg.getPageCount());
			} else
			{
				if (request.getParameter("curpage") != null)
				{
					int curpage = 0;
					try
					{
						curpage = Integer.parseInt(request.getParameter("curpage"));
					} catch (Exception ex)
					{
						//System.out.println("query2出错");
						ex.getStackTrace();
						return "error";
					}
					// 更新当前页面
					if (curpage < 1)
					{

						return "query_success";
					} else if (curpage > pg.getPageCount())
					{

						return "query_success";
					} else
					{
						pg.setCurPage(curpage);
						System.out.println("curpage的改变：" + pg.getCurPage());
					}
				}

				// 更新session的值
				session.setAttribute("curpage", pg.getCurPage());
				session.setAttribute("pagecount", pg.getPageCount());
				session.setAttribute("pg", pg);
			}
		/*} catch (Exception ex)
		{
			System.out.println("query3出错");
			ex.getStackTrace();
		}*/
		// 获取分页的学生信息

		if (sdao.queryAllStudents(pg.getCurPage()).isEmpty())
		{
			return "error";
		} else
		{
		//	try
			//{
				list = sdao.queryAllStudents(pg.getCurPage());
		/*	} catch (Exception ex)
			{
				System.out.println("query4出错");
				ex.getStackTrace();
				return "error";
			}*/
			// 放进session中
			if (list != null && list.size() > 0)
			{
				session.setAttribute("students_list", list);

			}
			return "query_success";
		}
	}

	// 获取分页信息query
	public String pagecount() throws Exception
	{
		session.removeAttribute("students_list");
		// 进入的是主界面
		PageParameter pg = new PageParameter();
		int pagecount = 0;// 初始化pagecount
		try
		{
			pagecount = new StudentsDAOImpl().getpageCount();// 设置pagecount
		} catch (Exception ex)
		{
			System.out.println("pagecount1出错");
			ex.getStackTrace();
			return "error";
		}
		pg.setPageCount(pagecount);
		if (session.getAttribute("delete") != null)
		{
			try
			{
				pg.setCurPage(((PageParameter) session.getAttribute("pg")).getCurPage());
			} catch (Exception ex)
			{
				System.out.println("pagecount2出错");
				ex.getStackTrace();
				return "error";
			}
		}

		session.setAttribute("pg", pg);
		return "pagecount_query_success";
	}

	// 获取分页信息search
	public String spagecount() throws Exception
	{

		session.removeAttribute("search_list");
		PageParameter pg = new PageParameter();
		int pagecount = 0;
		try
		{
			SearchQuery sq = (SearchQuery) session.getAttribute("searchquery");
			pagecount = new StudentsDAOImpl().getpageCount(sq);
			pg.setPageCount(pagecount);
			//System.out.println("pagecounttry:" + pagecount);
		} catch (Exception ex)
		{
			//System.out.println("pagecount3出错");
			ex.getStackTrace();
			return "error";
		}

		//System.out.println("pg.setPageCount(pagecount);" + pg.getPageCount());
		// 通过删除重新进入界面时curpage应不初始化
		if (session.getAttribute("delete") != null)
		{
			try
			{
				pg.setCurPage(((PageParameter) session.getAttribute("pg")).getCurPage());
			} catch (Exception ex)
			{
				//System.out.println("pagecount4出错");
				ex.getStackTrace();
				return "error";
			}

		}
		session.setAttribute("pg", pg);
		return "pagecount_search_success";
	}

	// 添加学生
	public String add() throws Exception
	{
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s = new Students();
		try
		{
			s.setStuName(request.getParameter("stuname"));
			s.setSex(request.getParameter("sex"));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s.setBirthday(sdf.parse(request.getParameter("birthday")));
		} catch (Exception ex)
		{
			System.out.println("add1出错");
			ex.getStackTrace();
			return "error";
		}
		if (sdao.addStudents(s))
		{
			return "add_success";
		} else
		{
			return "error";
		}
	}

	// 删除学生
	public String delete() throws Exception
	{
		StudentsDAO sdao = new StudentsDAOImpl();
		try
		{
			String sid = request.getParameter("stuid");
			sdao.deleteStudents(sid);
			session.setAttribute("delete", true);
		} catch (Exception ex)
		{
			//System.out.println("delete1出错");
			ex.getStackTrace();
			return "error";
		}
		if (request.getParameter("search") == null)
		{
			//System.out.println("querydelete");
			return "delete_success";
		} else
		{
			//System.out.println("searchdelete");
			return "search_delete_success";
		}
	}

	// 获取要修改的学生信息
	public String modify() throws Exception
	{
		session.removeAttribute("searchupdate");
		session.removeAttribute("modify_students");
		// 获得传递过来的学生编号
		try
		{
			String stuid = request.getParameter("stuid");
			StudentsDAO sdao = new StudentsDAOImpl();
			Students s = sdao.queryStudentsBySid(stuid);
			// 将学生信息保存在session中
			session.setAttribute("modify_students", s);
		} catch (Exception ex)
		{
			//System.out.println("modify出错");
			ex.getStackTrace();
			return "error";
		}
		if(request.getParameter("searchupdate")!=null)
		{
			System.out.println("searchupdate不为空");
			session.setAttribute("searchupdate", 1);
		}
		System.out.println("modify_success");
		return "modify_success";
	}

	public String update() throws Exception
	{
	
		Students s = new Students();
		try
		{
			System.out.println("update");
			s.setStuID(request.getParameter("stuid"));
			s.setStuName(request.getParameter("stuname"));
			s.setSex(request.getParameter("sex"));
			
			// 更改birthday的类型String->yyyy-MM-dd
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s.setBirthday(sdf.parse(request.getParameter("birthday")));
		} catch (Exception ex)
		{
			System.out.println("update出错");
			ex.getStackTrace();
			return "error";
		}
		System.out.println("update2");
		StudentsDAO sdao = new StudentsDAOImpl();
		System.out.println("update3");
		if (sdao.updateStudents(s))
		{
			System.out.println("update4");
			return "update_success";	
			
		} else
		{
			return "error";
		}
	}

	public String searchquery() throws Exception
	{
		try
		{
			// 重置条件
			session.removeAttribute("searchquery");
			// 添加条件到session中
			SearchQuery sq = new SearchQuery();

			if (request.getParameter("stuid").length() > 0)
			{
				String stuid = request.getParameter("stuid");
				sq.setStuid(stuid);
			}

			if (request.getParameter("stuname").length() > 0)
			{
				String stuname = request.getParameter("stuname");
				sq.setStuname(stuname);

			}

			if (request.getParameter("sex").length() > 0)
			{
				String sex = request.getParameter("sex");
				sq.setSex(sex);

			}

			if (request.getParameter("birthday2").length() > 0)
			{
				String birthday2 = request.getParameter("birthday2");
				sq.setBirthday2(birthday2);

			}

			if (request.getParameter("birthday1").length() > 0)
			{
				String birthday1 = request.getParameter("birthday1");
				sq.setBirthday1(birthday1);

			}
			session.setAttribute("searchquery", sq);
		} catch (Exception ex)
		{
			System.out.println("querysearch出现错误");
			ex.getStackTrace();
			return "error";
		}
		return "search_query_success";
	}

	// 查询
	public String search() throws Exception
	{
		System.out.println("search");
		// 获取查询信息
		SearchQuery sq = new SearchQuery();
		PageParameter pg = new PageParameter();

		if ((SearchQuery) session.getAttribute("searchquery") != null)
		{
			sq = (SearchQuery) session.getAttribute("searchquery");
		}
		// 获取分页信息
		if ((PageParameter) session.getAttribute("pg") != null)
		{
			pg = (PageParameter) session.getAttribute("pg");
		}
		// 判断是否从删除界面过来的
		if (session.getAttribute("delete") != null)
		{
			// 清除标记
			session.removeAttribute("delete");

			try
			{
				if (session.getAttribute("scurpage").toString() != null)
				{
					pg.setCurPage(Integer.parseInt(session.getAttribute("scurpage").toString()));
				}
			} catch (Exception ex)
			{
				//System.out.println("search错误1");
				ex.getStackTrace();
				return "error";
			}
			if (pg.getCurPage() > pg.getPageCount())
			{

				pg.setCurPage(pg.getPageCount());

				session.setAttribute("scurpage", pg.getCurPage());

			}
			// 刷新delete后的表页面总数
			session.setAttribute("spagecount", pg.getPageCount());

		} else// 如果不是从删除界面过来的
		{
			if (request.getParameter("curpage") != null)
			{

				int curpage = 0;
				try
				{
					if (request.getParameter("curpage") != null)
					{
						curpage = Integer.parseInt(request.getParameter("curpage"));
					}
				} catch (Exception ex)
				{
					//System.out.println("search错误2");
					ex.getStackTrace();
					return "error";
				}
				// 更新当前页面
				if (curpage < 1)
				{

					return "search_success";
				} else if (curpage > pg.getPageCount())
				{

					return "search_success";
				} else
				{

					pg.setCurPage(curpage);

				}
			}
			session.setAttribute("scurpage", pg.getCurPage());
			session.setAttribute("spagecount", pg.getPageCount());
			session.setAttribute("pg", pg);
			System.out.println("进入开始准备查询工作");
		}
		// 开始准备查询

		StudentsDAO sdao = new StudentsDAOImpl();

		try
		{

			if (sdao.searchStudents(sq, pg.getCurPage()).size() > 0)
			{
				List<Students> list = sdao.searchStudents(sq, pg.getCurPage());
				System.out.println("list:" + list.toString());
				session.setAttribute("search_list", list);
			} else
			{
				System.out.println("search_listlist为空");
			}
		} catch (Exception ex)
		{
			System.out.println("search错误3");
			ex.getStackTrace();
			return "error";
		}
		System.out.println("pagecount" + pg.getPageCount());
		System.out.println("curpage" + pg.getCurPage());
		System.out.println("search_success");
		return "search_success";
	}
}
