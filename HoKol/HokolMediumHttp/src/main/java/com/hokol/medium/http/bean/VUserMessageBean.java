package com.hokol.medium.http.bean;

import java.util.List;

public class VUserMessageBean
{
	private List<VUserMessageOneBean> list;

	public static class VUserMessageOneBean
	{
		/* 消息ID */
		private String mess_id;

		/* 用户ID */
		private String user_id;

		/* 发送时间 */
		private long pub_time;

		/* 消息标题 */
		private String mess_title;

		/* 消息内容 */
		private String mess_content;

		/* 是否已读（0-未读,1-已读） */
		private int is_read;

		public String getMess_id()
		{
			return mess_id;
		}

		public void setMess_id(String mess_id)
		{
			this.mess_id = mess_id;
		}

		public String getUser_id()
		{
			return user_id;
		}

		public void setUser_id(String user_id)
		{
			this.user_id = user_id;
		}

		public long getPub_time()
		{
			return pub_time;
		}

		public void setPub_time(long pub_time)
		{
			this.pub_time = pub_time;
		}

		public String getMess_title()
		{
			return mess_title;
		}

		public void setMess_title(String mess_title)
		{
			this.mess_title = mess_title;
		}

		public String getMess_content()
		{
			return mess_content;
		}

		public void setMess_content(String mess_content)
		{
			this.mess_content = mess_content;
		}

		public int getIs_read()
		{
			return is_read;
		}

		public void setIs_read(int is_read)
		{
			this.is_read = is_read;
		}
	}

	public List<VUserMessageOneBean> getList()
	{
		return list;
	}

	public void setList(List<VUserMessageOneBean> list)
	{
		this.list = list;
	}
}
