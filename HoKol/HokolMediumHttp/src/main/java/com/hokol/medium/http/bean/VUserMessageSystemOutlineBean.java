package com.hokol.medium.http.bean;

public class VUserMessageSystemOutlineBean
{
	/* 消息ID */
	private String mess_id;

	/* 消息标题 */
	private String mess_title;

	/* 发布时间 */
	private long pub_time;

	/* 未读消息数 */
	private int unread_num;

	public String getMess_id()
	{
		return mess_id;
	}

	public void setMess_id(String mess_id)
	{
		this.mess_id = mess_id;
	}

	public String getMess_title()
	{
		return mess_title;
	}

	public void setMess_title(String mess_title)
	{
		this.mess_title = mess_title;
	}

	public long getPub_time()
	{
		return pub_time;
	}

	public void setPub_time(long pub_time)
	{
		this.pub_time = pub_time;
	}

	public int getUnread_num()
	{
		return unread_num;
	}

	public void setUnread_num(int unread_num)
	{
		this.unread_num = unread_num;
	}
}
