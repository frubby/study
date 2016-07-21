package com.frw.core;

import com.frw.dal.IOrdersDao;
import com.frw.dal.IProductDao;
import com.frw.dal.IUsersDao;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.util.Map;


/**
 * Created by fruwei on 2016/7/21.
 */
@Aspect
public class MultiDatasource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> currentDataSourceKey = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "users";
        }
    };


    @Before("execution(* com.frw.dal.*Dao.*(..))")
    public void before(JoinPoint joinPoint) {
        Class<?>[] targetInterfaces = ClassUtils.getAllInterfaces(joinPoint.getTarget());
        System.out.println("before " + targetInterfaces[0].getName());
        String daoName = targetInterfaces[0].getSimpleName().toLowerCase();
        if (daoName.contains("product")) {
            currentDataSourceKey.set("product");
        } else if (daoName.contains("orders")) {
            currentDataSourceKey.set("orders");
        } else if (daoName.contains("users")) {
            currentDataSourceKey.set("users");
        } else {
            currentDataSourceKey.set("users");
        }
        System.out.println("db route key :  " + currentDataSourceKey.get());
    }


    protected Object determineCurrentLookupKey() {
        return currentDataSourceKey.get();
    }
}
