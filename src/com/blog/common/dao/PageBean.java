package com.blog.common.dao;

import java.util.List;

/**
 * ����ҳ�󣬲����ķ�ҳbean����¼�����з�ҳ�Ͳ�ѯ�������Ϣ��
 */
public class PageBean{
    /**
     * ��ǰҳ��
     */
    private int currentPage;
    /**
     * ÿҳ��¼��
     */
    private int length;
    /**
     * ��ҳ��
     */
    private int totalPages;
    /**
     * �ܼ�¼��
     */
    private int totalRecords;
    /**
     * ���
     */
    private List results;
    
    
    
    
    public PageBean() {
		super();
	}
    
    public PageBean(int currentPage,int length){
    	this.currentPage=currentPage;
    	this.length=length;
    }
    
    public PageBean(int length){
    	this.length=length;
    }
    
	public List getResults() {
        return results;
    }
    public void setResults(List results) {
        this.results = results;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public int getTotalRecords() {
        return totalRecords;
    }
    public void setTotalRecords(int count) {
        totalRecords = count;
        if(length!=0){
        	totalPages = totalRecords / length;
            if (totalRecords % length != 0) {
                totalPages++;
            }

        }
        
    }
    
    /**
     * @return
     */
    public boolean canToFirst() {
        return currentPage > 1;
    }

    /**
     * @return
     */
    public boolean canToLast() {
        return currentPage < totalPages;
    }

    /**
     * @return
     */
    public boolean canToNext() {
        return currentPage < totalPages;
    }

    /**
     * @return
     */
    public boolean canToPre() {
        return currentPage > 1;
    }

    /**
     * ��ʼ��¼�š�
     * @return
     */
    public int getRsFirstNumber() {
    	if(currentPage==0)
    		return 1;
        return (currentPage - 1) * length + 1;
    }

    public int getRsLastNumber() {
        return currentPage * length;
    }
}
