package com.journaldev.spring.service;

import java.util.List;
import java.util.Map;


public interface MyService {
	public void addEntity(Object entity);
	public void updateEntity(Object p);
	public List<Object> listEntities(Class<?> objectClass);
	public List<Object> listEntitiesByAttribute(Class<?> objectClass,String attribute,Object value);
	public List<Object> listEntitiesByAttributes(Class<?> objectClass,Map<String,Object> map);
	public Object getEntityById(int id,Class<?> objectClass);
	public void removeEntity(int id,Class<?> objectClass);
	public Map<String,Long> levelStat(String anUnivCode);
	public void deleteEntitiesByAttribute(Class<?>  objectClass,String Attribute,Object value);
}
