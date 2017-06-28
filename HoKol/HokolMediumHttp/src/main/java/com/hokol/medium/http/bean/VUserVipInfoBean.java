package com.hokol.medium.http.bean;

public class VUserVipInfoBean
{
	/* 交流券数量 */
	private int call_card_num;

	/* 会员过期时间 */
	private long expire_time;

	/* 会员类型(1:包月,2:包季,3:包年) */
	private int member_type;

	public int getCall_card_num()
	{
		return call_card_num;
	}

	public void setCall_card_num(int call_card_num)
	{
		this.call_card_num = call_card_num;
	}

	public long getExpire_time()
	{
		return expire_time;
	}

	public void setExpire_time(long expire_time)
	{
		this.expire_time = expire_time;
	}

	public int getMember_type()
	{
		return member_type;
	}

	public void setMember_type(int member_type)
	{
		this.member_type = member_type;
	}
}
