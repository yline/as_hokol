package com.hokol.medium.http.bean;

import java.util.List;

public class VUserVipRechargeRecordBean
{
	public static final int VipTypeMonth = 1; // 包月

	public static final int VipTypeSeason = 2; // 包季

	public static final int VipTypeYear = 3; // 包年

	public static final int VipTypeOnceCall = 4; // 一次打电话

	public static final int VipTypeOnceSpace = 5; // 一次看空间

	private List<VUserVipRechargeRecordOneBean> list;

	public List<VUserVipRechargeRecordOneBean> getList()
	{
		return list;
	}

	public void setList(List<VUserVipRechargeRecordOneBean> list)
	{
		this.list = list;
	}

	public static class VUserVipRechargeRecordOneBean
	{
		/* 用户id */
		private String user_id;

		/* 购买时间 */
		private long add_time;

		/* 会员类型(1:包月, 2:包季, 3:包年, 4:一次打电话, 5:一次看空间) */
		private int member_type;

		/* 过期时间, 为0则表示期限为永久 */
		private long expire_time;

		/* 起效时间, 为0则表示期限为永久 */
		private long begin_time;

		public String getUser_id()
		{
			return user_id;
		}

		public void setUser_id(String user_id)
		{
			this.user_id = user_id;
		}

		public long getAdd_time()
		{
			return add_time;
		}

		public void setAdd_time(long add_time)
		{
			this.add_time = add_time;
		}

		public int getMember_type()
		{
			return member_type;
		}

		public void setMember_type(int member_type)
		{
			this.member_type = member_type;
		}

		public long getExpire_time()
		{
			return expire_time;
		}

		public void setExpire_time(long expire_time)
		{
			this.expire_time = expire_time;
		}

		public long getBegin_time()
		{
			return begin_time;
		}

		public void setBegin_time(long begin_time)
		{
			this.begin_time = begin_time;
		}
	}
}
