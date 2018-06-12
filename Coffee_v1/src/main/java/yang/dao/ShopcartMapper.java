package yang.dao;

import org.omg.CORBA.INTERNAL;
import yang.model.Shopcart;


import java.util.List;
import java.util.Map;

public interface ShopcartMapper {
    int insert(Shopcart record);

    int insertSelective(Shopcart record);

    List<Map<String, Object>> selectcart(Integer uid);

    int deleteclick(Shopcart record);

    int jiaclick(Shopcart record);

    int jianclick(Shopcart record);

    int deletecart(Integer uid);

}