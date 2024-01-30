// 영화 정보 관리 앱
// 02 분반
// 20201036 황지민
// 2022년 6월 18일 제출

package ddwu.mobile.finalreport_02_20201036;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MovieAdapter movieAdapter;
    ListView movieList;
    ArrayList<Movie> movieArrayList;

    MovieDBManger movieDBManger;

    final static int REQ_CODE = 100;
    final static int UPDATE_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieArrayList = new ArrayList<Movie>();
        movieList = findViewById(R.id.listView);

        movieAdapter = new MovieAdapter(this, R.layout.movie_adapter_layout, movieArrayList);
        movieList.setAdapter(movieAdapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;

                Movie update = movieArrayList.get(position);

                Log.d("MainActivity", "SetOnItemClick");

                Intent intent = new Intent(MainActivity.this, UpdateMovie.class);
                intent.putExtra("updateMovie", update);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        movieList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle(R.string.delete_dialog_title)
                        .setMessage(movieArrayList.get(position).getTitle() + " " + getString(R.string.delete_dialog_message))
                        .setPositiveButton(R.string.delete_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(movieDBManger.removeMovie(movieArrayList.get(position).get_id())) {
                                    Toast.makeText(MainActivity.this, "영화 삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                    movieArrayList.clear();
                                    movieArrayList.addAll(movieDBManger.getAllMovies());
                                    movieAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "영화 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancle, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "영화 삭제가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)
                        .show();

                return true;
            }
        });

        movieDBManger = new MovieDBManger(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieArrayList.clear();
        movieArrayList.addAll(movieDBManger.getAllMovies());
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final ConstraintLayout developerLayout = (ConstraintLayout) View.inflate(MainActivity.this, R.layout.developer_information, null);

        switch(item.getItemId()) {
            case R.id.developer:
                AlertDialog.Builder developBuilder = new AlertDialog.Builder(this);
                developBuilder.setTitle(R.string.developer_dialog_title)
                        .setView(developerLayout)
                        .setIcon(R.mipmap.develop_icon)
                        .show();
                break;
            case R.id.addMovie:
                Intent addIntent = new Intent(MainActivity.this, AddMovie.class);
                startActivityForResult(addIntent, REQ_CODE);
                break;
            case R.id.searchMovie:
                Intent searchIntent = new Intent(MainActivity.this, SearchMovie.class);
                startActivity(searchIntent);
                break;
            case R.id.terminate:
                AlertDialog.Builder finishBuilder = new AlertDialog.Builder(this);
                finishBuilder.setTitle(R.string.terminate_dialog_title)
                        .setMessage(R.string.terminate_dialog_message)
                        .setPositiveButton(R.string.terminate_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finishAffinity();
                                System.runFinalization();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancle, null)
                        .setCancelable(false)
                        .show();
                break;

        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "영화 추가가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 추가가 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "영화 수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 수정이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}