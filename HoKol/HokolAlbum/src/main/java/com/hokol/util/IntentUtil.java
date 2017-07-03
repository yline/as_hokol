package com.hokol.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.yline.log.LogFileUtil;
import com.yline.utils.FileUtil;

public class IntentUtil
{
	/**
	 * 拍照失败，则data不为null，其它值全部为null
	 * 拍照成功，则data为null
	 *
	 * @param activity
	 * @param fileName    暂存的图片名， camera_picture.jpg
	 * @param requestCode
	 * @return
	 */
	public static boolean openCamera(Activity activity, String fileName, int requestCode)
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		Uri outputUri = Uri.fromFile(FileUtil.create(activity.getExternalCacheDir(), fileName));
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri); // 存放位置为sdcard卡上cwj文件夹，文件名为android123.jpg格式
		if (null != intent.resolveActivity(activity.getPackageManager()))
		{
			activity.startActivityForResult(intent, requestCode);
			return true;
		}
		else
		{
			LogFileUtil.e("IntentUtil", "camera do not exist");
			return false;
		}
	}

	/**
	 * 拍照失败，则data不为null，其它值全部为null
	 * 拍照成功，则data为null
	 *
	 * @param fragment    第二级fragment开始, 无法接收到信息
	 * @param fileName    暂存的图片名， camera_picture.jpg
	 * @param requestCode
	 * @return
	 */
	public static boolean openCamera(Fragment fragment, String fileName, int requestCode)
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		Uri outputUri = Uri.fromFile(FileUtil.create(fragment.getContext().getExternalCacheDir(), fileName));
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri); // 存放位置为sdcard卡上cwj文件夹，文件名为android123.jpg格式
		if (null != intent.resolveActivity(fragment.getContext().getPackageManager()))
		{
			fragment.startActivityForResult(intent, requestCode);
			return true;
		}
		else
		{
			LogFileUtil.e("IntentUtil", "camera do not exist");
			return false;
		}
	}

	/**
	 * 选择成功，返回data不为null, data.getdata 为null
	 * 选择失败，返回data为null
	 *
	 * @param activity
	 * @param requestCode
	 * @return
	 */
	public static boolean openAlbum(Activity activity, int requestCode)
	{
		Intent tempIntent = new Intent();
		tempIntent.setAction(Intent.ACTION_PICK);
		tempIntent.setType("image/*");
		if (null != tempIntent.resolveActivity(activity.getPackageManager()))
		{
			activity.startActivityForResult(tempIntent, requestCode);
			return true;
		}
		else
		{
			LogFileUtil.e("IntentUtil", "album do not exist");
			return false;
		}
	}

	/**
	 * 选择成功，返回data不为null, data.getdata 为null
	 * 选择失败，返回data为null
	 *
	 * @param fragment    第二级fragment开始, 无法接收到信息
	 * @param requestCode
	 * @return
	 */
	public static boolean openAlbum(Fragment fragment, int requestCode)
	{
		Intent tempIntent = new Intent();
		tempIntent.setAction(Intent.ACTION_PICK);
		tempIntent.setType("image/*");
		if (null != tempIntent.resolveActivity(fragment.getActivity().getPackageManager()))
		{
			fragment.startActivityForResult(tempIntent, requestCode);
			return true;
		}
		else
		{
			LogFileUtil.e("IntentUtil", "album do not exist");
			return false;
		}
	}

	/**
	 * 请求相册，并裁剪
	 * 选择成功，返回data不为null
	 * 选择失败，返回data为null
	 *
	 * @param activity
	 * @param uri         onActivityResult 返回的结果
	 * @param fileName    缓存本地的文件名
	 * @param requestCode
	 * @return
	 */
	public static boolean openPictureZoom(Activity activity, Uri uri, String fileName, int requestCode)
	{
		Intent tempIntent = new Intent("com.android.camera.action.CROP");

		tempIntent.setDataAndType(uri, "image/*");
		tempIntent.putExtra("crop", "true");

		Uri outputUri = Uri.fromFile(FileUtil.create(activity.getExternalCacheDir(), fileName));
		tempIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);// 图像输出
		tempIntent.putExtra("aspectX", 3); // 边长比例
		tempIntent.putExtra("aspectY", 3);
		tempIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		tempIntent.putExtra("noFaceDetection", true);
		tempIntent.putExtra("return-data", false);// 回调方法data.getExtras().getParcelable("data")返回数据为空
		if (null != tempIntent.resolveActivity(activity.getPackageManager()))
		{
			activity.startActivityForResult(tempIntent, requestCode);
			return true;
		}
		else
		{
			LogFileUtil.e("IntentUtil", "album zoom do not exist");
			return false;
		}
	}

	/**
	 * 请求相册，并裁剪
	 * 选择成功，返回data不为null
	 * 选择失败，返回data为null
	 *
	 * @param fragment    第二级fragment开始, 无法接收到信息
	 * @param uri         onActivityResult 返回的结果
	 * @param fileName    缓存本地的文件名
	 * @param requestCode
	 * @return
	 */
	public static boolean openPictureZoom(Fragment fragment, Uri uri, String fileName, int requestCode)
	{
		Intent tempIntent = new Intent("com.android.camera.action.CROP");

		tempIntent.setDataAndType(uri, "image/*");
		tempIntent.putExtra("crop", "true");

		Uri outputUri = Uri.fromFile(FileUtil.create(fragment.getContext().getExternalCacheDir(), fileName));
		tempIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);// 图像输出
		tempIntent.putExtra("aspectX", 3); // 边长比例
		tempIntent.putExtra("aspectY", 3);
		tempIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		tempIntent.putExtra("noFaceDetection", true);
		tempIntent.putExtra("return-data", false);// 回调方法data.getExtras().getParcelable("data")返回数据为空
		if (null != tempIntent.resolveActivity(fragment.getContext().getPackageManager()))
		{
			fragment.startActivityForResult(tempIntent, requestCode);
			return true;
		}
		else
		{
			LogFileUtil.e("IntentUtil", "album zoom do not exist");
			return false;
		}
	}

	/**
	 * 浏览器
	 *
	 * @param website http:// website
	 * @return
	 */
	public static void openBrower(Context context, String website)
	{
		Intent tempIntent = new Intent();
		tempIntent.setAction(Intent.ACTION_VIEW);

		Uri tempUri;
		if ("http://".equals(website.substring(0, 7)) || "https://".equals(website.substring(0, 8)))
		{
			tempUri = Uri.parse(website);
		}
		else
		{
			tempUri = Uri.parse("https://" + website);
		}
		tempIntent.setData(tempUri);

		// 跳转
		if (null != tempIntent.resolveActivity(context.getPackageManager()))
		{
			context.startActivity(tempIntent);
		}
		else
		{
			LogFileUtil.e("IntentUtil", "Brower zoom do not exist");
		}
	}
}
