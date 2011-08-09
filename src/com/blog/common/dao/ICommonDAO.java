package com.blog.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;


/**
 * dao公共类，为了封装Hibernate的具体实现
 * @author liugang
 *
 */
public interface ICommonDAO {
	/**
	 * 保存对象
	 * @param obj
	 */
	public void save(Object obj);
	/**
	 * 更新对象
	 * @param obj
	 */
	public void update(Object obj);
	/**
	 * 删除对象
	 * @param obj
	 */
	public void delete(Object obj);
	/**
	 * 按hql查询
	 * @param queryString hql语句
	 * @return
	 */
	public List find(String queryString);
	/**
	 * 按Hql查询，带可变参数
	 * @param queryString hql语句，可变参数用?表示
	 * @param value 参数值
	 * @return
	 */
	public List find(String queryString,Object... value);
	/**
	 * 根据id读取对象
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object get(Class entityClass,Serializable id);
	/**
	 * 根据id读取对象
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object load(Class entityClass,Serializable id);
	/**
	 * 分页查询
	 * @param queryString hql语句，可变参数用？表示
	 * @param pageObj 分页对象，包含查询结果
	 * @param values 参数值
	 * @return
	 */
	public void find(String queryString, PageBean pageObj, Object ... values);	
	
	/**
	 * 查询，可变参数用命名参数表示，形式为<:参数名>
	 * @param queryString hql语句
	 * @param paramNames 命名参数数组
	 * @param values 参数值数组
	 * @return 查询结果
	 */
	public List find(String queryString,String[] paramNames,Object[] values);
	
	/**
	 * 分页查询，可变参数用命名参数表示，形式为<:参数名>
	 * @param queryString
	 * @param pageObj
	 * @param paramNames
	 * @param values
	 * @return
	 */
	public void find(String queryString,PageBean pageObj,String[] paramNames,Object[] values);
	
	/**
	 * 从数据库读取数据更新持久化对象状态
	 * @param obj
	 */
	public void refresh(Object obj);
	/**
	 * 按hql删除对象
	 * @param queryString hql语句，可变参数用？表示
	 * @param value 参数值
	 */
	public void deleteAll(String queryString,Object...value  );
	/**
	 * 按hql更新对象
	 * @param queryString hql语句，可变参数用？表示
	 * @param value 参数值
	 */
	public void bulkUpdate(String queryString,Object... value);
	
	/**
	 * 初始化持久化对象
	 * @param obj
	 */
	public void initialize(Object obj);
	
	/**
	 * 将某对象移出session
	 * @param obj
	 */
	public void evict(Object obj);
	
	/**
	 * 将某列表移出session
	 * @param list
	 */
	public void evictList(List list);
	
	/**
	 * 将缓存的对象保存到数据库中
	 *
	 */
	public void flush();
	
	/**
	 * 清除缓存和取消未提交的插入、更新和删除操作
	 *
	 */
	public void clear();
	
//	/**
//	 * 执行原生sql语句
//	 */
//	public Object executeMySQL(final String sql,int length);
	
	/**
	 * 执行原生sql
	 */
	public void executeNativeSql(String sql,PageBean pageBean);
	
	/**
	 * 查找前多少条信息，和分页方法的区别是不计算总条数
	 * @param queryString
	 * @param num
	 * @param value
	 * @return
	 */
	public List findTop(String queryString,int topNum,Object...value);
	
	/**
	 * 获取session
	 */
	public Session getSessions();
	
	public HibernateTemplate getHibertemplate();
}
