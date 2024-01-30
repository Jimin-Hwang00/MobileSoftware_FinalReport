package ddwu.mobile.finalreport_02_20201036;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchMovie extends AppCompatActivity {

    TextView searchEditText;
    ImageView posterImage;
    TextView titleInformation;
    TextView directorInformation;
    TextView dateInformation;
    TextView companyInformation;
    RadioButton titleRadioBtn;
    RadioButton directorRadioBtn;
    Button searchBtn;

    View resultView;
    View searchView;

    int search = 0;

    MovieDBManger movieDBManger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie);

        movieDBManger = new MovieDBManger(this);

        Intent resultIntent = new Intent();
        String searchBy = resultIntent.getStringExtra("searchBy");

        searchEditText = findViewById(R.id.searchEditText);
        posterImage = findViewById(R.id.posterInformation);
        titleInformation = findViewById(R.id.titleInformation);
        directorInformation = findViewById(R.id.directorInformation);
        dateInformation = findViewById(R.id.dateInformation);
        companyInformation = findViewById(R.id.companyInformation);
        titleRadioBtn = findViewById(R.id.titleRadioBtn);
        directorRadioBtn = findViewById(R.id.directorRadioBtn);
        resultView = findViewById(R.id.resultView);
        searchView = findViewById(R.id.searchView);
        searchBtn = findViewById(R.id.searchBtn);

        searchBtn.setText("검색");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleRadioBtn:
                if (titleRadioBtn.isChecked()) {
                    titleRadioBtn.setChecked(false);
                } else {
                    titleRadioBtn.setChecked(true);
                }
            case R.id.directorRadioBtn:
                if (directorRadioBtn.isChecked()) {
                    directorRadioBtn.setChecked(false);
                } else {
                    directorRadioBtn.setChecked(true);
                }
            case R.id.searchBtn:
                if(search == 0) {
                    ArrayList<Movie> movieArrayList = new ArrayList<Movie>();

                    if (titleRadioBtn.isChecked()) {
                        String title = searchEditText.getText().toString();

                        if (movieDBManger.getMovieByTitle(title) != null) {
                            movieArrayList.addAll(movieDBManger.getMovieByTitle(title));

                            posterImage.setImageResource(movieArrayList.get(movieArrayList.size() - 1).getPoster());
                            titleInformation.setText(movieArrayList.get(movieArrayList.size() - 1).getTitle());
                            directorInformation.setText(movieArrayList.get(movieArrayList.size() - 1).getDirector());
                            dateInformation.setText(movieArrayList.get(movieArrayList.size() - 1).getDate());
                            companyInformation.setText(movieArrayList.get(movieArrayList.size() - 1).getCompany());

                            resultView.setVisibility(View.VISIBLE);
                            searchView.setVisibility(View.INVISIBLE);

                            movieArrayList.clear();

                            search = 1;
                        } else {
                            Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                    } else if (directorRadioBtn.isChecked()) {
                        String director = searchEditText.getText().toString();

                        if (movieDBManger.getMovieByDirector(director) != null) {

                            movieArrayList.addAll(movieDBManger.getMovieByDirector(director));

                            posterImage.setImageResource(movieArrayList.get(movieArrayList.size() - 1).getPoster());
                            titleInformation.setText(movieArrayList.get(movieArrayList.size() - 1).getTitle());
                            directorInformation.setText(movieArrayList.get(movieArrayList.size() - 1).getDirector());
                            dateInformation.setText(movieArrayList.get(movieArrayList.size() - 1).getDate());
                            companyInformation.setText(movieArrayList.get(movieArrayList.size() - 1).getCompany());

                            resultView.setVisibility(View.VISIBLE);
                            searchView.setVisibility(View.INVISIBLE);

                            movieArrayList.clear();

                            search = 1;
                        } else {
                            Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    resultView.setVisibility(View.INVISIBLE);
                    searchView.setVisibility(View.VISIBLE);

                    searchEditText.setText("");

                    search = 0;
                }
                break;
            case R.id.terminateBtn:
                Toast.makeText(this, "영화 검색을 취소하였습니다.", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
