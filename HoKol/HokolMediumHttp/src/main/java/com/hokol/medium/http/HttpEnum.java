package com.hokol.medium.http;

import java.util.Arrays;
import java.util.List;

public class HttpEnum
{

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	public enum UserTag
	{
		// 1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育
		All("全部", 0), Red("网红", 1), Author("主播", 2), Performer("演员", 3), Model("模特", 4), Singer("歌手", 5), Sport("体育", 6);

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
				UserTag.Performer.getContent(), UserTag.Model.getContent(), UserTag.Singer.getContent(), UserTag.Sport.getContent());
	}

	public static UserTag getUserTag(int index)
	{
		HttpEnum.UserTag userTag;
		switch (index)
		{
			case 0:
				userTag = UserTag.All;
				break;
			case 1:
				userTag = HttpEnum.UserTag.Red;
				break;
			case 2:
				userTag = HttpEnum.UserTag.Author;
				break;
			case 3:
				userTag = HttpEnum.UserTag.Performer;
				break;
			case 4:
				userTag = HttpEnum.UserTag.Model;
				break;
			case 5:
				userTag = HttpEnum.UserTag.Singer;
				break;
			case 6:
				userTag = HttpEnum.UserTag.Sport;
				break;
			default:
				userTag = HttpEnum.UserTag.Red;
				break;
		}
		return userTag;
	}

	/* &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& 1:男,2:女 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& */
	public enum UserSex
	{
		// 1:男,2:女
		All("不限", 0), Boy("男", 1), Girl("女", 2);

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
		HttpEnum.UserSex userSex;
		switch (index)
		{
			case 0:
				userSex = HttpEnum.UserSex.All;
				break;
			case 1:
				userSex = HttpEnum.UserSex.Boy;
				break;
			case 2:
				userSex = HttpEnum.UserSex.Girl;
				break;
			default:
				userSex = HttpEnum.UserSex.All;
				break;
		}
		return userSex;
	}
}
