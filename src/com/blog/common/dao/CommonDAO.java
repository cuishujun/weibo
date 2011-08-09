package com.blog.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



public class CommonDAO extends HibernateDaoSupport implements ICommonDAO {

	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);

	}

	public List find(String queryString) {
		return getHibernateTemplate().find(queryString);
	}

	public List find(String queryString, Object... value) {
		return getHibernateTemplate().find(queryString,value);
	}

	public void save(Object obj) {
		getHibernateTemplate().save(obj);

	}

	public void update(Object obj) {
		getHibernateTemplate().update(obj);
		
	}

	public Object get(Class entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	public Object load(Class entityClass, Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}
	
    public void find(String queryString, PageBean pageObj, Object ... values) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(queryString,pageObj,values));
    }

	public void refresh(Object obj) {
		getHibernateTemplate().refresh(obj);
		
	}

	public void deleteAll(String queryString,Object...value) {
		getHibernateTemplate().deleteAll(find(queryString,value));
		
	}

	public void bulkUpdate(String queryString, Object... value) {
		getHibernateTemplate().bulkUpdate(queryString, value);
		
	}

	public List find(String queryString, String[] paramNames, Object[] values) {
		return getHibernateTemplate().findByNamedParam(queryString,paramNames,values);
		
	}

	public void find(String queryString, PageBean pageObj, String[] paramNames, Object[] values) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(queryString,pageObj,values,paramNames));
	}

	public void initialize(Object obj) {
		getHibernateTemplate().initialize(obj);
		
	}

	public void evict(Object obj) {
		getHibernateTemplate().evict(obj);
		
	}

	public void flush() {
		getHibernateTemplate().flush();
		
	}

	public void evictList(List list) {
		for (Object object : list) {
			evict(object);
		}
		
	}

	public void clear() {
		getHibernateTemplate().clear();
		
	}
	
	public void executeNativeSql(String sql,PageBean pageBean) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(sql,pageBean,true));;
	}

	@Override
	public List findTop(String queryString, int topNum, Object... value) {
		return getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(queryString,topNum,value));
	}

	@Override
	public Session getSessions() {
		return getSession();
	}

	@Override
	public HibernateTemplate getHibertemplate() {
		return getHibernateTemplate();
	}
	
/*
      //执行原生的sql语句取的�?���?
	public Object executeMySQL(final String sql,final int length,final int cols) {
		return getHibernateTemplate().execute( new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
			ResultSet rs=null;
		    // To store all records of 'emp' as individual array elements.    
		    Set rowArray = new LinkedHashSet();
            
			Connection CurConn = session.connection();
			Statement ps = CurConn.prepareStatement(sql);
			rs=ps.executeQuery(sql);
			while(rs.next())
			{   
				if(rowArray.size()>=length)
					{
						ps.close();
						session.flush();
						return rowArray;
					}
				String[] temp = new String[cols];
				for(int i=0;i<cols;i++){
					temp[i] = rs.getString(i+1);
				}
				rowArray.add(temp);
			}
			ps.close();
			session.flush();
			return rowArray;
			}
		} );
	}
	*/
}
