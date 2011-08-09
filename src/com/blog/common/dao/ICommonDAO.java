package com.blog.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;


/**
 * dao�����࣬Ϊ�˷�װHibernate�ľ���ʵ��
 * @author liugang
 *
 */
public interface ICommonDAO {
	/**
	 * �������
	 * @param obj
	 */
	public void save(Object obj);
	/**
	 * ���¶���
	 * @param obj
	 */
	public void update(Object obj);
	/**
	 * ɾ������
	 * @param obj
	 */
	public void delete(Object obj);
	/**
	 * ��hql��ѯ
	 * @param queryString hql���
	 * @return
	 */
	public List find(String queryString);
	/**
	 * ��Hql��ѯ�����ɱ����
	 * @param queryString hql��䣬�ɱ������?��ʾ
	 * @param value ����ֵ
	 * @return
	 */
	public List find(String queryString,Object... value);
	/**
	 * ����id��ȡ����
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object get(Class entityClass,Serializable id);
	/**
	 * ����id��ȡ����
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object load(Class entityClass,Serializable id);
	/**
	 * ��ҳ��ѯ
	 * @param queryString hql��䣬�ɱ�����ã���ʾ
	 * @param pageObj ��ҳ���󣬰�����ѯ���
	 * @param values ����ֵ
	 * @return
	 */
	public void find(String queryString, PageBean pageObj, Object ... values);	
	
	/**
	 * ��ѯ���ɱ����������������ʾ����ʽΪ<:������>
	 * @param queryString hql���
	 * @param paramNames ������������
	 * @param values ����ֵ����
	 * @return ��ѯ���
	 */
	public List find(String queryString,String[] paramNames,Object[] values);
	
	/**
	 * ��ҳ��ѯ���ɱ����������������ʾ����ʽΪ<:������>
	 * @param queryString
	 * @param pageObj
	 * @param paramNames
	 * @param values
	 * @return
	 */
	public void find(String queryString,PageBean pageObj,String[] paramNames,Object[] values);
	
	/**
	 * �����ݿ��ȡ���ݸ��³־û�����״̬
	 * @param obj
	 */
	public void refresh(Object obj);
	/**
	 * ��hqlɾ������
	 * @param queryString hql��䣬�ɱ�����ã���ʾ
	 * @param value ����ֵ
	 */
	public void deleteAll(String queryString,Object...value  );
	/**
	 * ��hql���¶���
	 * @param queryString hql��䣬�ɱ�����ã���ʾ
	 * @param value ����ֵ
	 */
	public void bulkUpdate(String queryString,Object... value);
	
	/**
	 * ��ʼ���־û�����
	 * @param obj
	 */
	public void initialize(Object obj);
	
	/**
	 * ��ĳ�����Ƴ�session
	 * @param obj
	 */
	public void evict(Object obj);
	
	/**
	 * ��ĳ�б��Ƴ�session
	 * @param list
	 */
	public void evictList(List list);
	
	/**
	 * ������Ķ��󱣴浽���ݿ���
	 *
	 */
	public void flush();
	
	/**
	 * ��������ȡ��δ�ύ�Ĳ��롢���º�ɾ������
	 *
	 */
	public void clear();
	
//	/**
//	 * ִ��ԭ��sql���
//	 */
//	public Object executeMySQL(final String sql,int length);
	
	/**
	 * ִ��ԭ��sql
	 */
	public void executeNativeSql(String sql,PageBean pageBean);
	
	/**
	 * ����ǰ��������Ϣ���ͷ�ҳ�����������ǲ�����������
	 * @param queryString
	 * @param num
	 * @param value
	 * @return
	 */
	public List findTop(String queryString,int topNum,Object...value);
	
	/**
	 * ��ȡsession
	 */
	public Session getSessions();
	
	public HibernateTemplate getHibertemplate();
}
