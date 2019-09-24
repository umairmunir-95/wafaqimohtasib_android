package com.wafaqimohtasib.mohstasibapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.chaos.view.PinView;
import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.remote.models.SignUp.SignUpRequest;
import com.wafaqimohtasib.mohstasibapp.ui.activities.LoginActivity;
import com.wafaqimohtasib.mohstasibapp.ui.activities.MyComplaintsActivity;
import com.wafaqimohtasib.mohstasibapp.ui.viewmodels.UsersViewModel;

public class DialogManager {

    public static void noConnectivityPopUp(final Context context) {

        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_alert_dialog, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final AlertDialog alertDialog = alertDialogBuilder.create();
                ImageView ivClose = promptsView.findViewById(R.id.iv_close);
                ImageView ivInfo = promptsView.findViewById(R.id.iv_info);
                TextView tvTitle = promptsView.findViewById(R.id.tv_option);
                TextView tvBody = promptsView.findViewById(R.id.tv_body);
                Button btnNo = promptsView.findViewById(R.id.btn_no);
                Button btnYes = promptsView.findViewById(R.id.btn_yes);
                ivClose.setVisibility(View.GONE);
                btnNo.setVisibility(View.GONE);
                ivInfo.setVisibility(View.VISIBLE);
                tvTitle.setText(R.string.internet_available_title);
                tvBody.setText(R.string.internet_available_msg);
                btnYes.setText(context.getResources().getString(R.string.dismiss));

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
                alertDialogBuilder.setCancelable(true);
                alertDialog.show();
            }
        });
    }

    public static void showSettingsDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.needpermission));
        builder.setMessage(context.getString(R.string.permissionmessage));
        builder.setPositiveButton(context.getString(R.string.gotosettings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings(context);
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    public static void openSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        ((Activity)context).startActivityForResult(intent, 101);
    }

    public static void showVerificationPinPopuop(final Context context, final ProgressBar progressBar,final SignUpRequest signUpRequest) {
        final UsersViewModel usersViewModel = ViewModelProviders.of((FragmentActivity) context).get(UsersViewModel.class);
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.dialog_pin_entry, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final AlertDialog alertDialog = alertDialogBuilder.create();
                final PinView etPinView = promptsView.findViewById(R.id.pin_view);
                final Button btnSubmit = promptsView.findViewById(R.id.btn_submit);

                etPinView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        if (etPinView.getText().toString().equals(Helpers.getPreferenceValues(context, context.getResources().getString(R.string.verification_code)))) {
                            usersViewModel.registerUser(context, progressBar, signUpRequest);
                        } else {
                            Toast.makeText(context, context.getResources().getString(R.string.verification_error), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialog.setCancelable(false);
                alertDialogBuilder.setCancelable(false);
                alertDialog.show();
            }
        });
    }


    public static void showNetworkInfo(final Context context, final String title, final String body, final String requestType) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.custom_alert_dialog, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final AlertDialog alertDialog = alertDialogBuilder.create();
                ImageView ivClose = promptsView.findViewById(R.id.iv_close);
                TextView tvTitle = promptsView.findViewById(R.id.tv_option);
                TextView tvBody = promptsView.findViewById(R.id.tv_body);
                Button btnNo = promptsView.findViewById(R.id.btn_no);
                Button btnYes = promptsView.findViewById(R.id.btn_yes);
                ivClose.setVisibility(View.VISIBLE);
                btnNo.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
                tvTitle.setText(title);
                tvBody.setText(body);
                btnYes.setText(context.getResources().getString(R.string.dismiss));
                tvBody.setTextColor(context.getResources().getColor(R.color.black));
                tvBody.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                ivClose.setBackground(context.getResources().getDrawable(R.drawable.ic_info));

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(requestType.equals("userRegistration"))
                        {
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }
                        else  if(requestType.equals("approveReject"))
                        {
                            context.startActivity(new Intent(context, MyComplaintsActivity.class));
                        }
                        alertDialog.cancel();
                    }
                });
                alertDialogBuilder.setCancelable(false);
                alertDialog.show();
            }
        });
    }

}
