package com.blog.common.dao;

import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;



public class HibernateCallbackByPageImpl implements HibernateCallback {
//	private Log log = LogFactory.getLog(HibernateCallbackByPageImpl.class); 
	private String hql;
	private PageBean pageObject;
	private Object[] param;
	private String[] nameForSetVar;
	private boolean isNativeSql = false;
	private int topNum=0;
	
	/**
	 * 
	 * @param hql
	 * @param pageObject
	 */
	public HibernateCallbackByPageImpl(String hql, PageBean pageObject) {
		this.hql = hql;
		this.pageObject = pageObject;		
	}
	
	public HibernateCallbackByPageImpl(String hql, PageBean pageObject,boolean isNativeSql) {
		this.hql = hql;
		this.pageObject = pageObject;		
		this.isNativeSql = isNativeSql;
	}

	public HibernateCallbackByPageImpl(String hql, PageBean pageObject,Object[] param) {	
		this.hql = hql;
		this.pageObject = pageObject;
		this.param = param;		
	}
	

	

	public HibernateCallbackByPageImpl(String hql, PageBean pageObject,Object[] param,String[] nameForSetVar) {	
		this.hql = hql;
		this.pageObject = pageObject;
		this.param = param;		
		this.nameForSetVar = nameForSetVar;
	}	
	
	

	public HibernateCallbackByPageImpl(String queryString, int topNum,
			Object[] value) {
		this.hql = queryString;
		this.topNum=topNum;
		this.param = value;	
	}

	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate.HibernateCallback#doInHibernate(net.sf.hibernate.Session)
	 */
	public Object doInHibernate(Session session) throws HibernateException, SQLException {
		Query selectQuery;
		if(isNativeSql){
			selectQuery = session.createSQLQuery(hql);
		}else{
			selectQuery = session.createQuery(hql);
		}
		if(param!=null){
			//log.debug("��ʼ���в�������");
			for (int i = 0; i < param.length; i++) {

				if(param[i] instanceof List){
					//log.debug("���õ�"+(i+1)+"�����?);
					selectQuery.setParameterList(nameForSetVar[i],(List)param[i]);
				}else{
					if(nameForSetVar==null){
						selectQuery.setParameter(i,param[i]);
					}else{
						selectQuery.setParameter(nameForSetVar[i],param[i]);
					}
				}
			}
		}
		if(this.pageObject!=null){
			if(hql.toLowerCase().indexOf("select")>=0){
				int pos = hql.toLowerCase().indexOf("from");
				hql = hql.substring(pos);
			}
			
			if(hql.toLowerCase().indexOf("order by")>=0){
				int pos=hql.toLowerCase().indexOf("order by");
				hql=hql.substring(0, pos);
			}
			
			String getTotolCountHql = "select count(*) "+hql;		
			Query countQuery;
			if(isNativeSql){
				countQuery = session.createSQLQuery(getTotolCountHql);	
			}else{
				countQuery = session.createQuery(getTotolCountHql);	
			}
			if(param!=null){
				for (int i = 0; i < param.length; i++) {
					if(param[i] instanceof List){
						countQuery.setParameterList(nameForSetVar[i],(List)param[i]);
					}else{
						if(nameForSetVar==null){
							countQuery.setParameter(i,param[i]);
						}else{
							countQuery.setParameter(nameForSetVar[i],param[i]);
						}
					}
				}
			}
			//��ü�¼����?
			if(null != countQuery.list() && countQuery.list().size() > 0 ){
    			if(isNativeSql){
    				pageObject.setTotalRecords(((BigDecimal)(countQuery.list().get(0))).intValue());
    			}else{
    				pageObject.setTotalRecords(((Long)countQuery.list().get(0)).intValue());
    			}
			}
			
			selectQuery.setFirstResult((int)(pageObject.getRsFirstNumber()-1));
			selectQuery.setMaxResults(pageObject.getLength());
		}
		else if(topNum!=0){
			selectQuery.setFirstResult(0);
			selectQuery.setMaxResults(topNum);
		}
		
		List result=selectQuery.list();
		
		if(pageObject!=null)
			pageObject.setResults(result);
		return result;
	}
}
