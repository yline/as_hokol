# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\study_adt_studio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
######################################### 以下是固定的 ############################################
######################################### 压缩 ############################################
#指定代码的压缩级别
-optimizationpasses 5

######################################### 优化 ############################################
 #不优化输入的类文件
-dontoptimize

######################################### 混淆配置 ############################################
#包名不混合大小写；混淆时，不会产生形形色色的类名
-dontusemixedcaseclassnames

#指定 不去忽略非公共的库类；是否混淆第三方jar
-dontskipnonpubliclibraryclasses

#混淆时，不做预校验
-dontpreverify

#混淆时是否记录日志
-verbose

# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护给定的可选属性，例如LineNumberTable, LocalVariableTable, SourceFile, Deprecated, Synthetic, Signature, InnerClass
-keepattributes Signature
-keepattributes *Annotation*

#忽略警告，避免打包时某些警告出现
#-ignorewarning

##记录生成的日志数据,gradle build时在本项目根目录输出##
#apk 包内所有 class 的内部结构
-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
-printusage unused.txt
#混淆前后的映射
-printmapping mapping.txt
########记录生成的日志数据，gradle build时 在本项目根目录输出-end######

#忽略警告
-dontwarn com.lippi.recorder.utils**

#如果引用了v4或者v7包
-dontwarn android.support.**

######################################### 保持哪些类不被混淆 ############################################
#不混淆某个类 的子类
-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**

######################################### 保持哪些类不被混淆 ############################################
#Gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson 下面替换成自己的实体类
-keep class com.hokol.medium.http.bean.** { *; }

#OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#保持 native 方法不被混淆
#  -keepclasseswithmembernames class * {
#       native <methods>;
#  }

#保持自定义控件类不被混淆
#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet);
#}

# 保持自定义控件类不被混淆
#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}

#保持类成员
#-keepclassmembers class * extends android.app.Activity {
#   public void *(android.view.View);
#}

#保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
#-keepclassmembers enum * {
#  public static **[] values();
#  public static ** valueOf(java.lang.String);
#}

#保持 Parcelable 不被混淆
#-keep class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator *;
#}

#保持 Serializable 不被混淆
#-keepnames class * implements java.io.Serializable

#保留一个完整的包
#-keep class com.lippi.recorder.utils.** {
#    *;
#}

#如果不想混淆 keep 掉
#-keep class MyClass;

######################################### 第三方库 ############################################
#gson
#-keepattributes *Annotation*   上面已经有了
#-keep class com.google.gson.** {
# *;
#}

#-keep class com.google.gson.examples.android.model.** { *; }

#-keep class com.hokol.medium.http.** {
# *;
#}

#-keep class com.yline.** {
# *;
#}

#-keep class com.** {
# *;
#}

#-keep class jp.** {
# *;
#}

#-keep class android.support.** {
# *;
#}

#-keep class okio.** {
# *;
#}

#-keep class okhttp3.** {
# *;
#}

#####混淆保护自己项目的部分代码以及引用的第三方jar包library#######
#-libraryjars libs/umeng-analytics-v5.2.4.jar
#三星应用市场需要添加:sdk-v1.0.0.jar,look-v1.0.1.jar
#-libraryjars libs/sdk-v1.0.0.jar
#-libraryjars libs/look-v1.0.1.jar

#-keepnames class com.hokol.config.glide.HokolGlideModule
#-keep public class * implements com.bumptech.glide.module.GlideModule