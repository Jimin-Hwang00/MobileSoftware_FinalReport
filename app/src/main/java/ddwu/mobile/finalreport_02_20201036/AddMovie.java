package ddwu.mobile.finalreport_02_20201036;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMovie extends AppCompatActivity {

    EditText titleEditText;
    EditText directorEditText;
    EditText dateEditText;
    EditText companyEditText;

    MovieDBManger movieDBManger;

    Calendar calendar = Calendar.getInstance();

    private void updateLabel() {
        String format = "yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);

        dateEditText.setText(simpleDateFormat.format(calendar.getTime()));
    }

    DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        titleEditText = findViewById(R.id.titleTextView);
        directorEditText = findViewById(R.id.directorTextView);
        dateEditText = findViewById(R.id.dateTextView);
        companyEditText = findViewById(R.id.companyTextView);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddMovie.this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        movieDBManger = new MovieDBManger(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                String title = titleEditText.getText().toString();
                String director = directorEditText.getText().toString();

                if (title.equals("")) {
                    Toast.makeText(this, "영화 제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (director.equals("")) {
                    Toast.makeText(this, "영화 감독을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                        int posterID = R.mipmap.movie_icon;
                        String date = dateEditText.getText().toString();
                        String company = companyEditText.getText().toString();

                        Movie newMovie = new Movie(0, posterID, title, director, date, company);

                        if (movieDBManger.addNewMovie(newMovie)) {
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(this, "영화 추가에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                }

                break;
            case R.id.addCancelBtn:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
