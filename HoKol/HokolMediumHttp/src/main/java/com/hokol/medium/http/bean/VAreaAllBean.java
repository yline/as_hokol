package com.hokol.medium.http.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class VAreaAllBean
{
	private List<VAreaProvinceBean> province;

	public List<VAreaProvinceBean> getProvince()
	{
		return province;
	}

	public void setProvince(List<VAreaProvinceBean> province)
	{
		this.province = province;
	}

	/**
	 * 获取 省份信息
	 */
	public List<String> getProvinceNameList()
	{
		List<String> nameList = new ArrayList<>();
		for (VAreaProvinceBean bean : province)
		{
			nameList.add(bean.getP_name());
		}
		return nameList;
	}

	/**
	 * 获取 省份 code
	 *
	 * @param pName
	 * @return
	 */
	public String getProvinceCode(String pName)
	{
		if (TextUtils.isEmpty(pName))
		{
			return null;
		}

		for (VAreaProvinceBean bean : province)
		{
			if (pName.equals(bean.getP_name()))
			{
				return bean.getP_code();
			}
		}

		return null;
	}

	/**
	 * 获取 城市 code
	 *
	 * @param pName
	 * @param cName
	 * @return
	 */
	public String getCityCode(String pName, String cName)
	{
		if (TextUtils.isEmpty(pName) || TextUtils.isEmpty(cName))
		{
			return null;
		}

		for (VAreaProvinceBean bean : province)
		{
			if (pName.equals(bean.getP_name()))
			{
				for (VAreaCityBean cityBean : bean.getCity())
				{
					if (cName.equals(cityBean.getC_name()))
					{
						return cityBean.getC_code();
					}
				}
			}
		}

		return null;
	}

	/**
	 * 获取 城市信息
	 */
	public List<String> getCityNameList(String pCode)
	{
		List<String> nameList = new ArrayList<>();
		if (TextUtils.isEmpty(pCode))
		{
			return nameList;
		}

		for (VAreaProvinceBean bean : province)
		{
			if (pCode.equals(bean.getP_code()))
			{
				List<VAreaCityBean> cityList = bean.getCity();
				for (VAreaCityBean cityBean : cityList)
				{
					nameList.add(cityBean.getC_name());
				}
				return nameList;
			}
		}

		return nameList;
	}

	public class VAreaProvinceBean
	{
		private String p_name;

		private String p_code;

		private List<VAreaCityBean> city;

		public String getP_name()
		{
			return p_name;
		}

		public void setP_name(String p_name)
		{
			this.p_name = p_name;
		}

		public String getP_code()
		{
			return p_code;
		}

		public void setP_code(String p_code)
		{
			this.p_code = p_code;
		}

		public List<VAreaCityBean> getCity()
		{
			return city;
		}

		public void setCity(List<VAreaCityBean> city)
		{
			this.city = city;
		}
	}

	public class VAreaCityBean
	{
		private String c_name;

		private String c_code;

		public String getC_name()
		{
			return c_name;
		}

		public void setC_name(String c_name)
		{
			this.c_name = c_name;
		}

		public String getC_code()
		{
			return c_code;
		}

		public void setC_code(String c_code)
		{
			this.c_code = c_code;
		}
	}
}
