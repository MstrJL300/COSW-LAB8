package com.example.jose.codelab2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

/**
 * Created by jose on 14/11/16.
 */

public class MessageDialogFragment extends android.support.v4.app.DialogFragment {

    /**
     * When MessageDialogFragment is created in MainActivity it uses "onCreateDialog" to
     * make the content of the dialog.
     * @param savedInstanceState
     * @return
     */

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        builder.setView(inflater.inflate(R.layout.message_dialog_fragment, null));

        //This is another way to set the message for the builder.
//        builder.setMessage(R.string.dialog_fire_missiles);
                /*
                This are aditional atributes for builder in the form of buttons, I'm not using
                them for this app.

                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });*/

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
