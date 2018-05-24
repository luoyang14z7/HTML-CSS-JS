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
 
 
 
 
 
 
 接下来做用户登录 && 注册。
 
 
 
 
 
