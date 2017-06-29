package com.hokol.medium.http.bean;

import java.util.List;

public class VUserCreditBean
{
	/* 用户信息完整度(0-100的数字) */
	private int score1;

	/* 用户作为雇主的评分(数组) */
	private List<VUserCreditHostBean> score2;

	/* 用户作为雇员的评分(数组) */
	private List<VUserCreditSubBean> score3;
	
	public int getScore1()
	{
		return score1;
	}

	public void setScore1(int score1)
	{
		this.score1 = score1;
	}

	public List<VUserCreditHostBean> getScore2()
	{
		return score2;
	}

	public void setScore2(List<VUserCreditHostBean> score2)
	{
		this.score2 = score2;
	}

	public List<VUserCreditSubBean> getScore3()
	{
		return score3;
	}

	public void setScore3(List<VUserCreditSubBean> score3)
	{
		this.score3 = score3;
	}

	/**
	 * 作为雇主身份
	 */
	public static class VUserCreditHostBean
	{
		/* 符合度分数 */
		private int conformity_score;

		/* 交流态度分数 */
		private int communion_score;

		/* 诚信度分数 */
		private int credibility_score;

		public int getConformity_score()
		{
			return conformity_score;
		}

		public void setConformity_score(int conformity_score)
		{
			this.conformity_score = conformity_score;
		}

		public int getCommunion_score()
		{
			return communion_score;
		}

		public void setCommunion_score(int communion_score)
		{
			this.communion_score = communion_score;
		}

		public int getCredibility_score()
		{
			return credibility_score;
		}

		public void setCredibility_score(int credibility_score)
		{
			this.credibility_score = credibility_score;
		}
	}

	/**
	 * 作为雇员身份
	 */
	public static class VUserCreditSubBean
	{
		/* 符合度分数 */
		private int conformity_score;

		/* 活动能力分数 */
		private int action_capacity_score;

		/* 工作态度分数 */
		private int attitude_score;

		public int getConformity_score()
		{
			return conformity_score;
		}

		public void setConformity_score(int conformity_score)
		{
			this.conformity_score = conformity_score;
		}

		public int getAction_capacity_score()
		{
			return action_capacity_score;
		}

		public void setAction_capacity_score(int action_capacity_score)
		{
			this.action_capacity_score = action_capacity_score;
		}

		public int getAttitude_score()
		{
			return attitude_score;
		}

		public void setAttitude_score(int attitude_score)
		{
			this.attitude_score = attitude_score;
		}
	}
}
