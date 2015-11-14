//package soft.zzti.edu.cn.ruxuebaodian.jsontools;
//
//import android.content.Context;
//import com.android.volley.toolbox.ImageLoader;
//
///**
// * Created by zhaoxiangyu on 2015/11/11.
// */
//public class ImageLoaderPicture {
//    private DisplayImageOptions options;
//
//    public ImageLoaderPicture(Context context) {
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
//                .denyCacheImageMultipleSizesInMemory()
//                .discCacheFileNameGenerator(new Md5FileNameGenerator())
//                .tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging()
//                .memoryCache(new WeakMemoryCache())
//                .build();
//        ImageLoader.getInstance().init(config);
//
//        options = new DisplayImageOptions.Builder()
//                .showStubImage(0)
//                .showImageForEmptyUri(0)
//                .showImageOnFail(0)
//                .cacheInMemory().cacheOnDisc()
//                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//                .bitmapConfig(android.graphics.Bitmap.Config.RGB_565)
//                .build();
//    }
//
//    public DisplayImageOptions getOptions() {
//        return options;
//    }
//
//    public void setOptions(DisplayImageOptions options) {
//        this.options = options;
//    }
//}
