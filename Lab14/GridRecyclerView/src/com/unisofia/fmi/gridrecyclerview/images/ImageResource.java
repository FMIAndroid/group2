package com.unisofia.fmi.gridrecyclerview.images;

import java.util.ArrayList;
import java.util.List;

public final class ImageResource {

	private static ImageResource sInstance;

	private List<String> mImageUrls = new ArrayList<String>();

	public static final ImageResource getInstance() {
		if (sInstance == null) {
			sInstance = new ImageResource();
		}

		return sInstance;
	}

	private ImageResource() {
		for (int i = 0; i < 50; i++) {
			mImageUrls.add("http://placehold.it/300/000000&text=FMI%20" + (i + 1));
		}
	}

	public final List<String> getImageUrls() {
		return mImageUrls;
	}
}
