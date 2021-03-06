package murer.rudy.soft7035_mobile_assignement;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import static murer.rudy.soft7035_mobile_assignement.MainActivity.EXTRA_MESSAGE;

/**
 * The type Second activity.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        /**
         * Get the intent Content
         */
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        /**
         * Find all the views from the layout
         */
        TextView mDataPassedTextview = findViewById(R.id.mDataPassedTextview);
        Button mGenerateDialog = findViewById(R.id.mGenerateDialog);
        Button mGenerateMap = findViewById(R.id.mGenerateMap);

        mDataPassedTextview.setText(message);

        /**
         * Create The Dialog and display it
         */
        mGenerateDialog.setOnClickListener((View v) -> {
            Dialog mPickAColorDialog = CreateDialogColorPicker(savedInstanceState);
            mPickAColorDialog.show();
        });


        /**
         * Generate A Google map
         */
        mGenerateMap.setOnClickListener((View v) -> {
                Intent mapintent = new Intent(this, MapsActivity.class);
                startActivity(mapintent);
        });




    }

    /**
     *  Generate the Dialog for the color picker
     * @param savedInstanceState
     * @return the Dialog
     */
    private Dialog CreateDialogColorPicker(Bundle savedInstanceState) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle(R.string.pick_color).setNegativeButton(R.string.cancel, (dialog, id) -> dialog.dismiss());

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(getResources().getString(R.string.Color_Red));
        arrayAdapter.add(getResources().getString(R.string.Color_Green));
        arrayAdapter.add(getResources().getString(R.string.Color_Blue));

        builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
            String strName = arrayAdapter.getItem(which);
            AlertDialog.Builder builderInner = new AlertDialog.Builder(SecondActivity.this);
            builderInner.setMessage(strName);
            builderInner.setTitle(R.string.color_picked);
            builderInner.setPositiveButton("Ok", (dialog1, which1) -> dialog1.dismiss());
            builderInner.show();
        });

        return builderSingle.create();
    }




}
