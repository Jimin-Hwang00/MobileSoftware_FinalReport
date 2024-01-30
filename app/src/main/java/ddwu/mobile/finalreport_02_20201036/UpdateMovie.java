package ddwu.mobile.finalreport_02_20201036;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateMovie extends AppCompatActivity {

    TextView idTextView;
    ImageView posterImage;
    EditText titleEditText;
    EditText directorEditText;
    EditText dateEditText;
    EditText companyEditText;
    Movie updateMovie;
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
        setContentView(R.layout.update_movie);

        updateMovie = (Movie) getIntent().getSerializableExtra("updateMovie");

        idTextView = findViewById(R.id.idTextView);
        posterImage = findViewById(R.id.updateImageView);
        titleEditText = findViewById(R.id.updateTitle);
        directorEditText = findViewById(R.id.updateDirector);
        dateEditText  = findViewById(R.id.updateDate);
        companyEditText  = findViewById(R.id.updateCompany);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UpdateMovie.this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        idTextView.setText("ID #" + Long.toString(updateMovie.get_id()));
        posterImage.setImageResource(updateMovie.getPoster());
        titleEditText.setText(updateMovie.getTitle());
        directorEditText.setText(updateMovie.getDirector());
        dateEditText.setText(updateMovie.getDate());
        companyEditText.setText(updateMovie.getCompany());

        movieDBManger = new MovieDBManger(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updateBtn:
                String title = titleEditText.getText().toString();
                String director = directorEditText.getText().toString();

                if (title.equals("")) {
                    Toast.makeText(this, "영화 제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else if (director.equals("")) {
                    Toast.makeText(this, "영화 감독을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    updateMovie.setTitle(title);
                    updateMovie.setDirector(director);
                    updateMovie.setDate(dateEditText.getText().toString());
                    updateMovie.setCompany(companyEditText.getText().toString());

                    if (movieDBManger.modifyMovie(updateMovie)) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(this, "영화 수정에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.updateCancelBtn:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
