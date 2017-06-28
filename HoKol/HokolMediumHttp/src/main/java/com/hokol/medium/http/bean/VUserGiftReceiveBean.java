package com.hokol.medium.http.bean;

import java.util.List;

public class VUserGiftReceiveBean
{
	private List<VUserGiftReceiveOneBean> list;

	public List<VUserGiftReceiveOneBean> getList()
	{
		return list;
	}

	public void setList(List<VUserGiftReceiveOneBean> list)
	{
		this.list = list;
	}

	public static class VUserGiftReceiveOneBean
	{

		/* 用户ID */
		private String user_id;

		/* 用户头像 */
		private String user_logo;

		/* 用户昵称 */
		private String user_nickname;

		/* 红豆数量 */
		private int coin_num;

		/* 赠送时间 */
		private long add_time;

		public String getUser_id()
		{
			return user_id;
		}

		public void setUser_id(String user_id)
		{
			this.user_id = user_id;
		}

		public String getUser_logo()
		{
			return user_logo;
		}

		public void setUser_logo(String user_logo)
		{
			this.user_logo = user_logo;
		}

		public String getUser_nickname()
		{
			return user_nickname;
		}

		public void setUser_nickname(String user_nickname)
		{
			this.user_nickname = user_nickname;
		}

		public int getCoin_num()
		{
			return coin_num;
		}

		public void setCoin_num(int coin_num)
		{
			this.coin_num = coin_num;
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
