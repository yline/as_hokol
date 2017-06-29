package com.hokol.medium.http.bean;

public class VUserCreditBean
{
	/* 用户信息完整度(0-100的数字) */
	private float score1;

	/* 用户作为雇主的评分(数组) */
	private VUserCreditHostBean score2;

	/* 用户作为雇员的评分(数组) */
	private VUserCreditSubBean score3;

	public float getScore1()
	{
		return score1;
	}

	public void setScore1(float score1)
	{
		this.score1 = score1;
	}

	public VUserCreditHostBean getScore2()
	{
		return score2;
	}

	public void setScore2(VUserCreditHostBean score2)
	{
		this.score2 = score2;
	}

	public VUserCreditSubBean getScore3()
	{
		return score3;
	}

	public void setScore3(VUserCreditSubBean score3)
	{
		this.score3 = score3;
	}

	/**
	 * 作为雇主身份
	 */
	public static class VUserCreditHostBean
	{
		/* 符合度分数 */
		private float conformity_score;

		/* 交流态度分数 */
		private float communion_score;

		/* 诚信度分数 */
		private float credibility_score;

		public float getConformity_score()
		{
			return conformity_score;
		}

		public void setConformity_score(float conformity_score)
		{
			this.conformity_score = conformity_score;
		}

		public float getCommunion_score()
		{
			return communion_score;
		}

		public void setCommunion_score(float communion_score)
		{
			this.communion_score = communion_score;
		}

		public float getCredibility_score()
		{
			return credibility_score;
		}

		public void setCredibility_score(float credibility_score)
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
		private float conformity_score;

		/* 活动能力分数 */
		private float action_capacity_score;

		/* 工作态度分数 */
		private float attitude_score;

		public float getConformity_score()
		{
			return conformity_score;
		}

		public void setConformity_score(float conformity_score)
		{
			this.conformity_score = conformity_score;
		}

		public float getAction_capacity_score()
		{
			return action_capacity_score;
		}

		public void setAction_capacity_score(float action_capacity_score)
		{
			this.action_capacity_score = action_capacity_score;
		}

		public float getAttitude_score()
		{
			return attitude_score;
		}

		public void setAttitude_score(float attitude_score)
		{
			this.attitude_score = attitude_score;
		}
	}
}
