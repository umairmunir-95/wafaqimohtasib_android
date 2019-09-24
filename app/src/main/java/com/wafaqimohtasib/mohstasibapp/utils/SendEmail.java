package com.wafaqimohtasib.mohstasibapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.local.preferences.SharedPreferencesHelper;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender.From;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender.Personalization;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender.ReplyTo;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender.SendEmails;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender.To;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.EmailSender.Content;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.SignUp.SignUpRequest;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends AsyncTask<Void,Void,Void> {

    private Context context;
    private Session session;
    private String emailTo;
    private String subject;
    private String message;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;

    public SendEmail(Context context, ProgressBar progressBar, String emailTo, String subject, String message){
        this.context = context;
        this.emailTo = emailTo;
        this.subject = subject;
        this.message = message;
        this.progressBar=progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,context.getResources().getString(R.string.verifying_email),context.getResources().getString(R.string.please_wait),false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        DialogManager.showVerificationPinPopuop(context,progressBar,createSignUpObject(context));
    }

    @Override
    protected Void doInBackground(Void... params) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EmailConfig.EMIAL_FROM,EmailConfig.PASSWORD);
                    }
                });
        try
        {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(EmailConfig.EMIAL_FROM));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mm.setSubject(subject);
            mm.setText(message);
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SignUpRequest createSignUpObject(Context context)
    {
        SignUpRequest signUpRequest=new SignUpRequest();
        signUpRequest.setName(Helpers.getPreferenceValues(context,context.getResources().getString(R.string.name)));
        signUpRequest.setEmail(Helpers.getPreferenceValues(context,context.getResources().getString(R.string.email)));
        signUpRequest.setPassword(Helpers.getPreferenceValues(context,context.getResources().getString(R.string.password)));
        signUpRequest.setCnic(Helpers.getPreferenceValues(context,context.getResources().getString(R.string.cnic)));
        signUpRequest.setCellNo(Helpers.getPreferenceValues(context,context.getResources().getString(R.string.cell_no)));
        signUpRequest.setAddress(Helpers.getPreferenceValues(context,context.getResources().getString(R.string.address)));
        signUpRequest.setUserType(Helpers.getPreferenceValues(context,context.getResources().getString(R.string.user_type)));
        clearPreferences(context);
        return signUpRequest;
    }

    private void clearPreferences(Context context)
    {
        SharedPreferencesHelper sharedPreferencesHelper=new SharedPreferencesHelper(context);
        sharedPreferencesHelper.setString(context.getResources().getString(R.string.name), "");
        sharedPreferencesHelper.setString(context.getResources().getString(R.string.email), "");
        sharedPreferencesHelper.setString(context.getResources().getString(R.string.password),"");
        sharedPreferencesHelper.setString(context.getResources().getString(R.string.cnic), "");
        sharedPreferencesHelper.setString(context.getResources().getString(R.string.cell_no),"");
        sharedPreferencesHelper.setString(context.getResources().getString(R.string.address),"");
        sharedPreferencesHelper.setString(context.getResources().getString(R.string.user_type),"");
    }
}