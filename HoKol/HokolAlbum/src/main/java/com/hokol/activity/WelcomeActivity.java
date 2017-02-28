package com.hokol.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hokol.R;
import com.hokol.base.common.BaseAppCompatActivity;
import com.hokol.base.log.LogFileUtil;
import com.hokol.medium.http.bean.RequestPhoneLoginBean;
import com.hokol.medium.http.bean.ResponsePhoneLoginBean;
import com.hokol.medium.http.xHttp;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首个欢迎界面
 *
 * @author yline 2017/2/8 --> 10:47
 * @version 1.0.0
 */
public class WelcomeActivity extends BaseAppCompatActivity
{
	// 跳转
	@OnClick(R.id.btn_action_main)
	public void btnActionMain()
	{
		MainActivity.actionStart(WelcomeActivity.this);
	}

	@BindView(R.id.et_main_username)
	public EditText etUserName;

	@BindView(R.id.et_main_password)
	public EditText etPassWord;

	@OnClick(R.id.btn_main_login)
	public void btnActionLogin()
	{
		String username = etUserName.getText().toString().trim();
		String password = etPassWord.getText().toString().trim();

		String httpHead = "http://192.168.1.100/index.php/Back/Api/";
		String httpParam = "login";

		LogFileUtil.v("username = " + username + ",password = " + password);
		LogFileUtil.v("httpUrl = " + httpHead + httpParam);

		doGet(httpHead + httpParam);
		doPost(httpHead + httpParam, new RequestPhoneLoginBean(username, password));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		ButterKnife.bind(this);
	}
	
	private void doGet(String httpUrl)
	{
		new xHttp<ResponsePhoneLoginBean>()
		{
			@Override
			public void onSuccess(ResponsePhoneLoginBean bean)
			{
				LogFileUtil.v(bean.toString());
				Toast.makeText(WelcomeActivity.this, bean.toString(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailureCode(int code)
			{
				Toast.makeText(WelcomeActivity.this, "code = " + code, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Exception ex)
			{
				Toast.makeText(WelcomeActivity.this, "ex = " + Log.getStackTraceString(ex), Toast.LENGTH_SHORT).show();
			}
		}.doRequest(httpUrl, ResponsePhoneLoginBean.class);
	}

	private void doPost(String httpUrl, RequestPhoneLoginBean param)
	{
		new xHttp<ResponsePhoneLoginBean>()
		{

			@Override
			public void onSuccess(ResponsePhoneLoginBean responsePhoneLoginBean)
			{
				Toast.makeText(WelcomeActivity.this, responsePhoneLoginBean.toString(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailureCode(int code)
			{
				super.onFailureCode(code);
				Toast.makeText(WelcomeActivity.this, "code = " + code, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFailure(Exception ex)
			{
				super.onFailure(ex);
				Toast.makeText(WelcomeActivity.this, "ex = " + Log.getStackTraceString(ex), Toast.LENGTH_SHORT).show();
			}
		}.doRequest(httpUrl, param, ResponsePhoneLoginBean.class);
	}

	@OnClick(R.id.btn_test)
	public void parse()
	{
		new Sample<DeleteBean>().test(new Tallback<DeleteBean>()
		{

			@Override
			public void onResult(DeleteBean bean)
			{
				LogFileUtil.v(bean.toString());
			}
		}, DeleteBean.class);

		LogFileUtil.v("");

		// Json 解析
		// String json = "{\"code\":1,\"data\":[]}";
		String json = "{\"code\":0,\"data\":{\"user_tel\":\"1\",\"user_pwd\":\"1\"}}";
		try
		{
			JSONObject jsonObject = new JSONObject(json);
			int code3 = jsonObject.getInt("code");
			LogFileUtil.v("code3 = " + code3);
			if (code3 == 0)
			{
				String json2 = jsonObject.getString("data");
				LogFileUtil.v("json2 = " + json2);
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static class Sample<Result>
	{
		public void test(Tallback<Result> back, Class<Result> clazz)
		{
			String json = "{\"code\":1,\"data\":[]}";
			// String json = "{\"code\":0,\"data\":{\"user_tel\":\"1\",\"user_pwd\":\"1\"}}";

			LogFileUtil.v("json = " + json);

			Gson gson = new Gson();

			CommenBean<Result> commenBean = gson.fromJson(json, new TypeToken<CommenBean<Result>>()
			{
			}.getType());

			LogFileUtil.v("commenBean = " + commenBean.toString());

			final int code2 = commenBean.getCode();

			if (code2 == 0)
			{
				Result finals = gson.fromJson(commenBean.getData().toString(), clazz);

				back.onResult(finals);
			}
		}
	}

	public class DeleteBean
	{
		private String user_tel;

		private String user_pwd;

		public String getUser_tel()
		{
			return user_tel;
		}

		public void setUser_tel(String user_tel)
		{
			this.user_tel = user_tel;
		}

		public String getUser_pwd()
		{
			return user_pwd;
		}

		public void setUser_pwd(String user_pwd)
		{
			this.user_pwd = user_pwd;
		}

		@Override
		public String toString()
		{
			return "DeleteBean [user_tel=" + user_tel + ", user_pwd=" + user_pwd + "]";
		}
	}

	public interface Tallback<T extends Object>
	{
		void onResult(T t);
	}

	public class CommenBean<T>
	{
		private int code;

		private T data;

		public int getCode()
		{
			return code;
		}

		public void setCode(int code)
		{
			this.code = code;
		}

		public T getData()
		{
			return data;
		}

		public void setData(T data)
		{
			this.data = data;
		}

		@Override
		public String toString()
		{
			return "CommenBean [code=" + code + ", data=" + data + "]";
		}
	}
}
