package com.journaldev.spring.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.Etudiant;
import com.journaldev.spring.model.PlanEtude;

@Repository
public class MyDAOImpl implements MyDAO{

	
	private static final Logger logger = LoggerFactory.getLogger(MyDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	public SessionFactory getSessionFactory(){
		return this.sessionFactory ;
	}
	@Override
	public void addEntity(Object entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(entity);
		logger.info("Person saved successfully, Object Details="+entity);
	}
	@Override
	public void updateEntity(Object entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(entity);
		logger.info("Person updated successfully, entity Details="+entity);
	}
	@Override
	public List<Object> listEntities(Class<?>  objectClass) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Object> personsList = session.createQuery("from "+objectClass.getName()).list();
		for(Object p : personsList){
			logger.info("Entity List::"+p);
		}
		return personsList;
	}
	
	@Override
	public List<Object> listEntitiesByAttribute(Class<?>  objectClass,String Attribute,Object value) {
		Session session = this.sessionFactory.getCurrentSession();
		String x="";
		if(value instanceof Integer)
			x=String.valueOf(value);
		else if(value instanceof String)
			x="'"+String.valueOf(value)+"'";
		else if(value instanceof Boolean)
			x=Boolean.toString((Boolean)value).toUpperCase();
		List<Object> personsList = session.createQuery("select au from "+objectClass.getName()+" au where au."+Attribute+" ="+x)
	           // .setParameter(Attribute, value)
	            .list();
		if (personsList!=null)
		for(Object p : personsList){
			logger.info("Entity List::"+p);
		}
		return personsList;
	}
	
	@Override
	public void deleteEntitiesByAttribute(Class<?>  objectClass,String Attribute,Object value) {
		Session session = this.sessionFactory.getCurrentSession();
		String x="";
		if(value instanceof Integer)
			x=String.valueOf(value);
		else if(value instanceof String)
			x="'"+String.valueOf(value)+"'";
		session.createQuery("delete au  "+objectClass.getName()+" au where au."+Attribute+" ="+x)
	            .executeUpdate();
	}
	
	@Override
	public List<Object> listEntitiesByAttributes(Class<?>  objectClass ,Map<String,Object> map) {
		Session session = this.sessionFactory.getCurrentSession();
		String x="";
		for (Map.Entry<String, Object> entry : map.entrySet())
		{
			if(!x.isEmpty())
				x+=" AND ";
			x+="au."+entry.getKey()+" = ";
			if(entry.getValue() instanceof Integer  )
				x+=String.valueOf(entry.getValue());
			else if(entry.getValue() instanceof String || entry.getValue() instanceof  Date )
				x+="'"+String.valueOf(entry.getValue())+"'";
		}
		List<Object> personsList = session.createQuery("select au from "+objectClass.getName()+" au where "+x)
	           // .setParameter(Attribute, value)
	            .list();
		if (personsList!=null)
		for(Object p : personsList){
			logger.info("Entity List::"+p);
		}
		return personsList;
	}
	
	@Override
	public Object getEntityById(int id,Class<?>  objectClass) {
		Session session = this.sessionFactory.getCurrentSession();		
		Object p =  session.load(objectClass, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}
	
	@Override
	public void removeEntity(int id,Class<?> objectClass) {
	    Session session = this.sessionFactory.getCurrentSession();
	    Object p = session.load(objectClass, new Integer(id));
	    if(null != p){
		session.delete(p);
    	}
	   logger.info("Object deleted successfully, person details="+p);
		
	}
	
	@Override
	public Map<String,Long> levelStat(String anUnivCode)
	{
		Map<String,Long> myMap=new HashMap<String,Long>();
		Session session = this.sessionFactory.getCurrentSession();	
		Criteria cr = session.createCriteria(Classe.class,"classe");
		if(anUnivCode!=null)
		{
			cr.createAlias("classe.anneeUniversitaire", "anneeUniversitaire");
			cr.add(Restrictions.eq("anneeUniversitaire.code", anUnivCode));
		}
		cr.setProjection(Projections.distinct(Projections.property("niveau")));
		List<Integer> list=cr.list();
		for(Integer level:list)
		{
			Criteria cr2 = session.createCriteria(Etudiant.class,"etudiant");
			cr2.createAlias("etudiant.classe", "classe"); // inner join by default
			cr2.add(Restrictions.eq("classe.niveau", level));
			cr2.setProjection(Projections.rowCount());
			List<Long> list2=cr2.list();
			if(!list2.isEmpty())
			   myMap.put(level.toString(), list2.get(0));
		}
		return myMap;
	}
	
//	@Override
//	public Map<String,Long> levelStatAbsences(String anUnivCode)
//	{
//		Map<String,Long> myMap=new HashMap<String,Long>();
//		Session session = this.sessionFactory.getCurrentSession();	
//		Criteria cr = session.createCriteria(Classe.class,"classe");
//		if(anUnivCode!=null)
//		{
//			cr.createAlias("classe.anneeUniversitaire", "anneeUniversitaire");
//			cr.add(Restrictions.eq("anneeUniversitaire.code", anUnivCode));
//		}
//		cr.setProjection(Projections.distinct(Projections.property("niveau")));
//		List<Integer> list=cr.list();
//		for(Integer level:list)
//		{
//			Criteria cr2 = session.createCriteria(Etudiant.class,"etudiant");
//			cr2.createAlias("etudiant.classe", "classe"); // inner join by default
//			cr2.add(Restrictions.eq("classe.niveau", level));
//			cr2.setProjection(Projections.rowCount());
//			List<Long> list2=cr2.list();
//			if(!list2.isEmpty())
//			   myMap.put(level.toString(), list2.get(0));
//		}
//		return myMap;
//	}
	
	@Override
	public void assignPlanEtude(Classe classe, PlanEtude planEtude) {
		// TODO Auto-generated method stub
		
	}

	

}
