package edu.hm.eggers.ss2015.appliedmath.lab03;

import java.io.InputStream;

public final class ImageLoader {

	public static InputStream load(final String path){
		InputStream input = ImageLoader.class.getResourceAsStream(path);
		if(input == null)
			input = ImageLoader.class.getResourceAsStream("/" + path);
		return input;
	}
	
}
