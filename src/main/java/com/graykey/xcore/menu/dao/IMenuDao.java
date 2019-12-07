package com.graykey.xcore.menu.dao;


import com.graykey.xcore.menu.module.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 菜单配置	Dao层接口
 *
 * @author xuezb
 * @date 2019-12-05
 */
@Repository("iMenuDao")
public interface IMenuDao extends JpaRepository<Menu, String> {

}