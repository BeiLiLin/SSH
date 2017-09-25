package entity;

import org.junit.Test;

import po.PageParameter;

public class TestPageParameter
{
	@Test
	public void testPage(){
		PageParameter pg=new PageParameter();
		pg.setCurPage(1);
		int countpage=pg.getPageCount();
		System.out.println(pg.getCurPage());
		System.out.println(countpage);
	}
}
