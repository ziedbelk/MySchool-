package com.journaldev.spring.dao;

import java.util.List;
import java.util.Map;

import com.journaldev.spring.model.Classe;
import com.journaldev.spring.model.PlanEtude;

public interface MyDAO {
	
public void addEntity(Object entity);
public void updateEntity(Object entity);
public List<Object> listEntities(Class<?>  objectClass);
public List<Object> listEntitiesByAttribute(Class<?>  objectClass,String Attribute,Object value) ;
public Object getEntityById(int id,Class<?>  objectClass);
public void removeEntity(int id,Class<?> objectClass);
public List<Object> listEntitiesByAttributes(Class<?>  objectClass ,Map<String,Object> map);
public Map<String,Long> levelStat(String anUnivCode);
public void assignPlanEtude(Classe classe,PlanEtude planEtude);
public void deleteEntitiesByAttribute(Class<?>  objectClass,String Attribute,Object value);

}
