package com.unisofia.fmi.materialdesigndemo.recyclerview;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.unisofia.fmi.materialdesigndemo.R;
import com.unisofia.fmi.materialdesigndemo.recyclerview.RecyclerViewAdapter.RecyclerViewHolder;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

	private List<String> mItems;

	public RecyclerViewAdapter(List<String> items) {
		mItems = items;
	}

	@Override
	public int getItemCount() {
		return mItems == null ? 0 : mItems.size();
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_card, viewGroup,
				false);
		return new RecyclerViewHolder(v);
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
		if (position % 2 == 0) {
			viewHolder.mImageView.setImageResource(R.drawable.image1);
		} else {
			viewHolder.mImageView.setImageResource(R.drawable.image2);
		}

		String item = mItems.get(position);
		viewHolder.mTextView.setText(item);
	}

	public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

		public ImageView mImageView;
		public TextView mTextView;

		public RecyclerViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			mImageView = (ImageView) itemView.findViewById(R.id.imageview);
			mTextView = (TextView) itemView.findViewById(R.id.textview);
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(v.getContext(), "Clicked " + (getPosition() + 1), Toast.LENGTH_SHORT)
					.show();
		}
	}
}
