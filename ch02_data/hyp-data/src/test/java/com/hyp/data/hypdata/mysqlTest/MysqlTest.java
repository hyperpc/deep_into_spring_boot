package com.hyp.data.hypdata.mysqlTest;


import com.hyp.data.hypdata.entity.*;
import com.hyp.data.hypdata.repository.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
//@EnableJpaRepositories(basePackages = "com.hyp.data.hypdata.repository")
@EnableJpaRepositories
@EntityScan(basePackages = "com.hyp.data.hypdata.entity")
public class MysqlTest {
    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        departmentRepository.deleteAll();
        roleRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.notNull(department.getId(), "Department.Id must not be null");

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId(), "Role.Id must not be null");

        User user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDepartment(department);

        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles, "Roles must not be null");
        user.setRoles(roles);

        userRepository.save(user);
        Assert.notNull(user.getId(), "User.Id must not be null");
    }

    @Test
    public void findPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.notNull(page, "Page result must not be null");
        for(User user : page.getContent()){
            logger.info("====user==== user name:{}, department name:{}, role name:{}", user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
        }
    }

    public void test(){
        User user1 = userRepository.findByNameLike("u%");
        Assert.notNull(user1, "user1 must not be null");
        
        User user2 = userRepository.findByNameLike("user");
        Assert.notNull(user2, "user2 must not be null");
        
        List<User> users = userRepository.getByCreatedateLessThan(new Date());
        Assert.notNull(users, "users must not be null");
    }
}
