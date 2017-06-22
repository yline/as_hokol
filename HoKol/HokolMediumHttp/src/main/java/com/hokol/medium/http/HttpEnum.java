package com.hokol.medium.http;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

public class HttpEnum
{

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	public enum UserTag
	{
		// 1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育
		All("全部", 0), Red("网红", 1), Author("主播", 2), Performer("演员", 3), Model("模特", 4), Singer("歌手", 5), Sport("体育", 6), Other("其他", 7);

		private final int index;

		private final String content;

		public int getIndex()
		{
			return index;
		}

		public String getContent()
		{
			return content;
		}

		UserTag(String content, int index)
		{
			this.index = index;
			this.content = content;
		}
	}

	public static List<String> getUserTagListAll()
	{
		return Arrays.asList(UserTag.All.getContent(), UserTag.Red.getContent(), UserTag.Author.getContent(),
				UserTag.Performer.getContent(), UserTag.Model.getContent(), UserTag.Singer.getContent(), UserTag.Sport.getContent(), UserTag.Other.getContent());
	}

	public static List<String> getUserTagListTail()
	{
		return Arrays.asList(UserTag.Red.getContent(), UserTag.Author.getContent(), UserTag.Performer.getContent(),
				UserTag.Model.getContent(), UserTag.Singer.getContent(), UserTag.Sport.getContent(), UserTag.Other.getContent());
	}

	public static List<String> getUserTagList()
	{
		return Arrays.asList(UserTag.Red.getContent(), UserTag.Author.getContent(), UserTag.Performer.getContent(),
				UserTag.Model.getContent(), UserTag.Singer.getContent(), UserTag.Sport.getContent());
	}

	public static UserTag getUserTag(int index)
	{
		switch (index)
		{
			case 0:
				return UserTag.All;
			case 1:
				return HttpEnum.UserTag.Red;
			case 2:
				return HttpEnum.UserTag.Author;
			case 3:
				return HttpEnum.UserTag.Performer;
			case 4:
				return HttpEnum.UserTag.Model;
			case 5:
				return HttpEnum.UserTag.Singer;
			case 6:
				return HttpEnum.UserTag.Sport;
			default:
				return HttpEnum.UserTag.Red;
		}
	}

	public static UserTag getUserTag(String content)
	{
		if (content.equals(UserTag.All.getContent()))
		{
			return UserTag.All;
		}
		else if (content.equals(UserTag.Red.getContent()))
		{
			return HttpEnum.UserTag.Red;
		}
		else if (content.equals(UserTag.Author.getContent()))
		{
			return HttpEnum.UserTag.Author;
		}
		else if (content.equals(UserTag.Performer.getContent()))
		{
			return HttpEnum.UserTag.Performer;
		}
		else if (content.equals(UserTag.Model.getContent()))
		{
			return HttpEnum.UserTag.Model;
		}
		else if (content.equals(UserTag.Singer.getContent()))
		{
			return HttpEnum.UserTag.Singer;
		}
		else if (content.equals(UserTag.Sport.getContent()))
		{
			return HttpEnum.UserTag.Sport;
		}
		else
		{
			return HttpEnum.UserTag.Red;
		}
	}

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 1:男,2:女 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	public enum UserSex
	{
		// 1:男,2:女
		All("不限", 0), Boy("男", 1), Girl("女", 2), Null("", -1);

		private final int index;

		private final String content;

		public int getIndex()
		{
			return index;
		}

		public String getContent()
		{
			return content;
		}

		UserSex(String content, int index)
		{
			this.index = index;
			this.content = content;
		}
	}

	public static List<String> getUserSexListAll()
	{
		return Arrays.asList(UserSex.All.getContent(), UserSex.Boy.getContent(), UserSex.Girl.getContent());
	}

	public static UserSex getUserSex(int index)
	{
		switch (index)
		{
			case -1:
				return HttpEnum.UserSex.Null;
			case 0:
				return HttpEnum.UserSex.All;
			case 1:
				return HttpEnum.UserSex.Boy;
			case 2:
				return HttpEnum.UserSex.Girl;
			default:
				return HttpEnum.UserSex.Null;
		}
	}

