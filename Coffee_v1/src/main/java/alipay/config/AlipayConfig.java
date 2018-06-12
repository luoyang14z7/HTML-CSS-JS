package alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091400505643";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDdp30Lzv5eZbtfezugiDDj3QNMK44O8380pRiglFD4N5650IQAkjYrLOrj08DsRpK3g5euBmA4vnp9seuJXbin37h3D7yeBrCaMcyoWOoHktjlnWqPGk2f8j1PEmvLTMaK7KAMwCLtvTdhKSbz2PTO+Sl34kc2IkYd3hopsRY8CH6eAZcnA6+6IocLpcCyK+OsfOO5yfYGutYQ+eqTEncDDyOPODzoljswwg/VgdoGY7HLNx/97SN3jD0Yq4NNFHX+G/Eln17sfRLy6nJednIeuGeMctpDEmkBgE+Cz30cg2Dd6rTh4G/iDiIs+yK4cpsTxO+xK2FPFR3itLB5u1yZAgMBAAECggEAD83+g5YJ2y9D0gYWdVbgrY63ynmckoJYQqe2/tQ0hc5pefRdRTEnvaJhSUdzDSXnFkDC0vzPZZxnXYkPM2dEYGv6Wjr8BwSRGR2N6z8TyOB2qMC1tWh5unrqVjYJC6D40zUWIHKeyvbf+QSXK3H/2rY3d73huAFvoHeSxABFhpgudxQSuuuayoCp/DPGwfUFmduwzWP14CYyxwURs2a+/M+BFNqvxh2ax9cwNq+sbxB5t5lp60iF6IQ3jNioFlRUwYLNFyxa3/xUxlwYJhgyfDUZ9tybmuAjKnVuNsyPM0H4VjLix+QQ7Unyz0Dn/t1GCuKTS7FXnQPxNPRrqoyGgQKBgQD4UWmug2c2vIHf8N4yj29eDZI7nsT6fhmJBQvJ+aRMMKBAEtu216wbEck0e9OVwrbGgHe/eRQ/FCUySvFSsmPFmGX3df4akigiFm/eiNGEL7d0ZUQeQuOxCGO4/CKmIi6hXfSi7dJq9JGddo7D2rKLyK7SWYrMu2B1h77lAbqNUQKBgQDkguiPO+OYtD+bD0nGr6Zb8AiyPGUqpBJ8ef9KJ1kcAApQNcJUm6icZhT1GufkPpXsHBDBgEaqlxxQl+tVa0e4dYM2qSQj9SL9+KTzlaOVN9UrQK4ULK4kIu7cvrr5SCsGmUI4JjB80+jflg/wN7BXmRAwLEJWUvoOJbtOuuHoyQKBgGf2PdawcQ/7kCxfvcdRQgEQkX6glDuDe9Ff6XWgLOcxHIZ23sIIPaf/pEty5Dy5DqjAtGHXoWBfYMtTkQFcamUZzubx4XbiZicPWbrDcWR0TcxPcVtyCb3ek1GpTiBcA0XP/lIQW54n0qcoMdFRyJq5ZGfw0Smx8UqDkKp0NNRhAoGBALjlQ49CAjJ5Bk5M3IErdwxE+IRfYexSvbKCdBJoLC3/yliEZy1WleOWpbZ9gsyn518YhmZLiOJXSs3bXsvK9klGrFj6GwgNPbcKDU4a8jW264EH4NLqJC2RJnzuxxMVVdsM/wu3utwGCxN+tH7GUKVQcEzKQxLeKP5xgVF+3XQhAoGAbcuXi0arfiYNO31WdDJ8gz0kKIYxTelr4djEcedzrk++flcVrOYCixf+//mgPH+JLhFaKnd9wxmvKyzEIScRjnJN/n/Ftu7QF4wdY282ic1sUgmzlZIuEdpSI9NWjJ2hqgg/miUvrWqvJh83mhh1ami04CAFER9my1dL4q7LBoI=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqkUU/pDiOylS7K6K9ja0EXU6HNqwsADdRHvWVC1nWFPZhbqT3asG2+kUD21mm3/sDbvmJiCzueJvXoCE5a+BF3PV8pEIR69LIchg/5VvijdvCRbJZddB5smc8lrPJw51Pnr7vWahPLDaSliAkaBcXpC0cNMPXMouh/F98nUAVn2MlYfF7E2CxBzBi+JMqKXkk8FhxA6WBcDZ3eZqNJK5RgJRyM6ee6zsrZwNWaDidv39T0CmjNyeawutG4oZkoTIjd9Vh4Z6/IyB0ybhHVsafyboC/EN5g7HJfIsbKdYpCbIJSaWnEfsEgIveXR8helz1rWPNlEcRMTOcyEDs020cQIDAQAB\n";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/alipay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

