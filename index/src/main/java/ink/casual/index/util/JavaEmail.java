package ink.casual.index.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author lwt
 * @date 2018/11/2 20:27
 */
public class JavaEmail {

    private static Logger logger = LoggerFactory.getLogger(JavaEmail.class);

    final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public static Integer sendEmail(String to, String subject, String content){
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();

        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout","20000");
        // 验证账号及密码，密码需要是第三方授权码
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("nbliuwentao@qq.com", "mtlywrtgcwzlbhdi");
            }
        };
        Session session = Session.getInstance(props, auth);
        // 2.创建一个Message，它相当于是邮件内容
        MimeMessage message = new MimeMessage(session);
        try {
            //防止成为垃圾邮件，披上outlook的马甲
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            // 设置发送者
            message.setFrom(new InternetAddress("nbliuwentao@qq.com"));
            // 设置发送方式与接收者
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            // 设置主题
            message.setSubject(subject);
            //创建消息主体
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content,"text/html;charset=utf-8");
            // 创建多重消息
            Multipart multipart=new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            // 设置邮件消息发送的时间
            message.setSentDate(Calendar.getInstance().getTime());
            // 设置内容
            message.setContent(multipart, "text/html;charset=utf-8");
            // 3.创建 Transport用于将邮件发送
            Transport.send(message);
            return 200;
        }catch (Exception e){
            logger.error("send mail had some error:{}",e);
            return 400;
        }
    }
}
