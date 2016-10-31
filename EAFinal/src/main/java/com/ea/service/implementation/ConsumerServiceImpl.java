package com.ea.service.implementation;

import com.ea.domain.Consumer;
import com.ea.repository.IConsumerRepository;
import com.ea.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConsumerServiceImpl implements IConsumerService  {
	
	@Autowired
	public IConsumerRepository consumerRepository;

	public void save(Consumer consumer) {
		consumerRepository.save(consumer);
	}

	public void update(Consumer consumer) {
		consumerRepository.save(consumer);
	}

	@Override
	public void delete(int id) {
		consumerRepository.delete(id);
	}

	@Override
	public void delete(Consumer consumer) {
		consumerRepository.delete(consumer);
	}

	@Override
	public List<Consumer> findAll() {
		return consumerRepository.findAll();
	}

	@Override
	public Consumer findConsumerById(Integer id) {
		return consumerRepository.findOne(id);
	}


}
