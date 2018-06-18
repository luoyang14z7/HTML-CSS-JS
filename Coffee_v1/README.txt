目前商品列表做好了，碰到以下错误，提醒自己。

 @ResponseBody
 return 不会跳转地址。
 
 
 
List<Map<String, Object>> getshop = session.selectList("yang.dao.ShoplistMapper.selectall");
selectcall 是mapper.java指向xml的ID。

@RequestMapping(value="/getshoplist",produces = {"application/json;charset=UTF-8"})
改变网页header的编码问题。

  <select id="selectall"  parameterType="java.util.HashMap" resultType="map">
    select
    shopname,shopdes,shoppri,shopimg
    from shoplist
  </select>
 select 列出查询的字段，*效率低。resultType="map" 表明输出类型map。之前BaseResultMap的数据类型是model，会导致map数据格式不匹配。
 
 
 var datalist = eval('(' + 'date' + ')');
 eval后会将接受的数据String转换成可操作的函数。
 
 
 
 
 
用户登录 && 注册。
 
 注册会检测用户是否存在。
 
 登录成功后返还UID、用户名 将用户名、UID存入sessionStorage。
 
 
 
 添加购物车 && 结算 && 订单信息
 支付宝接口做好了，可以跳转支付宝付款，但回调参数没有进行处理。
 
 
