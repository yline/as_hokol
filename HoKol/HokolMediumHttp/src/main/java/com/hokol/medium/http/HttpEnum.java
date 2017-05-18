package com.hokol.medium.http;

public class HttpEnum
{
	public enum UserTag
	{
		// 1:网红,2:主播,3:演员,4:模特,5:歌手,6:体育
		Red(1), Author(2), Performer(3), Model(4), Singer(5), Sport(6);

		private final int pos;

		public int getIndex()
		{
			return pos;
		}

		UserTag(int index)
		{
			this.pos = index;
		}
	}

	public static UserTag getUserTag(int index)
	{
		HttpEnum.UserTag userTag;
		switch (index)
		{
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
}
