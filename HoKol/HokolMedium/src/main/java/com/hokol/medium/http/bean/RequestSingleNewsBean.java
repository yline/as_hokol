package com.hokol.medium.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestSingleNewsBean implements Parcelable
{
	private String news_id;

	public RequestSingleNewsBean()
	{
	}

	public RequestSingleNewsBean(String news_id)
	{
		this.news_id = news_id;
	}

	public String getNews_id()
	{
		return news_id;
	}

	public void setNews_id(String news_id)
	{
		this.news_id = news_id;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(news_id);
	}

	public static final Parcelable.Creator<RequestSingleNewsBean> CREATOR = new Parcelable.Creator<RequestSingleNewsBean>()
	{

		@Override
		public RequestSingleNewsBean createFromParcel(Parcel source)
		{
			RequestSingleNewsBean bean = new RequestSingleNewsBean();
			bean.news_id = source.readString(); // 读取name
			return bean;
		}

		@Override
		public RequestSingleNewsBean[] newArray(int size)
		{
			return new RequestSingleNewsBean[size];
		}
	};
}
