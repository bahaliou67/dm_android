package com.example.cineenigma;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cineenigma.data.RaitingFilmContract;
import com.example.cineenigma.data.RaitingFilmDbHelper;

public class RaitingListActivity extends AppCompatActivity implements RaitingFilmListAdapter.SendMailButonListener {

    String username = "Anonyme";

    private RaitingFilmListAdapter mAdapter;
    private TextView mUsernameText;
    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raiting_list);

        mUsernameText = (TextView) findViewById(R.id.user_ratingList);
        RecyclerView raitingListView = (RecyclerView) findViewById(R.id.raiting_list_view);

        // Set layout for the RecyclerView, because it's a list we are using the linear layout
        raitingListView.setLayoutManager(new LinearLayoutManager(this));

        RaitingFilmDbHelper raitingFilmDbHelper = new RaitingFilmDbHelper(this);
        mDb = raitingFilmDbHelper.getReadableDatabase();
        Cursor cursor = getAllRaitingFilm();

        // Create an adapter for that cursor to display the data
        mAdapter = new RaitingFilmListAdapter(this, cursor, this);

        // Link the adapter to the RecyclerView
        raitingListView.setAdapter(mAdapter);

        //get intent
        Intent intentStartMe = getIntent();
        //set mUsername tex
        if (intentStartMe.hasExtra("username")){
            String usernameT = intentStartMe.getStringExtra("username");
            mUsernameText.setText(usernameT);
            this.username = usernameT;
        }
    }


    /**
     * Open new raiting form Activity form
     * @param v
     */
    public void newRaiting(View v){
        //Start rating list page now
        Context context = getApplicationContext();
        Class raitingFormClass = RaitingFormActivity.class;

        Intent intent = new Intent(context, raitingFormClass);
        intent.putExtra("username", username);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == requestCode && RESULT_OK == resultCode){
            Toast.makeText(getApplicationContext(), "Votre commentaire a bien été ajouté", Toast.LENGTH_LONG).show();
            mAdapter.swapCursor(getAllRaitingFilm());
        }
    }

    /**
     * Get all raiting film
     * @return
     */
    public Cursor getAllRaitingFilm(){
        return mDb.query(
                RaitingFilmContract.RaitingFilmEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    /**
     * When we click on the button item we send mail
     * @param s
     */
    @Override
    public void onClick(String s) {
        sendMail(s);
    }

    public void sendMail(String s){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"bahaliou67@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Send raiting form");
        emailIntent.putExtra(Intent.EXTRA_TEXT   , s);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Toast.makeText(getApplicationContext(), "Mail send succes fully.", Toast.LENGTH_LONG).show();;
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
