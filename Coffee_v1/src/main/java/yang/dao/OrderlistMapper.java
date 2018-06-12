package yang.dao;

import yang.model.Orderlist;

import java.util.List;
import java.util.Map;

public interface OrderlistMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(Orderlist record);

    int insertSelective(Orderlist record);

    Orderlist selectByPrimaryKey(Integer oid);

    int updateByPrimaryKeySelective(Orderlist record);

    int updateByPrimaryKey(Orderlist record);

    List<Map<String, Object>> selectone(Integer uid);
}