package yang.controller;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yang.dao.ShoplistMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;


@Controller

public class ShoplistController {
    private static SqlSessionFactory sqlSessionFactory;
    String resource = "mybatis-config.xml";
    Reader reader = null;
    private ShoplistMapper shoplistMapper;
    @ResponseBody
    @RequestMapping(value="/getshoplist",produces = {"application/json;charset=UTF-8"})
    public String getshoplist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {

            List<Map<String, Object>> getshop = session.selectList("yang.dao.ShoplistMapper.selectall");
            JSONArray jsonArray = new JSONArray();
            session.commit();
      /*      System.out.println(getshop.getClass().toString());
            for (int i = 0; i < getshop.size(); i++) {
                System.out.println(getshop.get(i));
            }*/
            for (Map<String, Object> map : getshop) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("shopname", map.get("shopname"));
                jsonObject.put("shopdes", map.get("shopdes"));
                jsonObject.put("shoppri", map.get("shoppri"));
                jsonObject.put("shopimg", map.get("shopimg"));
                jsonArray.add(jsonObject);
            }
/*            for (int i = 0; i < jsonArray.size(); i++) {
                System.out.println(jsonArray.get(i));
            }*/
            return jsonArray.toString();
            } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();


        }

        return null;
    }

}
