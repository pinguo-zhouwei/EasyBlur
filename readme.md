 
### EasyBlur
 
 EasyBlur 是对RenderScript 和fastBlur 2种方法的包装。
 使用RenderScript 和fastBlur 做图片的高斯模糊，默认使用RenderScript,可以通过属性设置使用fastBlur方式。
 
 效果图：
 
 ![效果图](simple.png)
 
### Gradle依赖

1, 最外层build.gradle 添加一下代码：

```java
allprojects {
    repositories {
        jcenter()
        
        maven {url "https://jitpack.io"}
    }
}
```

2，app 的build.gradle添加：

```java
  dependencies {
  compile 'com.github.pinguo-zhouwei:EasyBlur:v1.0.0'
}
```	

3,app 的build.gradle添加：

```java
 defaultConfig {
        applicationId "com.zhouwei.easyblur"
        minSdkVersion 16
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
         
        //使用renderscript 兼容包 
        renderscriptTargetApi 25
        renderscriptSupportModeEnabled true
    }
```
### 使用方法
1，简单使用，指定Bitmap和半径
```java
Bitmap finalBitmap = EasyBlur.with(MainActivity.this)
                        .bitmap(overlay) //要模糊的图片
                        .radius(10)//模糊半径
                        .blur();
```
2,可以指定缩小的倍数，默认缩小倍数为8
```java
Bitmap finalBitmap = EasyBlur.with(MainActivity.this)
                        .bitmap(overlay) //要模糊的图片
                        .radius(10)//模糊半径
                        .scale(4)//指定模糊前缩小的倍数
                        .blur();
```
3, 指定使用哪一种方法,默认是使用兼容的RenderScript 高斯模糊
```java
Bitmap finalBitmap = EasyBlur.with(MainActivity.this)
                        .bitmap(overlay) //要模糊的图片
                        .radius(10)//模糊半径
                        .scale(4)//指定模糊前缩小的倍数
                        .policy(EasyBlur.BlurPolicy.FAST_BLUR)//使用fastBlur
                        .blur();
```
### 博客

	
	
