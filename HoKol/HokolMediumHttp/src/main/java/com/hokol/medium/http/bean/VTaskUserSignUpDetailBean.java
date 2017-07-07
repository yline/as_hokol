package com.hokol.medium.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class VTaskUserSignUpDetailBean
{
	private ArrayList<VTaskUserSignUpDetailOneBean> list;

	public ArrayList<VTaskUserSignUpDetailOneBean> getList()
	{
		return list;
	}

	public void setList(ArrayList<VTaskUserSignUpDetailOneBean> list)
	{
		this.list = list;
	}

	public static class VTaskUserSignUpDetailOneBean implements Parcelable
	{
		/**
		 * 注意这里读取的顺序一定要和刚才写出的顺序完全相同
		 */
		public static final Parcelable.Creator<VTaskUserSignUpDetailOneBean> CREATOR = new Parcelable.Creator<VTaskUserSignUpDetailOneBean>()
		{

			@Override
			public VTaskUserSignUpDetailOneBean createFromParcel(Parcel source)
			{
				VTaskUserSignUpDetailOneBean bean = new VTaskUserSignUpDetailOneBean();
				bean.user_id = source.readString(); // 读取name
				bean.user_nickname = source.readString(); // 读取age
				bean.user_logo = source.readString();
				bean.user_sign = source.readString();

				if (null == bean.user_tag)
				{
					bean.user_tag = new ArrayList<>();
				}
				source.readStringList(bean.user_tag);

				bean.is_employe = source.readInt();
				bean.user_sex = source.readString();
				bean.user_level = source.readInt();
				bean.level_url = source.readString();
				return bean;
			}

			@Override
			public VTaskUserSignUpDetailOneBean[] newArray(int size)
			{
				return new VTaskUserSignUpDetailOneBean[size];
			}
		};

		/* 用户id */
		private String user_id;

		/* 用户昵称 */
		private String user_nickname;

		/* 用户头像(链接) */
		private String user_logo;

		/* 用户签名 */
		private String user_sign;

		/* 用户标签 */
		private List<String> user_tag;

		/* 是否录用 */
		private int is_employe;

		/* 用户性别 */
		private String user_sex;

		/* 用户等级 */
		private int user_level;

		/* 用户等级图片 */
		private String level_url;

		public String getUser_id()
		{
			return user_id;
		}

		public void setUser_id(String user_id)
		{
			this.user_id = user_id;
		}

		public String getUser_nickname()
		{
			return user_nickname;
		}

		public void setUser_nickname(String user_nickname)
		{
			this.user_nickname = user_nickname;
		}

		public String getUser_logo()
		{
			return user_logo;
		}

		public void setUser_logo(String user_logo)
		{
			this.user_logo = user_logo;
		}

		public String getUser_sign()
		{
			return user_sign;
		}

		public void setUser_sign(String user_sign)
		{
			this.user_sign = user_sign;
		}

		public List<String> getUser_tag()
		{
			return user_tag;
		}

		public void setUser_tag(List<String> user_tag)
		{
			this.user_tag = user_tag;
		}

		public int getIs_employe()
		{
			return is_employe;
		}

		public void setIs_employe(int is_employe)
		{
			this.is_employe = is_employe;
		}

		public String getUser_sex()
		{
			return user_sex;
		}

		public void setUser_sex(String user_sex)
		{
			this.user_sex = user_sex;
		}

		public int getUser_level()
		{
			return user_level;
		}

		public void setUser_level(int user_level)
		{
			this.user_level = user_level;
		}

		public String getLevel_url()
		{
			return level_url;
		}

		public void setLevel_url(String level_url)
		{
			this.level_url = level_url;
		}

		@Override
		public int describeContents()
		{
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags)
		{
			dest.writeString(user_id);
			dest.writeString(user_nickname);
			dest.writeString(user_logo);
			dest.writeString(user_sign);
			dest.writeStringList(user_tag);
			dest.writeInt(is_employe);
			dest.writeString(user_sex);
			dest.writeInt(user_level);
			dest.writeString(level_url);
		}
	}
}
