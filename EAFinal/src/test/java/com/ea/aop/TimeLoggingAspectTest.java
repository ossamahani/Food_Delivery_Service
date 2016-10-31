package com.ea.aop;

import com.ea.config.AspectConfig;
import com.ea.service.IItemService;
import com.ea.service.implementation.ItemServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by amanadhikari on 9/27/16.
 */
@SpringBootTest
public class TimeLoggingAspectTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AspectConfig.class);
        ctx.refresh();
        IItemService itemService = ctx.getBean(ItemServiceImpl.class);
        itemService.findItemById(1);
    }
}
