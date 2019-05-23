package com.dd.service.impl;

import com.dd.pojo.Item;
import com.dd.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Override
    public Item getItem(int id) {
        Item item = new Item();
        item.setName("手机");
        item.setId("1");
        return item;
    }
}
