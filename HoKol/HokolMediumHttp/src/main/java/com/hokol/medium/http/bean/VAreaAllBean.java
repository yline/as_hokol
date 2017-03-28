package com.hokol.medium.http.bean;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class VAreaAllBean
{
	private Map<String, List<String>> list;

	public Map<String, List<String>> getList()
	{
		return list;
	}

	public void setList(Map<String, List<String>> list)
	{
		this.list = list;
	}

	public Set<String> keySet()
	{
		return list.keySet();
	}
}
