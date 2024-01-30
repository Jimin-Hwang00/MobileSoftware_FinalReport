package ddwu.mobile.finalreport_02_20201036;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private LayoutInflater layoutInflater;
    ArrayList<Movie> movieArrayList;

    public MovieAdapter(Context context, int layout, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.layout = layout;
        this.movieArrayList = movieArrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movieArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;

        if (view == null) {
            view = layoutInflater.inflate(layout, viewGroup, false);
        }

        ImageView posterImage = view.findViewById(R.id.posterImage);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView directorTextView = view.findViewById(R.id.directorTextView);
        TextView dateTextView = view.findViewById(R.id.dateTextView);

        posterImage.setImageResource(movieArrayList.get(position).getPoster());
        titleTextView.setText(movieArrayList.get(position).getTitle());
        directorTextView.setText(movieArrayList.get(position).getDirector());
        dateTextView.setText(movieArrayList.get(position).getDate());

        return view;
    }


}
