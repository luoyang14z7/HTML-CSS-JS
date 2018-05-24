package yang.dao;

import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import yang.model.Shoplist;

import java.util.List;
import java.util.Map;

public interface ShoplistMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Shoplist record);

    int insertSelective(Shoplist record);

    Shoplist selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Shoplist record);

    int updateByPrimaryKey(Shoplist record);

     List<Map<String, Object>> selectall();
}