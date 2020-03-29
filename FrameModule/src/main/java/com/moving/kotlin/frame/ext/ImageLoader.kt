package com.moving.kotlin.frame.ext

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.moving.kotlin.frame.base.BaseActivity
import com.moving.kotlin.frame.utils.StringSignature
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.io.File
import java.net.URL
/**
 * load String
 */
fun ImageView.loadImageSafely(url: String?,activity: BaseActivity){
    if(!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed)){
        Glide.with(this.context).load(url).into(this)
    }
}
/**
 * load String
 */
fun ImageView.loadImage(url: String?){
    Glide.with(this.context).load(url).into(this)
}


fun ImageView.loadImage(url: String?, rounder: Int) {
    Glide.with(this.context).load(url).apply(RequestOptions.bitmapTransform(RoundedCorners(rounder)).override(0)).into(this)
}

/**
 * load 字节流
 */
fun ImageView.loadImage(byte: ByteArray?) = Glide.with(this.context).load(byte).into(this)

fun ImageView.loadImage(byte: ByteArray?, rounder: Int) {
    Glide.with(this.context).load(byte).apply(RequestOptions.bitmapTransform(RoundedCorners(rounder)).override(0)).into(this)
}

/**
 * load Drawable
 */
fun ImageView.loadImage(drawable: Drawable?) = Glide.with(this.context).load(drawable).into(this)

fun ImageView.loadImage(drawable: Drawable?, rounder: Int) {
    Glide.with(this.context).load(drawable).apply(RequestOptions.bitmapTransform(RoundedCorners(rounder)).override(0)).into(this)
}

/**
 * load Bitmap
 */
fun ImageView.loadImage(bitmap: Bitmap?) = Glide.with(this.context).load(bitmap).into(this)

fun ImageView.loadImage(bitmap: Bitmap?, rounder: Int) {
    Glide.with(this.context).load(bitmap).apply(RequestOptions.bitmapTransform(RoundedCorners(rounder)).override(0)).into(this)
}

/**
 * load Uri
 */
fun ImageView.loadImage(uri: Uri?) = Glide.with(this.context).load(uri).into(this)

fun ImageView.loadImage(uri: Uri?, rounder: Int) {
    Glide.with(this.context).load(uri).apply(RequestOptions.bitmapTransform(RoundedCorners(rounder)).override(0)).into(this)
}

/**
 * load File
 */
fun ImageView.loadImage(file: File?) = Glide.with(this.context).load(file).into(this)

fun ImageView.loadImage(file: File?, rounder: Int) {
    Glide.with(this.context).load(file).apply(RequestOptions.bitmapTransform(RoundedCorners(rounder)).override(0)).into(this)
}

/**
 * load resource ID
 */
fun ImageView.loadImage(resourceId: Int?) = Glide.with(this.context).load(resourceId).into(this)
fun ImageView.loadImage(resourceId: Int?, rounder: Int) {
    Glide.with(this.context).load(resourceId).apply(RequestOptions.bitmapTransform(RoundedCorners(rounder)).override(0)).into(this)
}

/**
 * load URL
 */
fun ImageView.loadImage(url: URL?) = Glide.with(this.context).load(url).into(this)

fun ImageView.loadImage(url: URL?, rounder: Int) {
    Glide.with(this.context).load(url).apply(RequestOptions.bitmapTransform(RoundedCorners(rounder)).override(0)).into(this)
}

/**
 * 带有占位符的
 */
fun ImageView.loadImageCenterCrop(uri: String?, @DrawableRes holder: Int? = null) {
    Glide.with(this).load(uri).apply(RequestOptions().dontAnimate().dontTransform().centerCrop().apply {
        if (holder != null) {
            this.placeholder(holder)
        }
    }).into(this)
}

/**
 * 加载图片,使用占位符
 */
fun ImageView.loadImageWithHolder(uri: String?, @DrawableRes holder: Int) {
    Glide.with(this).load(uri).apply(RequestOptions.placeholderOf(holder)).into(this)
}

/**
 * 加载图片并进行模糊,使用占位符
 */
fun ImageView.loadImageWithBlur(uri: String?, holder: Int) {
    Glide.with(this).load(uri).apply(bitmapTransform(BlurTransformation(25))).placeholder(holder).into(this)
}


/**
 * 加载gif图片文件
 */
fun ImageView.loadGif(uri: String?, requestListener: RequestListener<GifDrawable?>? = null, centerCrop: Boolean? = null, @DrawableRes holder: Int? = null) {
    var requestOptions = RequestOptions().dontTransform()
    if (centerCrop != null) {
        requestOptions = requestOptions.centerCrop()
    }
    if (holder != null) {
        requestOptions = requestOptions.placeholder(holder)
    }
    if (requestListener != null) {
        Glide.with(this).asGif().load(uri).apply(requestOptions).listener(requestListener).into(this)
    } else {
        Glide.with(this).asGif().load(uri).apply(requestOptions).into(this)
    }
}


/**
 * 加载视频文件
 */
fun ImageView.loadVideo(uri: String, @DrawableRes holder: Int) {
    Glide.with(this).load(uri).apply(RequestOptions().frame(0)
            .centerCrop().placeholder(holder).dontAnimate()).into(this)
}

fun ImageView.loadSticker(uri: String?, type: String?) {
    uri?.let {
        when (type) {
            "GIF" -> {
                loadGif(uri)
            }
            else -> loadImage(uri)
        }
    }
}

/**
 * 加载base64编码的图片
 */
fun ImageView.loadBase64(uri: ByteArray?, width: Int, height: Int, mark: Int) {
    val multi = MultiTransformation(CropTransformation(width, height))
    Glide.with(this).load(uri)
            .apply(RequestOptions().centerCrop()
                    .transform(multi).signature(StringSignature("$uri$mark"))
                    .dontAnimate()).into(this)
}

/**
 * 加载base64编码的图片
 */
fun ImageView.loadBase64(uri: ByteArray?) {
    Glide.with(this).load(uri)
            .apply(RequestOptions()
                    .dontAnimate()).into(this)
}
/**
 * 加载圆形图片
 */
fun ImageView.loadCircleImage(uri: String?, @DrawableRes holder: Int? = null) {
    if (uri.isNullOrBlank()) {
        if (holder != null) {
            setImageResource(holder)
        }
    } else if (holder == null) {
        Glide.with(this).load(uri).apply(RequestOptions().circleCrop()).into(this)
    } else {
        Glide.with(this).load(uri).apply(RequestOptions().placeholder(holder).circleCrop()).into(this)
    }
}

fun ImageView.loadRoundImage(resourceId: Int?) = Glide.with(this.context).load(resourceId).apply(RequestOptions().circleCrop()).into(this)

/**
 * 加载圆角图片
 */
fun ImageView.loadRoundImage(uri: String?, radius: Int, @DrawableRes holder: Int? = null) {
    if (uri.isNullOrBlank() && holder != null) {
        setImageResource(holder)
    } else if (holder == null) {
        Glide.with(this).load(uri).apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(radius, 0))).into(this)
    } else {
        Glide.with(this).load(uri).apply(RequestOptions().transform(RoundedCornersTransformation(radius, 0))
                .placeholder(holder))
                .into(this)
    }
}
