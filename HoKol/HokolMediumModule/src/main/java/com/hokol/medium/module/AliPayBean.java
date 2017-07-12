package com.hokol.medium.module;

public class AliPayBean
{
	/**
	 * 描述信息(类型为字符串)
	 */
	public static final String KeyMemo = "memo";

	/**
	 * 返回结果
	 */
	public static final String KeyResult = "result";

	/**
	 * 9000 订单支付成功
	 * 8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
	 * 4000	订单支付失败
	 * 5000	重复请求
	 * 6001	用户中途取消
	 * 6002	网络连接出错
	 * 6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
	 * 其它	其它支付错误
	 */
	public static final String KeyResultStatus = "resultStatus";

	private String sign;

	private String sign_type;

	private AliPayResponse alipay_trade_app_pay_response;

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	public String getSign_type()
	{
		return sign_type;
	}

	public void setSign_type(String sign_type)
	{
		this.sign_type = sign_type;
	}

	public AliPayResponse getAlipay_trade_app_pay_response()
	{
		return alipay_trade_app_pay_response;
	}

	public void setAlipay_trade_app_pay_response(AliPayResponse alipay_trade_app_pay_response)
	{
		this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
	}

	public static class AliPayResponse
	{
		/**
		 * 结果码  详情参见：https://docs.open.alipay.com/common/105806
		 */
		private int code;

		/* 	处理结果的描述，信息来自于code返回结果的描述  	success */
		private String msg;

		/* 	支付宝分配给开发者的应用Id。  2014072300007148 */
		private String app_id;

		/* 商户网站唯一订单号  70501111111S001111119  */
		private String out_trade_no;

		/* 	该交易在支付宝系统中的交易流水号。最长64位。 2014112400001000340011111118  */
		private String trade_no;

		/* 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01,100000000.00]，精确到小数点后两位。  9.00 */
		private String total_amount;

		/* 收款支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字  2088111111116894 */
		private String seller_id;

		/* 编码格式  utf-8 */
		private String charset;

		/* 	时间 	2016-10-11 17:43:36  */
		private String timestamp;

		public int getCode()
		{
			return code;
		}

		public void setCode(int code)
		{
			this.code = code;
		}

		public String getMsg()
		{
			return msg;
		}

		public void setMsg(String msg)
		{
			this.msg = msg;
		}

		public String getApp_id()
		{
			return app_id;
		}

		public void setApp_id(String app_id)
		{
			this.app_id = app_id;
		}

		public String getOut_trade_no()
		{
			return out_trade_no;
		}

		public void setOut_trade_no(String out_trade_no)
		{
			this.out_trade_no = out_trade_no;
		}

		public String getTrade_no()
		{
			return trade_no;
		}

		public void setTrade_no(String trade_no)
		{
			this.trade_no = trade_no;
		}

		public String getTotal_amount()
		{
			return total_amount;
		}

		public void setTotal_amount(String total_amount)
		{
			this.total_amount = total_amount;
		}

		public String getSeller_id()
		{
			return seller_id;
		}

		public void setSeller_id(String seller_id)
		{
			this.seller_id = seller_id;
		}

		public String getCharset()
		{
			return charset;
		}

		public void setCharset(String charset)
		{
			this.charset = charset;
		}

		public String getTimestamp()
		{
			return timestamp;
		}

		public void setTimestamp(String timestamp)
		{
			this.timestamp = timestamp;
		}
	}
}
