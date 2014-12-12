package com.android.fibonaccicommon;

import android.os.Parcel;
import android.os.Parcelable;

public class FibonacciResponse implements Parcelable {
	private final long result;
	private final long timeInMills;
	
	public FibonacciResponse(long result, long timeInMills ){
		this.result=result;
		this.timeInMills=timeInMills;
	}

	public long getResult() {
		return result;
	}

	public long getTimeInMills() {
		return timeInMills;
	}
	
	public int describeContents(){
		return 0;
	}
	
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeLong(this.result);
		parcel.writeLong(this.timeInMills);
	}
	
	public static final Parcelable.Creator<FibonacciResponse> CREATOR=new Parcelable.Creator<FibonacciResponse>() {
		public FibonacciResponse createFromParcel(Parcel in){
			return new FibonacciResponse(in.readLong(),in.readLong());
		}

		public FibonacciResponse[] newArray(int size) {
			return new FibonacciResponse[size];
		}
		
	};

}
