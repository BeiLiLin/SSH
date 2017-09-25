package po;

public class PageParameter
{
	private int PageCount=1;//页面数
	private int PageSize=10;//页面数据的数量
	private int CurPage=1;//当前页面
	public PageParameter() {}
	
	public PageParameter(int pageCount, int pageSize, int curPage)
	{
		super();
		PageCount = pageCount;
		PageSize = pageSize;
		CurPage = curPage;
	}
	
	public int getPageSize()
	{
		return PageSize;
	}

	public void setPageSize(int pageSize)
	{
		PageSize = pageSize;
	}

	
	public int getPageCount()
	{
		return PageCount;
	}

	public void setPageCount(int pageCount)
	{
		PageCount = pageCount;
	}

	public int getCurPage(){		
		return CurPage;
	}
	public void setCurPage(int CurPage)
	{
		this.CurPage=CurPage;
	}
}
