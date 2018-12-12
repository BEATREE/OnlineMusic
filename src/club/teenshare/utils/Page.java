package club.teenshare.utils;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private List bigList;//分页数据集
	private List smallList;//返回实际的数据集
	private int currentPage;//当前页
	private int pageRow;//每页行数
	private int pageTotal;//总页数
	private int nextPage;//下一页
	private int upPage;//上一页
	private boolean isFirstPage;//是否为首页
	private boolean isLastPage;//是否尾页
	private int totalRow;//查询到的总记录数
	public boolean getIsFirstPage() {
		if(currentPage==1){
			isFirstPage=true;
		}
		return isFirstPage;
	}
	public boolean getIsLastPage() {
		this.getPageTotal();
		if(currentPage==pageTotal){
			isLastPage=true;
		}
		return isLastPage;
	}
	public int getNextPage() {
		this.nextPage=currentPage+1;
		return nextPage;
	}
	public int getUpPage() {
		this.upPage=currentPage-1;
		return upPage;
	}
	public int getPageTotal() {
		this.pageTotal=bigList.size()/pageRow;
		if(bigList.size()%pageRow!=0){
			this.pageTotal++;
		}
		return pageTotal;
	}
	public List getSmallList() {
		smallList=new ArrayList();
		for (int i=(currentPage-1)*pageRow;i<=currentPage*pageRow-1&&i<bigList.size();i++) {
			smallList.add(bigList.get(i));
		}
		return smallList;
	}
	public void setBigList(List bigList) {
		this.bigList = bigList;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}
	public int getTotalRow() {
		return this.bigList.size();
	}
	public int getPageRow() {
		return pageRow;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
}