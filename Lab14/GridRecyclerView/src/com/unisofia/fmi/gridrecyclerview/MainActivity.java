package com.unisofia.fmi.gridrecyclerview;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.unisofia.fmi.gridrecyclerview.images.ImageResource;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<String> imageUrls = ImageResource.getInstance().getImageUrls();

		// TODO Load all images in RecyclerView with CardView and GridLayoutManager

		// Example of loading image in ImageView with Picasso
		ImageView image = (ImageView) findViewById(R.id.imageview_fmi);
		Picasso.with(this).load(imageUrls.get(0)).into(image);
	}

}
