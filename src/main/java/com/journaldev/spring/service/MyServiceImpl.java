package com.journaldev.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.PersonDAO;
import com.journaldev.spring.dao.MyDAO;

@Service
public class MyServiceImpl implements MyService{
	
	
	private MyDAO myDAO;

	public void setMyDAO(MyDAO myDAO) {
		this.myDAO = myDAO;
	}

	@Override
	@Transactional
	public void addEntity(Object p) {
		this.myDAO.addEntity(p);
	}

	@Override
	@Transactional
	public void updateEntity(Object p) {
		this.myDAO.updateEntity(p);
		
	}

	@Override
	@Transactional
	public List<Object> listEntities(Class<?> objectClass) {
		// TODO Auto-generated method stub
		return this.myDAO.listEntities(objectClass);
	}

	@Override
	@Transactional
	public Object getEntityById(int id, Class<?> objectClass) {
		// TODO Auto-generated method stub
		return this.myDAO.getEntityById(id, objectClass);
	}

	@Override
	@Transactional
	public void removeEntity(int id, Class<?> objectClass) {
		this.myDAO.removeEntity(id, objectClass);
		
	}

	@Override
	@Transactional
	public List<Object> listEntitiesByAttribute(Class<?> objectClass, String attribute, Object value) {
		return this.myDAO.listEntitiesByAttribute(objectClass, attribute, value);
	}

	@Override
	public List<Object> listEntitiesByAttributes(Class<?> objectClass, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.myDAO.listEntitiesByAttributes(objectClass, map);
	}

	@Override
	@Transactional
	public Map<String, Long> levelStat(String anUnivCode) {
		// TODO Auto-generated method stub
		return this.myDAO.levelStat(anUnivCode);
	}

	@Override
	@Transactional
	public void deleteEntitiesByAttribute(Class<?> objectClass, String Attribute, Object value) {
		this.myDAO.deleteEntitiesByAttribute(objectClass, Attribute, value);
	}

}
