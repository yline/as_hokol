package com.hokol.medium.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class WNewsSingleBean implements Parcelable
{
	private String news_id;

	public WNewsSingleBean()
	{
	}

	public WNewsSingleBean(String news_id)
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

	public static final Parcelable.Creator<WNewsSingleBean> CREATOR = new Parcelable.Creator<WNewsSingleBean>()
	{

		@Override
		public WNewsSingleBean createFromParcel(Parcel source)
		{
			WNewsSingleBean bean = new WNewsSingleBean();
			bean.news_id = source.readString(); // 读取name
			return bean;
		}

		@Override
		public WNewsSingleBean[] newArray(int size)
		{
			return new WNewsSingleBean[size];
		}
	};
}
