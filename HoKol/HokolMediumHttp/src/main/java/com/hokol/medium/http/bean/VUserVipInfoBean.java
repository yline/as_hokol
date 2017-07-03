package com.hokol.medium.http.bean;

public class VUserVipInfoBean
{
	// 该用户不是会员
	public static final int CodeVipNone = 2006;

	// 会员已到期
	public static final int CodeVipPass = 2007;

	public static final int TypePass = -2; // 会员已过期

	public static final int TypeNull = -1; // 未开通会员

	public static final int TypeMonth = 1; // 包月

	public static final int TypeSeason = 2; // 包季

	public static final int TypeYear = 3; // 包年

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
