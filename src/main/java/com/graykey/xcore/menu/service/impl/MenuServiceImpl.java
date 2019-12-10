package com.graykey.xcore.menu.service.impl;

import com.graykey.xcore.menu.dao.IMenuDao;
import com.graykey.xcore.menu.module.Menu;

import com.graykey.xcore.menu.service.IMenuService;
import com.graykey.xcore.menu.vo.MenuVo;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;


/**
 * 菜单配置	Service层实现
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Service("menuServiceImpl")
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuDao iMenuDao;


//    @Override
//    public Pager queryEntityList(Integer page, Integer limit, MenuVo menuVo) {
//        List<Criterion> criterionList = new ArrayList<>();
//
//        if (StringUtils.isNotBlank(menuVo.getDataAreaCode())) {
//            criterionList.add(Restrictions.like("dataAreaCode", menuVo.getDataAreaCode(), MatchMode.START));
//        }
//
//
//        return this.menuDaoImpl.queryEntityList(page, limit, criterionList, Order.desc("createTime"), Menu.class);
//    }
//    public void queryEntityList(Integer page, Integer limit, MenuVo menuVo) {
//
//    }

    public void queryEntityList(Integer page, Integer limit, MenuVo menuVo) {

//        QMenu qMenu = QMenu.menu;
//        qMenu.id.eq("123");
//        Predicate predicate = qMenu.menuName.like("hehe");

        PageRequest pageRequest = PageRequest.of(page, limit, Sort.Direction.DESC, "createTime");

        //Page<Menu> menuPage = iMenuDao.findAll(predicate, pageRequest);


        iMenuDao.findAll(new Predicate() {
            @Override
            public Predicate not() {
                return null;
            }

            @Nullable
            @Override
            public <R, C> R accept(Visitor<R, C> visitor, @Nullable C c) {
                return null;
            }

            @Override
            public Class<? extends Boolean> getType() {
                return null;
            }
        }, new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        });
    }


    @Override
    public Menu saveOrUpdate(MenuVo menuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        if (StringUtils.isBlank(menu.getId())) {
            //this.save(menu);
            iMenuDao.save(menu);
        } else {
            //menu = gainModuleValue(menu, Menu.class, menu.getId());
            //menuDaoImpl.updateEntity(menu);
            iMenuDao.save(menu);
        }
        return menu;
    }

    @Override
    public void delete(String ids) {
        String[] idz = ids.split(",");
        for (int i = 0; i < idz.length; i++) {
            //Menu menu = this.getEntityById(Menu.class, idz[i]);
            //this.delete(menu);
            Menu menu = iMenuDao.getOne(idz[i]);
            iMenuDao.delete(menu);
        }
    }


}