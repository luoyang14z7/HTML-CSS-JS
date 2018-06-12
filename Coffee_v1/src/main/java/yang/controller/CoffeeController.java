package yang.controller;


import alipay.config.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import yang.model.Orderlist;
import yang.model.Shopcart;
import yang.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Controller

public class CoffeeController {
    private static SqlSessionFactory sqlSessionFactory;
    String resource = "mybatis-config.xml";
    Reader reader = null;
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
                jsonObject.put("SID", map.get("SID"));
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

    @ResponseBody
    @RequestMapping(value="/addshopcart",produces = {"application/json;charset=UTF-8"})
    public String addshopcart(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        Integer SID = Integer.valueOf(request.getParameter("SID"));
        Integer shoppri = Integer.valueOf(request.getParameter("shoppri"));
        Integer shopnum = Integer.valueOf(request.getParameter("shopnum"));





        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Shopcart shopcart = new Shopcart();
            shopcart.setUid(uid);
            shopcart.setSid(SID);
            shopcart.setShoppri(shoppri);
            shopcart.setNum(shopnum);
            int num = session.insert("yang.dao.ShopcartMapper.insert",shopcart);
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
    @RequestMapping(value="/getcartlist",produces = {"application/json;charset=UTF-8"})
    public String getcartlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession session = sqlSessionFactory.openSession();
        try {
            List<Map<String, Object>> getcart = session.selectList("yang.dao.ShopcartMapper.selectcart",uid);
            JSONArray jsonArray = new JSONArray();
            session.commit();
            for (Map<String, Object> map : getcart) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("sid", map.get("SID"));
                jsonObject.put("shopimg", map.get("shopimg"));
                jsonObject.put("shopname", map.get("shopname"));
                jsonObject.put("shoppri", map.get("shoppri"));
               jsonObject.put("shopnum", map.get("num"));
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
    @RequestMapping(value="/deletclick",produces = {"application/json;charset=UTF-8"})
    public String deletclick(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        Integer sid = Integer.valueOf(request.getParameter("sid"));

        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Shopcart shopcart = new Shopcart();
            shopcart.setUid(uid);
            shopcart.setSid(sid);
            session.delete("yang.dao.ShopcartMapper.deleteclick", shopcart);
            session.commit();
            String json = "1";
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
    @RequestMapping(value="/jiaclick",produces = {"application/json;charset=UTF-8"})
    public String jiaclick(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        Integer sid = Integer.valueOf(request.getParameter("sid"));

        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Shopcart shopcart = new Shopcart();
            shopcart.setUid(uid);
            shopcart.setSid(sid);
            session.update("yang.dao.ShopcartMapper.jiaclick", shopcart);
            session.commit();
            String json = "1";
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
    @RequestMapping(value="/jianclick",produces = {"application/json;charset=UTF-8"})
    public String jianclick(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        Integer num = Integer.valueOf(request.getParameter("num"));

        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Shopcart shopcart = new Shopcart();
            shopcart.setUid(uid);
            shopcart.setSid(sid);
            if (num == 1) {
                session.delete("yang.dao.ShopcartMapper.deleteclick", shopcart);
            }
            session.update("yang.dao.ShopcartMapper.jianclick", shopcart);
            session.commit();
            String json = "1";
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
    @RequestMapping(value="/putorder",produces = {"application/json;charset=UTF-8"})
    public ModelAndView putorder(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        String payname = request.getParameter("payname");
        String payphone = request.getParameter("payphone");
        String payaddress = request.getParameter("payaddress");
        Integer paypri = Integer.valueOf(request.getParameter("paypri"));
        String paychose = request.getParameter("paychose");
        Integer paydata = Integer.valueOf(request.getParameter("paydata"));



        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {





            Orderlist orderlist = new Orderlist();
            orderlist.setUid(uid);
            orderlist.setOaddress(payaddress);
            orderlist.setOname(payname);
            orderlist.setOphone(payphone);
            orderlist.setOpay(paychose);
            orderlist.setOstat("正在处理中.....");
            orderlist.setOdate(paydata);
            orderlist.setPaypri(paypri);

            int num=session.insert("yang.dao.OrderlistMapper.insert", orderlist);
            session.commit();
            if (num>0) {
                session.delete("yang.dao.ShopcartMapper.deletecart", uid);
                session.commit();
            }
            if (paychose.equals("支付宝")) {
                AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
                AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
                alipayRequest.setReturnUrl(AlipayConfig.return_url);
                alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
                Random rand = new Random();
                String out_trade_no = String.valueOf(rand.nextInt(500));
                String total_amount = paypri.toString();
                String subject = "coffee";
                alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                        + "\"total_amount\":\""+ total_amount +"\","
                        + "\"subject\":\""+ subject +"\","
                        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
                String result = alipayClient.pageExecute(alipayRequest).getBody();
                System.out.println(result);

                response.setContentType("text/html;charset=" + AlipayConfig.charset);
                response.getWriter().write(result);//直接将完整的表单html输出到页面
                response.getWriter().flush();
                response.getWriter().close();
                System.out.println("11111111111111111111111111111111111111111111111111111111111111111111111");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }






        return null;
    }

    @ResponseBody
    @RequestMapping(value="/getorderlist",produces = {"application/json;charset=UTF-8"})
    public String getorderlist(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer uid = Integer.valueOf(request.getParameter("uid"));
        try {
            reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Orderlist orderlist = new Orderlist();
            orderlist.setUid(uid);
            List<Map<String, Object>> getorder = session.selectList("yang.dao.OrderlistMapper.selectone",uid);
            JSONArray jsonArray = new JSONArray();
            session.commit();
            for (Map<String, Object> map : getorder) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("oid", map.get("oid"));
                jsonObject.put("oname", map.get("oname"));
                jsonObject.put("oaddress", map.get("oaddress"));
                jsonObject.put("ophone", map.get("ophone"));
                jsonObject.put("opay", map.get("opay"));
                jsonObject.put("odata", map.get("odate"));
                jsonObject.put("ostat", map.get("ostat"));
                jsonObject.put("paypri", map.get("paypri"));
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
}
