package com.hokol.medium.http.bean;

import java.util.List;

public class VUserRechargeRecordBean
{
	private List<VUserRechargeRecordOneBean> list;

	public List<VUserRechargeRecordOneBean> getList()
	{
		return list;
	}

	public void setList(List<VUserRechargeRecordOneBean> list)
	{
		this.list = list;
	}

	public static class VUserRechargeRecordOneBean
	{
		/* 充值红豆数 */
		private float recharge_num;

		/* 充值时间 */
		private long add_time;

		public float getRecharge_num()
		{
			return recharge_num;
		}

		public void setRecharge_num(float recharge_num)
		{
			this.recharge_num = recharge_num;
		}

		public long getAdd_time()
		{
			return add_time;
		}

		public void setAdd_time(long add_time)
		{
			this.add_time = add_time;
		}
	}
}
