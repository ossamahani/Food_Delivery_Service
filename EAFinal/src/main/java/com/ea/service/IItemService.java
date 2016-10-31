package com.ea.service;

import com.ea.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amanadhikari on 9/24/16.
 */
@Service
public interface IItemService {
    public abstract Item save(Item item);
    public abstract Item update(Item item);
    public abstract void delete(Item item);
    public abstract void delete(Integer id);
    public abstract Item findItemById(Integer id);
    public abstract List<Item> findAll();
    public abstract List<Item> findAllByBusiness(Integer businessId);
    public abstract List<Item> findAllByName(String name);
    /*public abstract List<Item> findAllByStatus(String status);
    public abstract List<Item> findAllByResources(Resources resources);
    public abstract List<Item> findAllByLocation(Address address);
    public abstract Project uploadImage(String filePath, Item item);
    public abstract List<Item> findAllByDescriptionLike(String description);
    public abstract List<Item> findAllWithVolunteersOrderByTaskTime();*/
}
