package com.zzj.utils;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
	//总条数
	private Integer resultSize;
	//每页显示的条数
	private Integer pageSize;
	//总共多少页
	private Integer pageCount;
	//是否有上一页0:没有 1:有
	private Integer hasPrePage;
	//是否有下一页0:没有 1:有
	private Integer hasNextPage;
	//去往的页码(当前页码)
	private Integer skipToPage;
	/**
	 * 显示的页码数组
	 */
	private List<String> pageList = new ArrayList<String>();
	
	
	public PageBean(Integer resultSize, Integer skipToPage,Integer pageSize) {
		this.resultSize = resultSize;
		this.skipToPage = skipToPage;
		if(pageSize != null){
			this.pageSize = pageSize;
		}
		//获取总页码
		if(resultSize%pageSize == 0 ){
			this.pageCount =  resultSize/pageSize;
		}else{
			this.pageCount =  resultSize/pageSize+1;
		}
		//是否有上一页
		if(skipToPage <=1){
			this.hasPrePage = 0;
		}else{
			this.hasPrePage = 1;
		}
		//是否有下一页
		if(skipToPage >= pageCount){
			this.hasNextPage = 0;
		}else{
			this.hasNextPage = 1;
		}
		setPageList();
	}

	public void setPageList(List<String> pageList) {
		this.pageList = pageList;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<String> getPageList() {
		return pageList;
	}

	public void setPageList() {
		if(pageCount < 10){
       		for(int i = 0; i <pageCount; i++){
       			pageList.add(i,String.valueOf((i+1)));
       		}
       	}else {  
       		if(skipToPage > 5 && (pageCount - skipToPage) <5 ){
       			pageList.add(0, "1");
       			pageList.add(1, "2");
       			pageList.add(2, "...");
       			for(int i = 6,j =3; i >= 0; i--,j++){
       				pageList.add(j, String.valueOf(pageCount - i));
       			}
       		}else if(skipToPage <= 5 && (pageCount - skipToPage)>=5 ){
       			for(int i = 0; i < 7 ; i ++){
       				pageList.add(i,String.valueOf((i+1)));
       			}
       			pageList.add(7, "...");
       			pageList.add(8, String.valueOf(pageCount -1));
       			pageList.add(9, pageCount.toString());
       		}else if(skipToPage > 5 && (pageCount - skipToPage)>=5){
       			
       			pageList.add(0, "1");
       			pageList.add(1, "2");
       			pageList.add(2, "...");
       			
       			for(int i = 3,j = skipToPage - 2;i < 8;i++,j++ ){
       				pageList.add(i, String.valueOf(j));
       			}
       			pageList.add(8, "...");
       			pageList.add(9, String.valueOf(pageCount-1));
       			pageList.add(10, String.valueOf(pageCount));
       		}
       	}
	}

	public Integer getHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(Integer hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public Integer getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Integer hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public Integer getResultSize() {
		return resultSize;
	}

	public void setResultSize(Integer resultSize) {
		this.resultSize = resultSize;
	}

	public Integer getSkipToPage() {
		return skipToPage;
	}

	public void setSkipToPage(Integer skipToPage) {
		this.skipToPage = skipToPage;
	}
	
  
	public static void main(String[] args) {
		PageBean page = new PageBean(500,20,10);
		List<String> pageList = page.getPageList();
		for(String a :pageList){
			System.out.println(a);
		}
	}
}

