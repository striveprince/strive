package com.cutv.ningbo.uim.bindingAdapter;

/**
 * Created by maomao on 2017/4/25.
 */

public class GalleryFlowBindingAdapter {

//
//    @BindingAdapter({"init_data", "set_angle", "set_zoom", "set_space"})
//    public static void initData(GalleryFlow galleryFlow, List<? extends ItemLayoutModel> bitmapsUrl, int angle, int zoom, int space) {
//        ArrayList<BitmapDrawable> bitmaps = new ArrayList<BitmapDrawable>();
//
//        Context mContext = galleryFlow.getContext();
//
//        for (ItemLayoutModel model:bitmapsUrl){
//            Glide.with(mContext).load(model.getPagerTitle()).asBitmap().into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    BitmapDrawable drawable = new BitmapDrawable(resource);
//                    drawable.setAntiAlias(true);
//                    bitmaps.add(drawable);
//                    if(bitmaps.size() == bitmapsUrl.size())
//                        setAdapter(galleryFlow, angle, zoom, space, bitmaps);
//                }
//            });
//        }
//
//    }
//
//    private static void setAdapter(final GalleryFlow galleryFlow, int angle, int zoom, int space, ArrayList<BitmapDrawable> bitmaps) {
//        GalleryAdapter galleryAdapter = new GalleryAdapter(bitmaps);
//        galleryFlow.setMaxRotationAngle(angle);
//        galleryFlow.setMaxZoom(zoom);
//        galleryFlow.setSpacing(space);
//        galleryFlow.setAdapter(galleryAdapter);
////        galleryAdapter.notifyDataSetChanged();
//
//    }
}

