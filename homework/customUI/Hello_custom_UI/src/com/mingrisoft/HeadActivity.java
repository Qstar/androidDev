package com.mingrisoft;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HeadActivity extends Activity {
	private ImageView imageView1=null;
	private ImageView imageView2=null;
	private TextView data;
	private TextView time;
	private static String m1;  
    private static String m2; 
    Timer timer = new Timer(); 
    
	public int[] imageId = new int[] { 
		R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07}; 							// 定义并初始化保存头像id的数组
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		 this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.head);	
		//设置该Activity使用的布局
	
		imageView1=(ImageView)findViewById(R.id.imageView1);
		imageView2=(ImageView)findViewById(R.id.imageView2);
		
		imageView1.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
            	 Toast toast1 = Toast.makeText(HeadActivity.this,"Make phone call", Toast.LENGTH_SHORT); 
 	  		    toast1.show();
            	 
             }
     });
		 
		imageView2.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
            	 Toast toast1 = Toast.makeText(HeadActivity.this,"Lock the screen", Toast.LENGTH_SHORT); 
 	  		    toast1.show();
             }
     });
		data=(TextView)findViewById(R.id.date);
		time=(TextView)findViewById(R.id.time);
		
		
		 final Calendar c = Calendar.getInstance();  
	        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));  
	       String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份 
	       String  mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份 
	       String  mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));  // 获取当前月份的日期号码  
	       String  mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));  
	        if("1".equals(mWay)){  
	            mWay ="Sunday";  
	        }else if("2".equals(mWay)){  
	            mWay ="Mondy";  
	        }else if("3".equals(mWay)){  
	            mWay ="Tuesday";  
	        }else if("4".equals(mWay)){  
	            mWay ="Wednesday";  
	        }else if("5".equals(mWay)){  
	            mWay ="Thursday";  
	        }else if("6".equals(mWay)){  
	            mWay ="Friday";  
	        }else if("7".equals(mWay)){  
	            mWay ="Saturday";  
	        }  
	        
	   m1=mWay+"  "+mDay+"-"+mMonth+"-"+mYear;
	   SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
       m2 =    sDateFormat.format(new    java.util.Date());  
		data.setText(m1);
		time.setText(m2.split(" ")[1]);
		timer.schedule(task, 1000, 1000);
		GridView gridview = (GridView) findViewById(R.id.gridView1); 			// 获取GridView组件
		BaseAdapter adapter=new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview;							//声明ImageView的对象
				if(convertView==null){
					imageview=new ImageView(HeadActivity.this);		//实例化ImageView的对象
					/*************设置图像的宽度和高度******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(200);
					imageview.setMaxHeight(200);
				
					/**************************************************/
					imageview.setPadding(5, 5, 5, 5);			//设置ImageView的内边距
				}else{
					imageview=(ImageView)convertView;
				}
				imageview.setImageResource(imageId[position]);		//为ImageView设置要显示的图片
				return imageview;//返回ImageView
			}
			/* 
			 * 功能：获得当前选项的ID
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}
			/* 
			 * 功能：获得当前选项
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}
			/*
			 * 获得数量
			 */
			@Override
			public int getCount() {
				return imageId.length;
			}
		};
		
		gridview.setAdapter(adapter); 									// 将适配器与GridView关联
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			
				switch(position)

				{

				case 0:{Toast toast1 = Toast.makeText(HeadActivity.this,"Send messages", Toast.LENGTH_SHORT); 
	  		           toast1.show();
	  		           break;
				}
				case 1:{Toast toast12 = Toast.makeText(HeadActivity.this,"Take photoes", Toast.LENGTH_SHORT); 
	  		    toast12.show();
	  	         break;
	  					}

				case 2: {Toast toast13 = Toast.makeText(HeadActivity.this,"Surf the Internet", Toast.LENGTH_SHORT); 
	  		    toast13.show();
	  	         break;
	  					}
				

				case 3: {Toast toast14 = Toast.makeText(HeadActivity.this,"Listen to the music", Toast.LENGTH_SHORT); 
	  		    toast14.show();
	  	         break;
	  					}
				}
			
				
			}
		});
		
	}

	
	
	
	TimerTask task = new TimerTask() {  
        @Override  
        public void run() {  
  
            runOnUiThread(new Runnable() {      // UI thread  
                @Override  
                public void run() {  
                	final Calendar c = Calendar.getInstance();  
        	        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));  
        	       String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份    
        	       String  mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份 
        	       String  mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码 
        	       String  mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));  
        	        if("1".equals(mWay)){  
        	            mWay ="Sunday";  
        	        }else if("2".equals(mWay)){  
        	            mWay ="Monday";  
        	        }else if("3".equals(mWay)){  
        	            mWay ="Tuesday";  
        	        }else if("4".equals(mWay)){  
        	            mWay ="Wednesday";  
        	        }else if("5".equals(mWay)){  
        	            mWay ="Thursday";  
        	        }else if("6".equals(mWay)){  
        	            mWay ="Friday";  
        	        }else if("7".equals(mWay)){  
        	            mWay ="Saturday";  
        	        }  
        	        
        	   m1=mWay+"  "+mDay+"-"+mMonth+"-"+mYear;
        	   SimpleDateFormat    sDateFormat    =   new    SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
               m2 =    sDateFormat.format(new    java.util.Date());  
               
        		data.setText(m1);
        		time.setText(m2.split(" ")[1]);
                }  
            });  
        }  
    };  
}
