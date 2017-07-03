package com.hokol.medium.http.bean;

import java.util.ArrayList;
import java.util.List;

public class WUserSystemMessageSignReadBean
{
	/* 用户ID */
	private String user_id;

	/* 用户提交的信息 */
	private List<String> mess_ids;

	public WUserSystemMessageSignReadBean(String user_id, String msgId)
	{
		this.user_id = user_id;
		this.mess_ids = new ArrayList<>();
		this.mess_ids.add(msgId);
	}

	public WUserSystemMessageSignReadBean(String user_id, List<String> mess_ids)
	{
		this.user_id = user_id;
		this.mess_ids = mess_ids;
	}

	public String getUser_id()
	{
		return user_id;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public List<String> getMess_ids()
	{
		return mess_ids;
	}

	public void setMess_ids(List<String> mess_ids)
	{
		this.mess_ids = mess_ids;
	}
}
