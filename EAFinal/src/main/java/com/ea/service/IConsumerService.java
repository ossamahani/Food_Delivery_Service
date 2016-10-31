/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ea.service;

import com.ea.domain.Consumer;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IConsumerService {

	public abstract void save(Consumer consumer);
	public void update(Consumer consumer);
	public void delete(int id);
	public abstract void delete(Consumer consumer);
	public List<Consumer> findAll();
	public Consumer findConsumerById(Integer id);
}
