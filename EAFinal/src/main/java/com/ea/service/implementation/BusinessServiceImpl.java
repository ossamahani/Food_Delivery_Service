package com.ea.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ea.domain.Business;
import com.ea.repository.IBusinessRepository;
import com.ea.service.IBusinessService;

import java.util.List;

@Service
@Transactional
public class BusinessServiceImpl implements IBusinessService  {
	
	@Autowired
	public IBusinessRepository businessRepository;

	public void save(Business business) {
		businessRepository.save(business);
	}

	public void update(Business business) {
		businessRepository.save(business);
	}

	@Override
	public void delete(int id) {
		businessRepository.delete(id);
	}

	@Override
	public void delete(Business business) {
		businessRepository.delete(business);
	}

	@Override
	public List<Business> findAll() {
		return businessRepository.findAll();
	}

	@Override
	public Business findBusinessById(Integer id) {
		return businessRepository.findOne(id);
	}


}
