package com.mage.my.shop.web.admin.service;

import com.mage.my.shop.commons.dto.BaseResult;
import com.mage.my.shop.domain.TbUser;


import java.util.List;


public interface TbUserService {

    public List<TbUser> selectAll();

    public BaseResult save(TbUser tbUser);

    public void delete(Long id);

    public TbUser getById(Long id);

    public void update(TbUser tbUser);

    public List<TbUser> selectByUsername(String username);

    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email,String password);


    /**
     * 搜索功能
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);

}
