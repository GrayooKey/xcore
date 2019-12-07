package com.graykey.xcore.menu.service.impl;

import com.graykey.xcore.menu.service.IMenuService;
import org.springframework.stereotype.Service;


/**
 * 菜单配置	Service层实现
 *
 * @author xuezb
 * @date 2019-12-07
 */
@Service("menuServiceImpl")
public class MenuServiceImpl implements IMenuService {

    //@Autowired
    //private IMenuDao iMenuDao;


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
//
//
//    @Override
//    public Menu saveOrUpdate(MenuVo menuVo) {
//        Menu menu = new Menu();
//        BeanUtils.copyProperties(menuVo, menu);
//        if (StringUtils.isBlank(menu.getId())) {
//            //this.save(menu);
//            iMenuDao.save(menu);
//        } else {
//            //menu = gainModuleValue(menu, Menu.class, menu.getId());
//            //menuDaoImpl.updateEntity(menu);
//            iMenuDao.save(menu);
//        }
//        return menu;
//    }
//
//    @Override
//    public void delete(String ids) {
//        String[] idz = ids.split(",");
//        for (int i = 0; i < idz.length; i++) {
//            //Menu menu = this.getEntityById(Menu.class, idz[i]);
//            //this.delete(menu);
//            Menu menu = iMenuDao.getOne(idz[i]);
//            iMenuDao.delete(menu);
//        }
//    }


}