package com.example.tripassistant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;

import android.os.Environment;
import android.util.Log;

public class textOperation {
	final static String file = "/history.txt";
	final static String TAG = "ERROR!!!";

	public void writeFile(String s) {
		String path = Environment.getExternalStorageDirectory() + file;
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					path, true));
			out.newLine();
			out.write(s);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			Log.d(TAG, "Error: Can't write");
		}

	}

	public String readFile() {
		String path = Environment.getExternalStorageDirectory() + file;

		String filecontent = null;
		File f = new File(path);
		if (f != null && f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Log.d(TAG, "Error: Input File not find!");
				return null;
			}

			CharBuffer cb;
			try {
				cb = CharBuffer.allocate(fis.available());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Log.d(TAG, "Error: CharBuffer initial failed!");
				return null;
			}

			InputStreamReader isr;
			try {
				isr = new InputStreamReader(fis, "utf-8");
				try {
					if (cb != null) {
						isr.read(cb);
					}
					filecontent = new String(cb.array());
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.d("FILEDATE", "readFile filecontent = " + filecontent);
		return filecontent;
	}
}
