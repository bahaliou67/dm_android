package com.example.cineenigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cineenigma.data.RaitingFilmContract;
import com.example.cineenigma.data.RaitingFilmDbHelper;
import com.example.cineenigma.model.RaitingFilm;

public class RaitingFormActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;

    private EditText filmTitleEdiText;
    private EditText filmDateEditText;
    private EditText filmScenarioEditText;
    private EditText filmRealisationEditText;
    private EditText filmMusicEditText;
    private EditText filmCommentaireEditText;

    private TextView erroRaitingFormTextView;
    private String username = "anonyme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form);

        //Get all view component here by findById
        filmTitleEdiText = (EditText) findViewById(R.id.raitng_film_title);
        filmDateEditText = (EditText) findViewById(R.id.raiting_film_date);
        filmScenarioEditText = (EditText) findViewById(R.id.raiting_film_scenario);
        filmRealisationEditText = (EditText) findViewById(R.id.raiting_film_realisation);
        filmMusicEditText = (EditText) findViewById(R.id.raiting_film_music);
        filmCommentaireEditText = (EditText) findViewById(R.id.raiting_film_commentaire);

        erroRaitingFormTextView = (TextView) findViewById(R.id.error_raiting_form);

        //database
        RaitingFilmDbHelper raitingFilmDbHelper = new RaitingFilmDbHelper(this);
        mDb = raitingFilmDbHelper.getWritableDatabase();

        //get username from intent
        Intent intentStartMe = getIntent();

        //set mUsername tex
        if (intentStartMe.hasExtra("username")){
            this.username = intentStartMe.getStringExtra("username");
        }
    }


    /**
     * set the form error visible
     */
    private void showRaitingError(){
        erroRaitingFormTextView.setVisibility(View.VISIBLE);
    }

    /**
     * set the form error invisible
     */
    private void hideRaitingError(){
        erroRaitingFormTextView.setVisibility(View.INVISIBLE);
    }

    /**
     * When the button submit is clicked is this method will be called
     * @param v
     */
    public void submitRaitingForm(View v){
        String filmTitle = filmTitleEdiText.getText().toString();
        String filmDate = filmDateEditText.getText().toString();
        String filmScenario = filmScenarioEditText.getText().toString();
        String filmRealisation = filmRealisationEditText.getText().toString();
        String filmMusic = filmMusicEditText.getText().toString();
        String filmCommentaire = filmCommentaireEditText.getText().toString();

        //If value are ok onInsertIt in database
        if (
                (null != filmTitle && filmTitle.length()>0) &&
                        (null != filmDate && filmDate.length() >0 ) &&
                        (null != filmScenario && filmScenario.length() >0 ) &&
                        (null != filmRealisation && filmRealisation.length() >0 ) &&
                        (null != filmMusic && filmMusic.length() >0 ) &&
                        (null != filmCommentaire && filmCommentaire.length() >0 )
        ){
            //If form is valide we save the value from database
            try {
                RaitingFilm raitingFilm = new RaitingFilm(username, filmTitle, filmDate, Integer.parseInt(filmScenario), Integer.parseInt(filmRealisation), Integer.parseInt(filmMusic), filmCommentaire);
                long id = addNewRaitingFilm(raitingFilm);
                if (id>0){
                    hideRaitingError();
                    setResult(RESULT_OK);
                    finish();
                }
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), "Oups an error has occured", Toast.LENGTH_LONG).show();
            }

        }
        else {
            //if form is not valid we show error form
            showRaitingError();
        }
    }


    /**
     * Add new raiting film in database
     * @return
     */
    private long addNewRaitingFilm(RaitingFilm raitingFilm){
        ContentValues cv = new ContentValues();
        cv.put(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_username, this.username);
        cv.put(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_title, raitingFilm.getFilmTitle());
        cv.put(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_date, raitingFilm.getFilmDate());
        cv.put(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_scenario, raitingFilm.getFilmScenario());
        cv.put(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_realisation, raitingFilm.getFilmRealisation());
        cv.put(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_music, raitingFilm.getFilmMusic());
        cv.put(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_commentaire, raitingFilm.getFilmCommentaire());
        return mDb.insert(RaitingFilmContract.RaitingFilmEntry.TABLE_NAME, null, cv);
    }

}
