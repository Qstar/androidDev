package com.example.soundrecorderdve;

import java.io.File;
import com.baidu.frontia.Frontia;
import com.baidu.frontia.FrontiaFile;
import com.baidu.frontia.api.FrontiaStorage;
import com.baidu.frontia.api.FrontiaStorageListener.FileProgressListener;
import com.baidu.frontia.api.FrontiaStorageListener.FileTransferListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SoundRecorderFilesActivity extends Activity {

	private ListView lv;
	private String fileUrl;
	private MediaPlayer mMediaPlayer;
	private String mFilePath;
	protected View myView;
	protected EditText myEditText;

	private final static String app_key = "RDEauKZnwRBbGDP5opRQGZ5V";

	/*
	 * private final static String mbApiKey = "RDEauKZnwRBbGDP5opRQGZ5V";
	 * private final static String mbRootPath = "/apps/pcstest_oauth"; private
	 * Handler mbUiThreadHandler = null; private String mbOauth = null;
	 */

	// private static final String[] strs = new String[] { "first", "second",
	// "third", "fourth", "fifth" };

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.recorder);
		lv = (ListView) findViewById(R.id.lv);
//		mbUiThreadHandler = new Handler();

		Intent intent = getIntent();
		fileUrl = intent.getStringExtra("fileDir");
		mFilePath = intent.getStringExtra("fileDir");

		// System.out.println(fileUrl);
		/*
		 * for (int i = 0; i < ListFile().length; i++)
		 * System.out.println(ListFile()[i]);
		 */
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, ListFile()));

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

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
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					// 选择的item为打开文件
					// System.out.println("file name:"+file.getAbsolutePath());
					playfile(file);
				} else if (which == 1) {
					upload(file);
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
											file.delete();
											// lv.setAdapter(new
											// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
											// ListFile()));
										}
									})
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
										}
									}).show();
				}
			}
		};
		// 选择一个文件时要跳出如何处理文件的ListDialog
		String[] menu = { "播放文件", "上传文件", "删除文件" };
		new AlertDialog.Builder(SoundRecorderFilesActivity.this)
				.setTitle("你要做什么？").setItems(menu, listener1)
				.setPositiveButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
	}

	private void upload(final File item) {
		boolean isInit = Frontia.init(getApplicationContext(), app_key);
		if (isInit) {// Frontia is successfully initialized.
			// Use Frontia
			FrontiaStorage mCloudStorage = Frontia.getStorage();
			FrontiaFile file = new FrontiaFile();
			file.setNativePath(item.getAbsolutePath());
			file.setRemotePath(getFileName(item.getAbsolutePath()));

			mCloudStorage.uploadFile(file, new FileProgressListener() {
				@Override
				public void onProgress(String source, long bytes, long total) {
					Log.d("log", "正在上传" + source + ",已经上传" + bytes + ",一共"
							+ total);
				}
			}, new FileTransferListener() {
				@Override
				public void onSuccess(String source, String newTargetName) {
					Log.d("log", "本地文件为" + source + ",云端文件为" + newTargetName);
				}

				@Override
				public void onFailure(String source, int errCode, String errMsg) {
					Log.d("log", "源文件为" + source + ",错误为" + errCode + errMsg);
				}
			});
		}

		/*
		 * BaiduOAuth oauthClient = new BaiduOAuth();
		 * oauthClient.startOAuth(SoundRecorderFilesActivity.this, mbApiKey, new
		 * String[] { "basic" }, new BaiduOAuth.OAuthListener() {
		 * 
		 * @Override public void onException(String msg) {
		 * Toast.makeText(getApplicationContext(), "Login failed " + msg,
		 * Toast.LENGTH_SHORT) .show(); }
		 * 
		 * public void onComplete(BaiduOAuthResponse response) { if (null !=
		 * response) { mbOauth = response.getAccessToken(); Toast.makeText(
		 * getApplicationContext(), "Token: " + mbOauth + "    User name:" +
		 * response.getUserName(), Toast.LENGTH_SHORT).show();
		 * 
		 * 
		 * if(null != mbOauth){
		 * 
		 * Thread workThread = new Thread(new Runnable(){ public void run() {
		 * 
		 * String tmpFile = item.getAbsolutePath(); // String tmpFile =
		 * "/mnt/sdcard/DCIM/File/1.txt";
		 * System.out.println("tmpFile:::::"+tmpFile);
		 * 
		 * BaiduPCSClient api = new BaiduPCSClient();
		 * api.setAccessToken(mbOauth);
		 * 
		 * final BaiduPCSActionInfo.PCSFileInfoResponse response =
		 * api.uploadFile(tmpFile, mbRootPath + getFileName(tmpFile), new
		 * BaiduPCSStatusListener(){
		 * 
		 * @Override public void onProgress(long bytes, long total) { // TODO
		 * Auto-generated method stub
		 * 
		 * 
		 * final long bs = bytes; final long tl = total;
		 * 
		 * mbUiThreadHandler.post(new Runnable(){ public void run(){
		 * Toast.makeText(getApplicationContext(), "total: " + tl + "    sent:"
		 * + bs, Toast.LENGTH_SHORT).show(); System.out.println("total: " + tl +
		 * "    sent:" + bs); } }); }
		 * 
		 * @Override public long progressInterval(){ return 1000; } });
		 * 
		 * mbUiThreadHandler.post(new Runnable(){ public void run(){
		 * Toast.makeText(getApplicationContext(), response.status.errorCode +
		 * "  " + response.status.message + "  " +
		 * response.commonFileInfo.blockList, Toast.LENGTH_SHORT).show(); } });
		 * } });
		 * 
		 * workThread.start(); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * } }
		 * 
		 * public void onCancel() { Toast.makeText(getApplicationContext(),
		 * "Login cancelled", Toast.LENGTH_SHORT).show(); } });
		 */

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
