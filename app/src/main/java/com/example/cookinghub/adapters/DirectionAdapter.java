package com.example.cookinghub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cookinghub.R;
import com.example.cookinghub.classes.Direction;

import java.util.List;

public class DirectionAdapter extends RecyclerView.Adapter<DirectionAdapter.DirectionViewHolder> {

    private List<Direction> directionList;
    private Context mContext;
    private DirectionListener directionListener;

    public DirectionAdapter(Context context, List<Direction> directionList) {
        mContext = context;
        this.directionList = directionList;
    }


    @Override
    public DirectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.direction_item_row,
                        parent, false);
        return new DirectionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DirectionViewHolder holder, int position) {
        Direction direction = directionList.get(position);
        holder.bind(direction);
    }

    @Override
    public int getItemCount() {
        return directionList.size();
    }

    public class DirectionViewHolder extends RecyclerView.ViewHolder {

        TextView directionText;
        ImageView wasteBin;

        public DirectionViewHolder(View itemView) {
            super(itemView);

            directionText = itemView.findViewById(R.id.directionText);

        }

        public void bind(Direction direction) {
            directionText.setText(direction.getBody());
        }
    }

    public void setDirectionListener(DirectionListener directionListener) {
        this.directionListener = directionListener;
    }

    public interface DirectionListener {
        void onDeleteDirection(int position);
    }
}