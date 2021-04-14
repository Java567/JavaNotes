package com.mage.my.shop.web.admin.dao;


import com.mage.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbUserDao {

    /**
     * jdk1.8版本之后就不用写public
     * 查询用户表全部信息
     * @return
     */
    public List<TbUser> selectAll();



    /**
     * 新增
     * @param tbUser
     */
    void insert(TbUser tbUser);

    /**
     * 根据主键删除基本信息
     * @param id
     */
    void delete(Long id);

    /**
     * 根据主键查询基本信息
     * @param id
     */
    TbUser getById(Long id);

    /**
     * 更新
     * @param tbUser
     */
    void update(TbUser tbUser);

    /**
     * 根据用户名模糊查询
     * @param username
     * @return
     */
    List<TbUser> selectByUsername(String username);

    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    TbUser getByEmail(String email);

    List<TbUser> search(TbUser tbUser);
}
