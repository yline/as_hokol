package com.hokol.medium.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestSingleNewsBean implements Parcelable
{
	private String new_id;

	public RequestSingleNewsBean()
	{
	}

	public RequestSingleNewsBean(String new_id)
	{
		this.new_id = new_id;
	}

	public String getNew_id()
	{
		return new_id;
	}

	public void setNew_id(String new_id)
	{
		this.new_id = new_id;
	}

	@Override
	public String toString()
	{
		return "RequestSingleNewsBean{" +
				"new_id='" + new_id + '\'' +
				'}';
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(new_id);
	}

	public static final Parcelable.Creator<RequestSingleNewsBean> CREATOR = new Parcelable.Creator<RequestSingleNewsBean>()
	{

		@Override
		public RequestSingleNewsBean createFromParcel(Parcel source)
		{
			RequestSingleNewsBean bean = new RequestSingleNewsBean();
			bean.new_id = source.readString(); // 读取name
			return bean;
		}

		@Override
		public RequestSingleNewsBean[] newArray(int size)
		{
			return new RequestSingleNewsBean[size];
		}
	};
}
