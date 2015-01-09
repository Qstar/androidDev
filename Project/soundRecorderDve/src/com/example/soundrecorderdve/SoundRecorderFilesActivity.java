package com.example.soundrecorderdve;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SoundRecorderFilesActivity extends Activity {

	private ListView lv;
	private String fileUrl;
	private MediaPlayer mMediaPlayer;
	private String mFilePath;
	private TextView mPaht;
	protected View myView;
	protected EditText myEditText;
	private ArrayList<String> item;
	private ArrayList<String> paths;
	private String rootPath = "/";

	// private static final String[] strs = new String[] { "first", "second",
	// "third", "fourth", "fifth" };

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.recorder);
		lv = (ListView) findViewById(R.id.lv);

		Intent intent = getIntent();
		fileUrl = intent.getStringExtra("fileDir");
		mFilePath = intent.getStringExtra("fileDir");

		// System.out.println(fileUrl);
		for (int i = 0; i < ListFile().length; i++)
			System.out.println(ListFile()[i]);
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ListFile()));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) { // TODO Auto-generated method stub

				/*
				 * Toast.makeText( SoundRecorderFilesActivity.this, "你点击的是第" +
				 * position + "项" + parent.getItemAtPosition(position),
				 * Toast.LENGTH_SHORT).show();
				 */

				// File item =(File) parent.getItemAtPosition(position);
				File item = new File((String) parent
						.getItemAtPosition(position));
				fileHandle(item);
				// System.out.println(item.getAbsolutePath());
				/*
				 * if (null != item) { if (item.isDirectory()) { } else { //
				 * 根据文件名称判断文件是否为音频文件，MediaFile为Android系统内部代码。后面的文章将放出 if
				 * (!MediaFile.isAudioFileType(item.getAbsolutePath())) {
				 * Toast.makeText(SoundRecorderFilesActivity.this, "非音频文件不能选择",
				 * Toast.LENGTH_LONG).show(); } else { if
				 * (item.getAbsolutePath().equals(mFilePath)) { return; }
				 * mFilePath = item.getAbsolutePath(); // 播放音乐 if (null ==
				 * mMediaPlayer) { mMediaPlayer = new MediaPlayer(); } else { //
				 * 若在正在播放，停止并将播放位置置为开始位置，不然不能播放。 if (mMediaPlayer.isPlaying()) {
				 * mMediaPlayer.stop(); } mMediaPlayer.seekTo(0);//
				 * 将播放位置置为开始位置，不然不能播放。 } try {// 设置要播放的音频文件路径
				 * mMediaPlayer.setDataSource(item .getAbsolutePath());
				 * mMediaPlayer.prepare(); mMediaPlayer.start();
				 * Toast.makeText(SoundRecorderFilesActivity.this, "已选择，开始播放",
				 * Toast.LENGTH_LONG).show(); } catch (Exception e) { } } } }
				 */

			}
		});

	}

	private void fileHandle(final File file) {
		android.content.DialogInterface.OnClickListener listener1 = new DialogInterface.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					// 选择的item为打开文件
					playfile(file);
				} else if (which == 1) {

				} else {
					// 选择的item为删除文件
					new AlertDialog.Builder(SoundRecorderFilesActivity.this)
							.setTitle("注意")
							.setMessage("确定删除文件？")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											file.delete();
										}
									})
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				}
			}
		};
		// 选择一个文件时要跳出如何处理文件的ListDialog
		String[] menu = { "打开文件", "更改文件名", "删除文件" };
		new AlertDialog.Builder(SoundRecorderFilesActivity.this)
				.setTitle("你要做什么？").setItems(menu, listener1)
				.setPositiveButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				}).show();
	}

	private void playfile(File item) {
		if (null != item) {
			if (item.isDirectory()) {
			} else { // 根据文件名称判断文件是否为音频文件，MediaFile为Android系统内部代码。后面的文章将放出
				if (!MediaFile.isAudioFileType(item.getAbsolutePath())) {
					Toast.makeText(SoundRecorderFilesActivity.this,
							"非音频文件不能选择", Toast.LENGTH_LONG).show();
				} else {
					if (item.getAbsolutePath().equals(mFilePath)) {
						return;
					}
					mFilePath = item.getAbsolutePath();
					// 播放音乐
					if (null == mMediaPlayer) {
						mMediaPlayer = new MediaPlayer();
					} else { // 若在正在播放，停止并将播放位置置为开始位置，不然不能播放。
						if (mMediaPlayer.isPlaying()) {
							mMediaPlayer.stop();
						}
						mMediaPlayer.seekTo(0);// 将播放位置置为开始位置，不然不能播放。
					}
					try {// 设置要播放的音频文件路径
						mMediaPlayer.setDataSource(item.getAbsolutePath());
						mMediaPlayer.prepare();
						mMediaPlayer.start();
						Toast.makeText(SoundRecorderFilesActivity.this,
								"已选择，开始播放", Toast.LENGTH_LONG).show();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	private String[] ListFile() {
		File[] files = new File(fileUrl + "/").listFiles();
		String Path[] = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			Path[i] = files[i].getPath();
			// Path[i] = getFileName(files[i].getPath());
		}
		return Path;
	}

	public String getFileName(String pathandname) {
		int start = pathandname.lastIndexOf("/");
		int end = pathandname.lastIndexOf(".");
		if (start != -1 && end != -1) {
			return pathandname.substring(start + 1, end);
		} else {
			return null;
		}
	}
}
