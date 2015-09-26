package org.njcuacm.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Saurabh on 6/2/2015.
 */
public class DialogAdapter {
    public AlertDialog dialogOutDialog = null;
    public DialogAdapter()
    {

    }

    public void dialogOut(Context context, String message,
                          boolean isPositiveButton, boolean isNegativeButton, boolean isCancellable,
                          String positiveButtonText, String negativeButtonText,
                          View.OnClickListener positiveClickAction, View.OnClickListener negativeClickAction)
    {
        AlertDialog.Builder diag = new AlertDialog.Builder(context);
        diag.setMessage(message);
        diag.setCancelable(isCancellable);
        if(isPositiveButton) {
            diag.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });
        }
        if(isNegativeButton) {
            diag.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });
        }
        final AlertDialog dialog = diag.create();
        dialog.show();
        dialogOutDialog = dialog;
        if(isPositiveButton) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(positiveClickAction);
        }
        if (isNegativeButton)
        {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(negativeClickAction);
        }
    }

    public void dialogOutWithCustomView(Context context, String message,
                          boolean isPositiveButton, boolean isNegativeButton, boolean isCancellable, View view,
                          String positiveButtonText, String negativeButtonText,
                          View.OnClickListener positiveClickAction, View.OnClickListener negativeClickAction)
    {
        AlertDialog.Builder diag = new AlertDialog.Builder(context);
        diag.setMessage(message);
        diag.setCancelable(isCancellable);
        diag.setView(null);
        diag.setView(view);
        if(isPositiveButton) {
            diag.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });
        }
        if(isNegativeButton) {
            diag.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });
        }
        final AlertDialog dialog = diag.create();
        dialog.show();
        dialogOutDialog = dialog;
        if(isPositiveButton) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(positiveClickAction);

        }
        if (isNegativeButton)
        {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(negativeClickAction);
        }
    }
}
