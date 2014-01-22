/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author boniface
 */
public class EmailUtil {

    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mshengutoilethire.co.za");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.socketFactory.port", "587");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
        return props;
    }

    private static Properties getPropertiesBackUp() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
//        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        return props;
    }

    public static void sendSimpleEmail(ComposeEmail email) {// Recipient's email ID needs to be mentioned.  
        // Sender's email ID needs to be mentioned
        final String from = email.getFrom();
        final String password = email.getPassword();

        // -- Attaching to default Session, or we could start a new one --
        Session session = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            try {
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from, "Mshengu KMIS"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
                message.setFrom(new InternetAddress(from));
            }


            // Set To: header field of the header. Compulsary
            if (email.getAddressesTo() != null) {
                if (!email.getAddressesTo().isEmpty()) {
                    Address[] addressTo = new Address[email.getAddressesTo().size()];
                    int i = 0;
                    for (String s : email.getAddressesTo()) {
                        addressTo[i++] = new InternetAddress(s);
//                        System.out.println("Added TO: " + s);
                    }
                    message.addRecipients(Message.RecipientType.TO, addressTo);
                }
            }

            //Set CC: header filed of the header. Not compulsary
            if (email.getAddressesCC() != null) {
                if (!email.getAddressesCC().isEmpty()) {
                    Address[] addressCC = new Address[email.getAddressesCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesCC()) {
                        addressCC[i++] = new InternetAddress(s);
//                        System.out.println("Added CC: " + s);
                    }
                    message.addRecipients(Message.RecipientType.CC, addressCC);
                }
            }

            //Set BCC: header filed of the header. Not compulsary
            if (email.getAddressesBCC() != null) {
                if (!email.getAddressesBCC().isEmpty()) {
                    Address[] addressBCC = new Address[email.getAddressesBCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesBCC()) {
                        addressBCC[i++] = new InternetAddress(s);
//                        System.out.println("Added BCC: " + s);
                    }
                    message.addRecipients(Message.RecipientType.BCC, addressBCC);
                }
            }


            // Set Subject: header field
            message.setSubject(email.getSubject());

            // Now set the actual message
            message.setText(email.getEmailBody());

            // Send message
            Transport.send(message);

            System.out.println("Sent email successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void sendWithAttachments(ComposeEmail email, DataSource attachment, String filename) {

        // Sender's email ID needs to be mentioned
        final String from = email.getFrom();
        final String password = email.getPassword();

        // -- Attaching to default Session, or we could start a new one --
        Session session = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));


            // Set To: header field of the header. Compulsary
            if (email.getAddressesTo() != null) {
                if (!email.getAddressesTo().isEmpty()) {
                    Address[] addressTo = new Address[email.getAddressesTo().size()];
                    int i = 0;
                    for (String s : email.getAddressesTo()) {
                        addressTo[i++] = new InternetAddress(s);
//                        System.out.println("Added TO: " + s);
                    }
                    message.addRecipients(Message.RecipientType.TO, addressTo);
                }
            }

            //Set CC: header filed of the header. Not compulsary
            if (email.getAddressesCC() != null) {
                if (!email.getAddressesCC().isEmpty()) {
                    Address[] addressCC = new Address[email.getAddressesCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesCC()) {
                        addressCC[i++] = new InternetAddress(s);
//                        System.out.println("Added CC: " + s);
                    }
                    message.addRecipients(Message.RecipientType.CC, addressCC);
                }
            }

            //Set BCC: header filed of the header. Not compulsary
            if (email.getAddressesBCC() != null) {
                if (!email.getAddressesBCC().isEmpty()) {
                    Address[] addressBCC = new Address[email.getAddressesBCC().size()];
                    int i = 0;
                    for (String s : email.getAddressesBCC()) {
                        addressBCC[i++] = new InternetAddress(s);
//                        System.out.println("Added BCC: " + s);
                    }
                    message.addRecipients(Message.RecipientType.BCC, addressBCC);
                }
            }


            // Set Subject: header field
            message.setSubject(email.getSubject());

            // Create the message part 
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setContent(email.getEmailBody(), "text/html");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(attachment));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
