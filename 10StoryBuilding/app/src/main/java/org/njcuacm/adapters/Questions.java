package org.njcuacm.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.njcuacm.exceptions.NotAChoiceException;
import org.njcuacm.tenstory.R;
import org.njcuacm.tenstory.StoryViewer;

/**
 * Created by Saurabh on 11/28/2015.
 */
public class Questions {
    public static String resultingAnswer = "";
    public boolean isCancelled = false;
    /**
     * Context context - This is the context of the class using this method. (Eg. Story3.class)
     * String c1 - The first choice to be shown.
     * String c2 - The second choice to be shown.
     * String c3 - The third choice to be shown.
     * String message - The message (question) to be shown in the pop-up box.
     * int rightAnswerChoice - The correct answer number (chosen from 0-2), which will trigger the pop-up box to cancel.
     * ^^^ - Throws NotAChoiceException when rightAnswerChoice integer provided is less than 0 or greater than 2.
     * */
    public void showThreeChoices(final Context context, String c1, String c2, String c3, String message, final int rightAnswerChoice) throws NotAChoiceException
    {
        if (rightAnswerChoice < 0 || rightAnswerChoice > 2)
        {
            throw new NotAChoiceException();
        }

        //Set cancelled to false, because it's a public variable...
        //isCancelled = false;

        // Set an EditText view to get user input
        //final EditText getChoice = new EditText(Story2.this);
        //getChoice.setPadding(10, 0, 10, 0);

        //Over here, we'll create a RadioGroup, which will be
        //A view for our Dialog Box.
        final RadioGroup group3 = new RadioGroup(context);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(context);
        final RadioButton rb11 = new RadioButton(context);
        final RadioButton rb12 = new RadioButton(context);

        //Set the radio text of the choices.
        rb10.setText(c1);
        rb11.setText(c2);
        rb12.setText(c3);
        //Now we add the radio buttons to the view (Radio Group)
        group3.addView(rb10);
        group3.addView(rb11);
        group3.addView(rb12);
        //Change the view's gravity.
        group3.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        //Set the gravity of the radio buttons to be in the middle.
        rb10.setGravity(Gravity.FILL_HORIZONTAL);
        rb11.setGravity(Gravity.FILL_HORIZONTAL);
        rb12.setGravity(Gravity.FILL_HORIZONTAL);
        //Initialize the dialog
        AlertDialog.Builder diag3 = new AlertDialog.Builder(context);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage(message);
        //Make sure the user cannot cancel the Dialog until the user answers it.
        diag3.setCancelable(true);
        diag3.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        diag3.setNegativeButton("GO BACK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        diag3.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isCancelled = true;
            }
        });
        final AlertDialog dialog3 = diag3.create();
        dialog3.show();
        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog3.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rightAnswerChoice)
                {
                    case 0:
                        if (rb10.isChecked()) {
                            //resultingAnswer = rb10.getText().toString();
                            Toast.makeText(context.getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                            dialog3.dismiss();
                        }
                        else {
                            Toast.makeText(context.getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        if (rb11.isChecked()) {
                            //resultingAnswer = rb11.getText().toString();
                            Toast.makeText(context.getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                            dialog3.dismiss();
                        }
                        else {
                            Toast.makeText(context.getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (rb12.isChecked()) {
                            //resultingAnswer = rb12.getText().toString();
                            Toast.makeText(context.getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                            dialog3.dismiss();
                        }
                        else {
                            Toast.makeText(context.getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        dialog3.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.cancel();
            }
        });
    }

    /**
     * Context context - This is the context of the class using this method. (Eg. Story2.class)
     * String c1 - The first choice to be shown.
     * String c2 - The second choice to be shown.
     * String message - The message (question) to be shown in the pop-up box.
     * int rightAnswerChoice - The correct answer number (chosen from 0-2), which will trigger the pop-up box to cancel.
     * ^^^ - Throws NotAChoiceException when rightAnswerChoice integer provided is less than 0 or greater than 2.
     * */
    public void showTwoChoices(final Context context, String c1, String c2, String message, final int rightAnswerChoice) throws NotAChoiceException
    {
        if (rightAnswerChoice < 0 || rightAnswerChoice > 1)
        {
            throw new NotAChoiceException();
        }

        //Set cancelled to false
        //isCancelled = false;

        // Set an EditText view to get user input
        //final EditText getChoice = new EditText(Story2.this);
        //getChoice.setPadding(10, 0, 10, 0);

        //Over here, we'll create a RadioGroup, which will be
        //A view for our Dialog Box.
        final RadioGroup group3 = new RadioGroup(context);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(context);
        final RadioButton rb11 = new RadioButton(context);

        //Set the radio text of the choices.
        rb10.setText(c1);
        rb11.setText(c2);
        //Now we add the radio buttons to the view (Radio Group)
        group3.addView(rb10);
        group3.addView(rb11);
        //Change the view's gravity.
        group3.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        //Set the gravity of the radio buttons to be in the middle.
        rb10.setGravity(Gravity.FILL_HORIZONTAL);
        rb11.setGravity(Gravity.FILL_HORIZONTAL);
        //Initialize the dialog
        AlertDialog.Builder diag3 = new AlertDialog.Builder(context);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage(message);
        //Make sure the user cannot cancel the Dialog until the user answers it.
        diag3.setCancelable(true);
        diag3.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        diag3.setNegativeButton("GO BACK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        diag3.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isCancelled = true;
            }
        });

        final AlertDialog dialog3 = diag3.create();
        dialog3.show();
        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog3.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rightAnswerChoice)
                {
                    case 0:
                        if (rb10.isChecked()) {
                            //resultingAnswer = rb10.getText().toString();
                            Toast.makeText(context.getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                            dialog3.dismiss();
                        }
                        else {
                            Toast.makeText(context.getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        if (rb11.isChecked()) {
                            //resultingAnswer = rb11.getText().toString();
                            Toast.makeText(context.getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                            dialog3.dismiss();
                        }
                        else {
                            Toast.makeText(context.getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
        dialog3.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.cancel();
            }
        });
    }

    /**
     * Context context - This is the context of the class using this method. (Eg. Story2.class)
     * String c1 - The first choice to be shown.
     * String c2 - The second choice to be shown.
     * String message - The message (question) to be shown in the pop-up box.
     * int rightAnswerChoice - The correct answer number (chosen from 0-2), which will trigger the pop-up box to cancel.
     * ^^^ - Throws NotAChoiceException when rightAnswerChoice integer provided is less than 0 or greater than 2.
     * */
    public void showEditableChoice(final Context context, String message, final String rightAnswer)
    {
        //Set cancelled to false
        //isCancelled = false;


        //Initialize Editable answer
        final EditText answer = new EditText(context);
        answer.setHint("Type your answer HERE--ANSWER_FOR_TESTING: " + rightAnswer);
        answer.setGravity(Gravity.CENTER_HORIZONTAL);
        answer.setHintTextColor(context.getResources().getColor(R.color.primaryColor));
        answer.setMaxLines(1);
        answer.setLines(1);
        answer.setPadding(10, 10, 10, 10);
        answer.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(answer.getApplicationWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        //Initialize the dialog
        AlertDialog.Builder diag3 = new AlertDialog.Builder(context);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(answer);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage(message);
        //Make sure the user cannot cancel the Dialog until the user answers it.
        diag3.setCancelable(true);
        diag3.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        diag3.setNegativeButton("GO BACK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        diag3.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isCancelled = true;
            }
        });
        final AlertDialog dialog3 = diag3.create();
        dialog3.show();

        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog3.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answerText = answer.getText().toString();
                if(answerText.equalsIgnoreCase(rightAnswer))
                {
                    Toast.makeText(context.getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
                    dialog3.dismiss();
                }
                else
                {
                    Toast.makeText(context.getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog3.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.cancel();
            }
        });
    }

    public void showResultingQuestion(final Context context, String question)
    {
        // Set an EditText view to get user input
        //final EditText getChoice = new EditText(Story2.this);
        //getChoice.setPadding(10, 0, 10, 0);

        //Over here, we'll create a RadioGroup, which will be
        //A view for our Dialog Box.
        final RadioGroup group3 = new RadioGroup(context);
        //We will have three radio buttons. Each radio button will contain a choice
        final RadioButton rb10 = new RadioButton(context);
        final RadioButton rb11 = new RadioButton(context);
        //Set the radio text of the choices.
        rb10.setText("Yes");
        rb11.setText("No");
        //Now we add the radio buttons to the view (Radio Group)
        group3.addView(rb10);
        group3.addView(rb11);
        //Change the view's gravity.
        group3.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        //Set the gravity of the radio buttons to be in the middle.
        rb10.setGravity(Gravity.FILL_HORIZONTAL);
        rb11.setGravity(Gravity.FILL_HORIZONTAL);
        //Initialize the dialog
        AlertDialog.Builder diag3 = new AlertDialog.Builder(context);
        //Set the RadioGroup to the dialog's view.
        diag3.setView(group3);
        //We now show the message and the speaker in the dialog.
        diag3.setMessage(question);
        //Make sure the user cannot cancel the Dialog until the user answers it.
        diag3.setCancelable(false);
        diag3.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        final AlertDialog dialog3 = diag3.create();
        dialog3.show();
        //Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog3.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                //Activity activity = new Activity();
                //The radio button number 3 is our answer, so check if the user chose it.
                if (rb10.isChecked()) {
                    resultingAnswer = rb10.getText().toString();

                    dialog3.dismiss();
                    intent = new Intent(context, StoryViewer.class);
                    context.startActivity(intent);

                } else if (rb11.isChecked()) {
                    resultingAnswer = rb11.getText().toString();
                    dialog3.dismiss();
                    intent = new Intent(context, StoryViewer.class);
                    context.startActivity(intent);
                }
            }
        });
    }



    public Questions()
    {

    }

    public String getResultingAnswer()
    {
        return resultingAnswer;
    }

    public void setResultingAnswer(String resultingAnswer)
    {
        Questions.resultingAnswer = resultingAnswer;
    }

    public boolean isCancelled()
    {
        return isCancelled;
    }

    public void setDialogBoxCancelled(boolean cancelled)
    {
        isCancelled = cancelled;
    }
}
