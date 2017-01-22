package com.example.testrecyclerviewdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by HaLoner on 2015/12/1.
 * 选择文件对话框
 * 摘自http://blog.csdn.net/herorenme/article/details/8506692
 */
public class OpenPicFileDialog {
    static final public String sRoot = "/";
    static final public String sParent = "..";
    static final public String sFolder = ".";
    static final public String sEmpty = "";
    static final private String sOnErrorMsg = "No rights to access!";

    public interface CallbackBundle {
        void callback(Bundle bundle);
    }

    // 参数说明
    // context:上下文
    // dialogid:对话框ID
    // title:对话框标题
    // callback:一个传递Bundle参数的回调接口
    // suffix:需要选择的文件后缀，比如需要选择wav、mp3文件的时候设置为".wav;.mp3;"，注意最后需要一个分号(;)
    // images:用来根据后缀显示的图标资源ID。
    //  根目录图标的索引为sRoot;
    //  父目录的索引为sParent;
    //  文件夹的索引为sFolder;
    //  默认图标的索引为sEmpty;
    //  其他的直接根据后缀进行索引，比如.wav文件图标的索引为"wav"
    public static Dialog createDialog(Context context, String title, final CallbackBundle callback, String suffix, Map<String, Integer> images) {
        final FileSelectView fileSelectView = new FileSelectView(context, callback, suffix, images);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(fileSelectView);
        if (suffix.equals(".png;.jpg;")) {
            builder.setPositiveButton("全选", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 1; i < fileSelectView.mList.size(); i++) {
                        String path = fileSelectView.getPicPath(i);
                        if (path != null) {
                            list.add(path);
                        }
                    }
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("path", list);
                    fileSelectView.mCallback.callback(bundle);
                }
            });
            builder.setNegativeButton("取消", null);
        }
        Dialog dialog = builder.create();
        dialog.setTitle(title);
        fileSelectView.setDialog(dialog);
        return dialog;
    }

    static class FileSelectView extends ListView implements OnItemClickListener {
        private CallbackBundle mCallback = null;
        private String mPath = sRoot;
        public List<Map<String, Object>> mList = null;
        private Dialog mDialog;
        private String mSuffix = null;
        private Map<String, Integer> mImageMap = null;

        public FileSelectView(Context context) {
            super(context);
        }

        public FileSelectView(Context context, CallbackBundle callback, String suffix, Map<String, Integer> images) {
            super(context);
            mImageMap = images;
            mSuffix = suffix == null ? "" : suffix.toLowerCase();
            mCallback = callback;
            setOnItemClickListener(this);
            refreshFileList();
        }

        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }

        private String getSuffix(String filename) {
            int dix = filename.lastIndexOf('.');
            if (dix < 0) {
                return "";
            } else {
                return filename.substring(dix + 1);
            }
        }

        private int getImageId(String s) {
            if (mImageMap == null) {
                return 0;
            } else if (mImageMap.containsKey(s)) {
                return mImageMap.get(s);
            } else if (mImageMap.containsKey(sEmpty)) {
                return mImageMap.get(sEmpty);
            } else {
                return 0;
            }
        }

        private int refreshFileList() {
            // 刷新文件列表
            File[] files;
            try {
                files = new File(mPath).listFiles();
            } catch (Exception e) {
                files = null;
            }
            if (files == null) {
                // 访问出错
                Toast.makeText(getContext(), sOnErrorMsg, Toast.LENGTH_SHORT).show();
                return -1;
            }
            if (mList != null) {
                mList.clear();
            } else {
                mList = new ArrayList<>(files.length);
            }

            // 用来先保存文件夹和文件夹的两个列表
            ArrayList<Map<String, Object>> folderList = new ArrayList<>();
            ArrayList<Map<String, Object>> fileList = new ArrayList<>();

            if (!this.mPath.equals(sRoot)) {
                // 添加根目录 和 上一层目录
                Map<String, Object> map = new HashMap<>();
                map.put("name", sRoot);
                map.put("path", sRoot);
                map.put("img", getImageId(sRoot));
                mList.add(map);

                map = new HashMap<>();
                map.put("name", sParent);
                map.put("path", mPath);
                map.put("img", getImageId(sParent));
                mList.add(map);
            }

            for (File file : files) {
                if (file.isDirectory() && file.listFiles() != null) {
                    // 添加文件夹
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", file.getName());
                    map.put("path", file.getPath());
                    map.put("img", getImageId(sFolder));
                    folderList.add(map);
                } else if (file.isFile()) {
                    // 添加文件
                    String sf = getSuffix(file.getName()).toLowerCase();
                    if (mSuffix == null || mSuffix.length() == 0 || (sf.length() > 0 && mSuffix.contains("." + sf + ";"))) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", file.getName());
                        map.put("path", file.getPath());
                        map.put("img", getImageId(sf));
                        fileList.add(map);
                    }
                }
            }

            mList.addAll(folderList); // 先添加文件夹，确保文件夹显示在上面
            mList.addAll(fileList);    //再添加文件


            SimpleAdapter adapter = new SimpleAdapter(getContext(), mList, R.layout.file_dialog_item, new String[]{"img", "name", "path"}, new int[]{R.id.file_dialog_item_img, R.id.file_dialog_item_name, R.id.file_dialog_item_path});
            this.setAdapter(adapter);
            return files.length;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            // 条目选择
            String pt = (String) mList.get(position).get("path");
            String fn = (String) mList.get(position).get("name");
            if (fn.equals(sRoot) || fn.equals(sParent)) {
                // 如果是更目录或者上一层
                File fl = new File(pt);
                String ppt = fl.getParent();
                if (ppt != null) {
                    // 返回上一层
                    mPath = ppt;
                } else {
                    // 返回更目录
                    mPath = sRoot;
                }
            } else {
                File fl = new File(pt);
                if (fl.isFile()) {
                    // 如果是文件,让文件夹对话框消失
                    mDialog.dismiss();
                    // 设置回调的返回值
                    Bundle bundle = new Bundle();
                    bundle.putString("path", pt);
                    bundle.putString("name", fn);
                    // 调用事先设置的回调函数
                    this.mCallback.callback(bundle);
                    return;
                } else if (fl.isDirectory()) {
                    // 如果是文件夹
                    // 那么进入选中的文件夹
                    mPath = pt;
                }
            }
            this.refreshFileList();
        }

        public String getPicPath(int position) {
            // 条目选择
            String pt = (String) mList.get(position).get("path");
//            String fn = (String) mList.get(position).get("name");
            File fl = new File(pt);
            if (fl.isFile()) {
                return pt;
            }
            return null;
        }
    }
}