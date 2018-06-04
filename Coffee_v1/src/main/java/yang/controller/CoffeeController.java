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
import yang.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller

public class CoffeeController {
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
            for (Map<String, Object> map : getshop) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("shopname", map.get("shopname"));
                jsonObject.put("shopdes", map.get("shopdes"));
                jsonObject.put("shoppri", map.get("shoppri"));
                jsonObject.put("shopimg", map.get("shopimg"));
                jsonArray.add(jsonObject);
            }
            return jsonArray.toString();
            } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();


        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value="/CheckUserName",produces = {"application/json;charset=UTF-8"})
    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String uphone = request.getParameter("uphone");


        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = new User();
            user.setUphone(uphone);
            int num = session.selectOne("yang.dao.UserMapper.checkuser", user);
            session.commit();
            boolean flag=true;
            if(num>0){
                flag=false;
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("flag", flag);
            String json = JSONObject.fromObject(map).toString();
            //将数据返回
            response.setCharacterEncoding("UTF-8");
            response.flushBuffer();
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }


        return null;
    }

    @ResponseBody
    @RequestMapping(value="/adduser",produces = {"application/json;charset=UTF-8"})
    public String adduser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String uphone = request.getParameter("uphone");
        String upwd = request.getParameter("upwd");



        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = new User();
            user.setUphone(uphone);
            user.setPassword(upwd);
            int num = session.insert("yang.dao.UserMapper.insert", user);
            session.commit();

            boolean flag=false;
            if(num>0){
                flag=true;
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("flag", flag);
            String json = JSONObject.fromObject(map).toString();
            //将数据返回
            response.setCharacterEncoding("UTF-8");
            response.flushBuffer();
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }


        return null;
    }

    @ResponseBody
    @RequestMapping(value="/userlogin",produces = {"application/json;charset=UTF-8"})
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String uphone = request.getParameter("uphone");
        String upwd = request.getParameter("upwd");



        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user = new User();
            user.setUphone(uphone);
            user.setPassword(upwd);
            Integer uid = session.selectOne("yang.dao.UserMapper.userlogin",user);
            boolean flag=false;
            if (uid != 0) {
                flag=true;
            }
            session.commit();
            System.out.println(uid);

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("flag", flag);
            map.put("uid", uid);
            String json = JSONObject.fromObject(map).toString();
            //将数据返回
            response.setCharacterEncoding("UTF-8");
            response.flushBuffer();
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }


        return null;
    }

}