	public static UserSex getUserSex(String content)
	{
		if (TextUtils.isEmpty(content))
		{
			return HttpEnum.UserSex.Null;
		}
		else if (UserSex.Boy.getContent().equals(content))
		{
			return UserSex.Boy;
		}
		else if (UserSex.Girl.getContent().equals(content))
		{
			return UserSex.Girl;
		}
		else
		{
			return HttpEnum.UserSex.Null;
		}
	}

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 1-水瓶座，2-双鱼座，3-白羊座，4-金牛座，5-双子座，6-巨蟹座，7-狮子座，8-处女座，9-天枰座，10-天蝎座，11-射手座，12-摩羯座 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	public enum UserConstell
	{
		// 1:男,2:女
		Aquarius("水瓶座", 1), Pisces("双鱼座", 2), Aries("白羊座", 3), Taurus("金牛座", 4), Gemini("双子座", 5), Cancer("巨蟹座", 6),
		Leo("狮子座", 7), Virgo("处女座", 8), Libra("天枰座", 9), Scorpio("天蝎座", 10), Sagittarius("射手座", 11), Capricorn("摩羯座", 12), Null("", -1);

		private final int index;

		private final String content;

		public int getIndex()
		{
			return index;
		}

		public String getContent()
		{
			return content;
		}

		UserConstell(String content, int index)
		{
			this.index = index;
			this.content = content;
		}
	}

	public static UserConstell getUserConstell(String content)
	{
		if (content.equals(UserConstell.Aquarius.getContent()))
		{
			return UserConstell.Aquarius;
		}
		else if (content.equals(UserConstell.Pisces.getContent()))
		{
			return UserConstell.Pisces;
		}
		else if (content.equals(UserConstell.Aries.getContent()))
		{
			return UserConstell.Aries;
		}
		else if (content.equals(UserConstell.Taurus.getContent()))
		{
			return UserConstell.Taurus;
		}
		else if (content.equals(UserConstell.Gemini.getContent()))
		{
			return UserConstell.Gemini;
		}
		else if (content.equals(UserConstell.Cancer.getContent()))
		{
			return UserConstell.Cancer;
		}
		else if (content.equals(UserConstell.Leo.getContent()))
		{
			return UserConstell.Leo;
		}
		else if (content.equals(UserConstell.Virgo.getContent()))
		{
			return UserConstell.Virgo;
		}
		else if (content.equals(UserConstell.Libra.getContent()))
		{
			return UserConstell.Libra;
		}
		else if (content.equals(UserConstell.Scorpio.getContent()))
		{
			return UserConstell.Scorpio;
		}
		else if (content.equals(UserConstell.Sagittarius.getContent()))
		{
			return UserConstell.Sagittarius;
		}
		else if (content.equals(UserConstell.Capricorn.getContent()))
		{
			return UserConstell.Capricorn;
		}
		else
		{
			return UserConstell.Null;
		}
	}

	public static UserConstell getUserConstell(int index)
	{
		if (index == (UserConstell.Aquarius.getIndex()))
		{
			return UserConstell.Aquarius;
		}
		else if (index == (UserConstell.Pisces.getIndex()))
		{
			return UserConstell.Pisces;
		}
		else if (index == (UserConstell.Aries.getIndex()))
		{
			return UserConstell.Aries;
		}
		else if (index == (UserConstell.Taurus.getIndex()))
		{
			return UserConstell.Taurus;
		}
		else if (index == (UserConstell.Gemini.getIndex()))
		{
			return UserConstell.Gemini;
		}
		else if (index == (UserConstell.Cancer.getIndex()))
		{
			return UserConstell.Cancer;
		}
		else if (index == (UserConstell.Leo.getIndex()))
		{
			return UserConstell.Leo;
		}
		else if (index == (UserConstell.Virgo.getIndex()))
		{
			return UserConstell.Virgo;
		}
		else if (index == (UserConstell.Libra.getIndex()))
		{
			return UserConstell.Libra;
		}
		else if (index == (UserConstell.Scorpio.getIndex()))
		{
			return UserConstell.Scorpio;
		}
		else if (index == (UserConstell.Sagittarius.getIndex()))
		{
			return UserConstell.Sagittarius;
		}
		else if (index == (UserConstell.Capricorn.getIndex()))
		{
			return UserConstell.Capricorn;
		}
		else
		{
			return UserConstell.Null;
		}
	}
}
