package com.hokol.medium.http.bean;

import java.util.List;

public class VUserContactVolumeBean
{
	private List<VUserContactVolumeOneBean> list;

	public List<VUserContactVolumeOneBean> getList()
	{
		return list;
	}

	public void setList(List<VUserContactVolumeOneBean> list)
	{
		this.list = list;
	}

	public static class VUserContactVolumeOneBean
	{
		/* 过期时间 */
		private long expire_time;

		public long getExpire_time()
		{
			return expire_time;
		}

		public void setExpire_time(long expire_time)
		{
			this.expire_time = expire_time;
		}
	}
}
