package com.example.myregexdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity {

    String poem = "Twas brillig, and the slithy toves/n" +
            "Did gyre and gimble in the wabe./n" +
            "All mimsy were the borogoves,/n" +
            "And the mome raths outgrabe./n/n" +
            "Beware the Jabberwock, my son,/n" +
            "The jaws that bite, the claws that catch./n" +
            "Beware the Jubjub bird, and shun/n" +
            "The frumious Bandersnatch.";

    //女性频道下面的所有分类
    static String AllSubSort = "{美容,UN1070801},{瘦身,UN1070802},{首饰,UN1070803},{服饰,UN1070804},{配饰,UN1070805},"
            + "{瑜伽,UN1090102},{居家,UN10709},{装饰,UN1070901},{园艺,UN1070902},{爱情,UN1070106},{夫妻,UN1070101},{婆媳,UN1070102}"
            + ",{育儿,UN10703},{营养,UN1100205},{美食,UN107070204},{药膳,UN110080101},{保健,UN1100304},{心理,UN1100107},{妇科,UN1100507}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
//        setTimeFormat();
//        strToLong();
//        find();
//        findRepeat();
//        tv.setText(findIp());
        tv.setText(initList());
//        replace();
//        isMail();
//        clearHtml();


//        String[] arr=AllSubSort.split(",");
//        Matcher m =
//                Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))___FCKpd___6quot;")
//                        .matcher(poem);
//        while (m.find()) {
//            for (int j = 0; j <= m.groupCount(); j++)
//                Log.e("babanana", "[" + m.group(j) + "]");
//            System.out.println();
//        }
    }

    private void clearHtml() {
        Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher("<a href=/"+"index.html/"+">主页</a>");
        String string = matcher.replaceAll("");
        Log.e("babanana", string);
    }

    private void isMail() {
        String str="ceponline@yahoo.com.cn";
        Pattern pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        Log.e("babanana", String.valueOf(matcher.matches()));
    }

    private void replace() {
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
        while (matcher.find()){
            Log.e("babanana",matcher.replaceAll("Didi"));
        }
//        StringBuffer sbr = new StringBuffer();
//        while (matcher.find()) {
//            matcher.appendReplacement(sbr, "Java");
//        }
//        matcher.appendTail(sbr);
//        Log.e("babanana",sbr.toString());
    }

    private void findRepeat() {
        String str="1988,1266,1699,1563,1987,1899,2020,2033,205,255,2055,15,20,198，1919,$$19,19$$21,21$,$$21";
        String format="(19|20)\\d{2}";// \\d{2} :\\d 代表0-9之间的数字，{n} 重复n次，
        // 如果{}前面是具体的字符，那么就是重复n次那个字符，如果{}前面是“\\d”这种，就是任意两个数字
        Pattern pattern=Pattern.compile(format);
        Pattern pattern2=Pattern.compile("\\w+\\${2}\\w+");
        Pattern pattern3=Pattern.compile("(19){2}");
        Matcher matcher=pattern.matcher(str);
        Matcher matcher2=pattern2.matcher(str);
        Matcher matcher3=pattern3.matcher(str);
        while (matcher.find()){
            Log.e("babanana",matcher.group());
        }
        while (matcher2.find()){
            Log.e("babanana",matcher2.group());
        }
        while (matcher3.find()){
            Log.e("babanana",matcher3.group());
        }
    }

    private String findIp() {
        String format="\\b" +
                "(" +
                "((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))" +
                "\\.){3}" +
                "((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))" +
                "\\b";
        String str="1.1.1.1234|1235.123.123.123|12.123.1234.1|255.255.255.255";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(str);
        String ip="";
        while (matcher.find()) {
            ip+=matcher.group()+" | ";
            Log.e("babanana", matcher.group(0));
        }
        return ip;
    }

    private void find() {
        Map<String, String> map = new HashMap<>();
        map.put("0", "类型一");
        map.put("1", "类型二");
        map.put("2", "类型三");
        Log.e("babanana", map.toString());

        String str = "\"evts\":[{\"id\":1,\"title\":\"test001\",\"start\":\"Mon Jan 27 2014 16:35:57 GMT+0800 (中国标准时间)\"," +
                "\"rem\":\"\",\"ad\":\"\",\"loc\":\"\",\"notes\":\"\",\"cid\":1,\"end\":\"Mon Jan 27 2014 16:35:57 GMT+0800 (中国标准时间)\"," +
                "\"url\":\"\"}]匹配不到";
        String s1 = "(?<=\\:\")[^\"]+";   //+：如果没有+，返回“M”，如果有+：返回“Mon Jan 27 2014 16:35:57 GMT+0800 (中国标准时间)”
        Pattern pattern = Pattern.compile(s1);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            Log.e("babanana", matcher.group());
        }

        String s2 = "[0:类型一,1:类型二,3:类型三]";
//        String s3 = s2.substring(1, s2.length() - 1);
//        String s3 = s2.replaceAll("\\[|\\]","");
//        String[] s4 = s3.split(",");
//        String[] s5 = new String[s4.length];
//        for (int i = 0; i < s4.length; i++) {
//            Log.e("babanana", s4[i]);
//            s5[i]=s4[i].substring(2);
//            Log.e("babanana", s5[i]);
//        }
        String string = "";
        String s6 = "(?<=\\:)\\w+\\b";
        Pattern pattern2 = Pattern.compile(s6);
        Matcher matcher2 = pattern2.matcher(s2);
        String s7 = "";
        List<String> list2 = new ArrayList<>();
        while (matcher2.find()) {
            list2.add(matcher2.group());
            Log.e("babanana", matcher2.group());
        }

    }

    //$1,$2,$3 子表达式
    private String initList() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Log.e("babanana", list.toString());
        String intStr = "12345";
        String s = Pattern.compile("\\b([\\w\\W])(\\d)\\b").matcher(list.toString()).replaceAll("'$'");
        Log.e("babanana", s);
        return s;
    }

    private void strToLong() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(calendar.getTime());
        long l = Long.parseLong(time);//只可以转化只含数字的字符串（long型字符串）
        Log.e("babanana", String.valueOf(l));
    }

    private void setTimeFormat() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = format.format(calendar.getTime());
        Log.e("babanana", time);
        Log.e("babanana", time.split(" ")[0]);
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(time);
            date.getTime();
            String time2 = format2.format(date);
            Log.e("babanana", time2);
            Date date2 = format2.parse(time);
            String time3 = format2.format(date2);
            Log.e("babanana", time3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String FindSubSortCodeByName(String subSortName) {
        String strRet = "";
        if (subSortName == null)
            return strRet;
        subSortName = subSortName.trim();
        String pattern = "{" + subSortName + ",(?<key>[^}]*)}";
        Pattern pattern2 = Pattern.compile(pattern);
        Matcher match = pattern2.matcher(AllSubSort);
        if (match != null)
            strRet = match.group();
        return strRet;
    }

    /// <summary>
    /// 通过分类代码找到分类名称
    /// </summary>
    /// <param name="subSortCode">分类代码</param>
    /// <returns>分类名称</returns>

    public static String FindSubSortNameByCode(String subSortCode) {
        String strRet = "";
        if (subSortCode == null)
            return strRet;
        subSortCode = subSortCode.trim();
        String pattern = "{(?<key>[^{]*)," + subSortCode + "}";
        Pattern pattern2 = Pattern.compile(pattern);
        Matcher match = pattern2.matcher(AllSubSort);
        if (match != null)
            strRet = match.group();
        return strRet;
    }


}
