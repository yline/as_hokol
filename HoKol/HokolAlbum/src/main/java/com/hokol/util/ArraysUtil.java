package com.hokol.util;

import java.util.Collections;
import java.util.List;

/**
 * 数据集合 工具类
 *
 * @author yline 2017/5/17 -- 13:47
 * @version 1.0.0
 */
public class ArraysUtil
{
	public static <T> boolean compare(T a, T b)
	{
		if (null == a)
		{
			if (null == b)
			{
				return true;
			}

			return false;
		}
		else
		{
			if (null == b)
			{
				return false;
			}
		}

		return a.equals(b);
	}

	public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b)
	{
		if (null == a || a.size() == 0)
		{
			if (null == b || b.size() == 0)
			{
				return true;
			}

			return false;
		}
		else
		{
			if (null == b || b.size() == 0)
			{
				return false;
			}
		}

		if (a.size() != b.size())
		{
			return false;
		}

		Collections.sort(a);
		Collections.sort(b);
		for (int i = 0; i < a.size(); i++)
		{
			if (!a.get(i).equals(b.get(i)))
			{
				return false;
			}
		}
		return true;
	}
}
