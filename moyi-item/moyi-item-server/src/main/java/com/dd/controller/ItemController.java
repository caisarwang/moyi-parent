package com.dd.controller;

import com.dd.enums.ExceptionEnum;
import com.dd.exception.CustomException;
import com.dd.pojo.Item;
import com.dd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 直接抛出异常方式提醒错误
     * @param id
     * @return
     */
    @PostMapping("getItem")
    public ResponseEntity<Item> getItem(Integer id) {
        if(id == null) {
            throw new RuntimeException("id不能为空");
        }
        Item item = itemService.getItem(id);
        return ResponseEntity.status(200).body(item);
    }

    /**
     * 抛出自定义异常方式
     * @param id
     * @return
     */
    @PostMapping("getItemTwo")
    public ResponseEntity<Item> getItemTwo(Integer id) {
        if(id == null) {
            throw new CustomException(ExceptionEnum.NO_ID_EXCEPTION);
        }
        Item item = itemService.getItem(id);
        return ResponseEntity.status(200).body(item);
    }
}
