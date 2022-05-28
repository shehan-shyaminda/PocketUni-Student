package com.codelabs.pocketuni.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.crowdfire.cfalertdialog.CFAlertDialog;

public class CustomAlertDialog {
    CFAlertDialog.Builder cfAlertDialog;

    public CustomAlertDialog(Context context){
        cfAlertDialog = new CFAlertDialog.Builder(context);
    }

    public void positiveAlert(String title, String description, String buttonTitle, CFAlertDialog.CFAlertStyle style){
        cfAlertDialog
                .setDialogStyle(style)
                .setTitle(title + " \uD83C\uDF8A")
                .setMessage(description)
                .addButton(buttonTitle, -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }


    public void negativeAlert(String title, String description, String buttonTitle, CFAlertDialog.CFAlertStyle style){
        cfAlertDialog
                .setDialogStyle(style)
                .setTitle(title + " \uD83E\uDD2F")
                .setMessage(description)
                .addButton(buttonTitle, -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}
