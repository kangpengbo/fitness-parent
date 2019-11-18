package com.woniu.fitness.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author 康蓬勃
 * @create 2019/9/28
 * @since 1.0.0
 */
public class EmailUtil {
    //获取属性文件中的值,建议把配置的信息放到属性文件中,方便修改和获取
//    private static Properties properties = new Properties();
//  e
//    public static    String SMTPSERVER = properties.getProperty("smtp.server"); //从属性文件中获取值其中key为smtp.server
//    public static  String SMTPPORT = properties.getProperty("smtp.port");   //端口号 465  465  465   不是456
//    public  static  String ACCOUT = properties.getProperty("smtp.account");//账户名:我的是163账户,此账户必须在设置中开启授权码授权
//    public  static  String PWD = properties.getProperty("smtp.pwd");   //授权密码
//
//    public static String users = properties.getProperty("email.users");   //这里是发送给多个用户多个用户用都好分割xxx@xx.com,xxx@xx.com
    static String SMTPSERVER="smtp.163.com";
    static String SMTPPORT="465";
    static String ACCOUT="heyrae233@163.com";
    static String PWD="zhm111";
    //static String users="1171849689@qq.com";
    public static void  sendEmail(String users,String content){
        try {
            // 创建邮件配置
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", " smtp.163.com"); // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
            props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl
            // 根据邮件配置创建会话，注意session别导错包
            Session session = Session.getDefaultInstance(props);
            // 开启debug模式，可以看到更多详细的输入日志
            session.setDebug(true);
            //创建邮件
            MimeMessage message = createEmail(session,users,content);   //将用户和内容传递过来
            //获取传输通道
            Transport transport = session.getTransport();
            transport.connect(SMTPSERVER,ACCOUT, PWD);
            //连接，并发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MimeMessage createEmail(Session session,String users,String content) throws Exception {
        // 根据会话创建邮件
        MimeMessage msg = new MimeMessage(session);
        // address邮件地址, personal邮件昵称, charset编码方式
        InternetAddress fromAddress = new InternetAddress(ACCOUT,
                "中间件推送", "utf-8");
        // 设置发送邮件方
        msg.setFrom(fromAddress);
        //        单个可以直接这样创建
       InternetAddress receiveAddress = new InternetAddress(users);
        // 设置邮件接收方
        //Address[] internetAddressTo = new InternetAddress().parse(users);

        //type:

        //要被设置为 TO, CC 或者 BCC，这里 CC 代表抄送、BCC 代表秘密抄送。举例：Message.RecipientType.TO


        msg.setRecipients(MimeMessage.RecipientType.TO, String.valueOf(receiveAddress));
        // 设置邮件标题
        msg.setSubject("验证码查收", "utf-8");
        msg.setText(content);
        // 设置显示的发件时间
        msg.setSentDate(new Date());
        // 保存设置
        msg.saveChanges();
        return msg;
    }

}
