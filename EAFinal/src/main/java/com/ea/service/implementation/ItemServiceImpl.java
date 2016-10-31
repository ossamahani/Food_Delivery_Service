package com.ea.service.implementation;

import com.ea.domain.Item;
import com.ea.repository.ItemRepository;
import com.ea.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amanadhikari on 9/24/16.
 */
@Service
@Transactional
public class ItemServiceImpl implements IItemService {
    @Autowired
    ItemRepository itemRepository;

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public void delete(Integer id) {
        itemRepository.delete(id);
    }

    @Override
    public Item findItemById(Integer id) {
        return itemRepository.findOne(id);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findAllByBusiness(Integer businessId) {
        return itemRepository.findAllByBusiness(businessId);
    }

    @Override
    public List<Item> findAllByName(String name) {
        return itemRepository.findAllByName(name);
    }
}
