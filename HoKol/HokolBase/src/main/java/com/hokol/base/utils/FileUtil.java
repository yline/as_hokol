package com.hokol.base.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;

/**
 * 目前提供给 LogFileUtil准备
 * simple introduction
 * @author YLine 2016-5-25 -> 上午8:06:08
 */
public class FileUtil
{
	private static final String HIDDEN_PREFIX = ".";

	/**
	 * 获取内置sd卡最上层路径
	 * @return /storage/emulated/0/ or null
	 */
	public static String getPath()
	{
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
		{
			return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
		}
		else
		{
			return null;
		}
	}

	/**
	 * android.permission.WRITE_EXTERNAL_STORAGE
	 * 创建一个文件夹
	 * @param path such as /storage/emulated/0/Yline/Log/
	 * @return file or null
	 */
	public static File createFileDir(String path)
	{
		File pathFile = new File(path);

		if (!pathFile.exists())
		{
			if (!pathFile.mkdirs())
			{
				return null;
			}
		}
		return pathFile;
	}

	/**
	 * android.permission.WRITE_EXTERNAL_STORAGE
	 * 构建一个文件,真实的创建
	 * @param dir  文件的目录
	 * @param name 文件名     such as log.txt
	 * @return file or null
	 */
	public static File createFile(File dir, String name)
	{
		if (null == dir || TextUtils.isEmpty(name))
		{
			return null;
		}

		File file = new File(dir, name);
		if (!file.exists())
		{
			try
			{
				if (file.createNewFile())
				{
					return file;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		else
		{
			return file;
		}

		return null;
	}

	/**
	 * 是否存在该文件
	 * @param dir  文件目录
	 * @param name 文件名称
	 * @return false(参数错误、文件不存在)
	 */
	public static boolean isExist(File dir, String name)
	{
		if (null == dir || TextUtils.isEmpty(name))
		{
			return false;
		}

		return new File(dir, name).exists();
	}

	/**
	 * android.permission.WRITE_EXTERNAL_STORAGE
	 * 删除一个文件
	 * @param dir  文件的目录
	 * @param name 文件名  such as log.txt
	 * @return false(参数错误、不存在该文件、删除失败)
	 */
	public static boolean deleteFile(File dir, String name)
	{
		if (null == dir || TextUtils.isEmpty(name))
		{
			return false;
		}

		File file = new File(dir, name);
		if (file.exists())
		{
			return file.delete();
		}

		return false;
	}

	/**
	 * 重命名一个文件
	 * @param dir     文件的目录
	 * @param oldName 文件名  such as log0.txt
	 * @param newName 文件名  such as log1.txt
	 * @return false(参数错误、不存在该文件、重命名失败)
	 */
	public static boolean renameFile(File dir, String oldName, String newName)
	{
		if (null == dir || TextUtils.isEmpty(oldName))
		{
			return false;
		}

		File oldFile = new File(dir, oldName);
		// 不存在该文件,即算作命名成功
		if (oldFile.exists())
		{
			if (TextUtils.isEmpty(newName))
			{
				return false;
			}
			File newFile = new File(dir, newName);
			return oldFile.renameTo(newFile);
		}

		return false;
	}

	/**
	 * 之后统计乱码的情况(理论是不乱码的)
	 * 写内容到文件中,尾随后面写
	 * @param file    文件
	 * @param content 内容
	 * @return false(写入失败, 写入工具关闭失败)
	 */
	public static boolean writeToFile(File file, String content)
	{
		FileWriter fileWriter = null;
		try
		{
			fileWriter = new FileWriter(file, true);
			fileWriter.append(content + "\n");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (null != fileWriter)
			{
				try
				{
					fileWriter.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 之后统计乱码的情况(理论是不乱码的)
	 * 写内容到文件中,尾随后面写
	 * @param file    文件
	 * @param content 内容
	 * @return false(写入失败, 写入工具关闭失败)
	 */
	public static boolean writeToFileNew1(File file, String content)
	{
		PrintStream printStream = null;

		try
		{
			printStream = new PrintStream(new FileOutputStream(file, true), false, "utf-8");
			printStream.println(content);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			printStream.close();
		}
		return true;
	}

	/**
	 * 之后统计乱码的情况(理论是不乱码的)
	 * 写内容到文件中,尾随后面写
	 * @param file    文件
	 * @param content 内容
	 * @return false(写入失败, 写入工具关闭失败)
	 */
	public static boolean writeToFileNew2(File file, String content)
	{
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try
		{
			outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true), "utf-8");
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			bufferedWriter.append(content + "\n");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if (null != bufferedWriter)
				{
					bufferedWriter.close();
				}
				if (null != outputStreamWriter)
				{
					outputStreamWriter.close();

				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * File and folder comparator. TODO Expose sorting option method
	 * @author paulburke
	 */
	private static Comparator<File> sComparator = new Comparator<File>()
	{
		@Override
		public int compare(File f1, File f2)
		{
			// Sort alphabetically by lower case, which is much cleaner
			return f1.getName().toLowerCase().compareTo(
					f2.getName().toLowerCase());
		}
	};

	private static FileFilter sFileFilter = new FileFilter()
	{
		public boolean accept(File file)
		{
			return file.isFile();
		}
	};

	private static FileFilter sDirFilter = new FileFilter()
	{
		public boolean accept(File file)
		{
			return file.isDirectory();
		}
	};

	/**
	 * File (not directories) filter.
	 * @author paulburke
	 */
	private static FileFilter sFilePointFilter = new FileFilter()
	{
		@Override
		public boolean accept(File file)
		{
			final String fileName = file.getName();
			// Return files only (not directories) and skip hidden files
			return file.isFile() && !fileName.startsWith(HIDDEN_PREFIX);
		}
	};

	/**
	 * Folder (directories) filter.
	 * @author paulburke
	 */
	private static FileFilter sDirPointFilter = new FileFilter()
	{
		@Override
		public boolean accept(File file)
		{
			final String fileName = file.getName();
			// Return directories only and skip hidden directories
			return file.isDirectory() && !fileName.startsWith(HIDDEN_PREFIX);
		}
	};

	public static Comparator<File> getsComparator()
	{
		return sComparator;
	}

	public static String getHiddenPrefix()
	{
		return HIDDEN_PREFIX;
	}

	public static FileFilter getsFilePointFilter()
	{
		return sFilePointFilter;
	}

	public static FileFilter getsDirPointFilter()
	{
		return sDirPointFilter;
	}

	public static FileFilter getsFileFilter()
	{
		return sFileFilter;
	}

	public static FileFilter getsDirFilter()
	{
		return sDirFilter;
	}
}
