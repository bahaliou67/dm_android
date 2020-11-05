package com.example.cineenigma;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cineenigma.data.RaitingFilmContract;
import com.example.cineenigma.model.RaitingFilm;

public class RaitingFilmListAdapter extends RecyclerView.Adapter<RaitingFilmListAdapter.RaitingViewHolder> {

    // Holds on to the cursor to display the waitlist
    private Cursor mCursor;
    private Context mContext;
    private SendMailButonListener sendMailButonListener;

    /**
     * Constructor using the context and the db cursor
     * @param context the calling context/activity
     * @param cursor the db cursor with waitlist data to display
     */
    public RaitingFilmListAdapter(Context context, Cursor cursor, SendMailButonListener sendMailButonListener) {
        this.mContext = context;
        this.mCursor = cursor;
        this.sendMailButonListener = sendMailButonListener;
    }

    @Override
    public RaitingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.rating_film_film_items, parent, false);
        return new RaitingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RaitingViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String username = mCursor.getString(mCursor.getColumnIndex(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_username));
        String filmTitle = mCursor.getString(mCursor.getColumnIndex(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_title));
        String date = mCursor.getString(mCursor.getColumnIndex(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_date));
        int scenario = mCursor.getInt(mCursor.getColumnIndex(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_scenario));
        int realisation = mCursor.getInt(mCursor.getColumnIndex(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_realisation));
        int music = mCursor.getInt(mCursor.getColumnIndex(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_music));
        String commentaire = mCursor.getString(mCursor.getColumnIndex(RaitingFilmContract.RaitingFilmEntry.COLUMN_film_commentaire));

        final RaitingFilm raitingFilm = new RaitingFilm(username, filmTitle, date, scenario, realisation, music, commentaire);

        // Display the guest name
        holder.nameTextView.setText("username "+username);
        holder.filmTitleTextView.setText(String.valueOf("filmTitle: "+filmTitle));
        holder.commentaireTextView.setText(String.valueOf("commentaire: "+commentaire));
        holder.musicTextView.setText(String.valueOf("music: "+music));
        holder.realisationTextView.setText(String.valueOf("realisation: "+realisation));
        holder.scenarioTextView.setText(String.valueOf("scenario: "+scenario));
        holder.dateTextView.setText(String.valueOf("Date: "+date));

        holder.sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMailButonListener.onClick(raitingFilm.toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.mCursor.getCount();
    }

    public interface SendMailButonListener {
        void onClick(String s);
    }



    // COMPLETED (15) Create a new function called swapCursor that takes the new cursor and returns void
    /**
     * Swaps the Cursor currently held in the adapter with a new one
     * and triggers a UI refresh
     *
     * @param newCursor the new cursor that will replace the existing one
     */
    public void swapCursor(Cursor newCursor) {
        // COMPLETED (16) Inside, check if the current cursor is not null, and close it if so
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        // COMPLETED (17) Update the local mCursor to be equal to  newCursor
        mCursor = newCursor;
        // COMPLETED (18) Check if the newCursor is not null, and call this.notifyDataSetChanged() if so
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    /**
     * Inner class to hold the views needed to display a single item in the recycler-view
     */
    class RaitingViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView filmTitleTextView;
        TextView dateTextView;
        TextView scenarioTextView;
        TextView realisationTextView;
        TextView musicTextView;
        TextView commentaireTextView;

        Button sendMailButton;


        public RaitingViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.username_item_text);
            filmTitleTextView = (TextView) itemView.findViewById(R.id.title_item_text);
            dateTextView = (TextView) itemView.findViewById(R.id.date_item_text);
            scenarioTextView = (TextView) itemView.findViewById(R.id.scenario_item_text);
            realisationTextView = (TextView) itemView.findViewById(R.id.realisation_item_text);
            musicTextView = (TextView) itemView.findViewById(R.id.music_item_text);
            commentaireTextView = (TextView) itemView.findViewById(R.id.commentaire_item_text);

            sendMailButton = (Button) itemView.findViewById(R.id.sendmail_item);
        }

        public void  voidSendMail(View v){

        }

    }
}
