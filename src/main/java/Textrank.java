import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.summary.TextRankKeyword;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

public class Textrank {
    public static void main(String[] args) {
        /**
         * 读取文件列表
         */
        System.out.println("hello");
        String filepath="news";
        ArrayList<String> files = new ArrayList<String>();
        getAllFileName(filepath,files);

        System.out.println("\n读取完成:"+files.toString()+"\n");

        /**
         * 存储文件内容到内存
         */
        List<String> data=new ArrayList<String>();//用于存储txt文件的内容
        for (int i=0;i< files.size();i++){
        String TxtPath=filepath+"/"+files.get(i);
        System.out.println("文件路径获取："+TxtPath);
        data.add(readFileByChars(TxtPath));
        }
        /**
         * 找到了一个轮子，啊，不，是卡车，算法直接结束了
         */
        for(int i=0;i< data.size();i++)
        {
            List<String> sentenceList = HanLP.extractSummary(data.get(i), 3);
            System.out.println(sentenceList);
        };
/****************************以下纯属乱写pass就好*********************************************/
//        String content = "我发誓不会再让喜欢我的小男孩受伤，可是好像也没人喜欢我，好像受伤的总是我";
//        List<String> keywordList = HanLP.extractKeyword(content, 3);
//        System.out.println(keywordList);


    }




    /**
     * 获取文件夹下的文件并存到list中
     * @param path
     * @param fileNameList
     */
    public static void getAllFileName(String path, ArrayList<String> fileNameList) {
        //ArrayList<String> files = new ArrayList<String>();
        boolean flag = false;
        File file = new File(path);
        File[] tempList = file.listFiles();
        System.out.println("开始读取：\n");
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
            System.out.println("文     件：" + tempList[i]);
                fileNameList.add(tempList[i].getName());
            }
            if (tempList[i].isDirectory()) {
             System.out.println("文件夹：" + tempList[i]);
                getAllFileName(tempList[i].getAbsolutePath(),fileNameList);
            }
        }
        return;
    }


    /**
     * 读取txt文件内容输出String
     * @param fileName
     */
    public static String readFileByChars(String fileName) {
        String outs="";
        File file = new File(fileName);
        Reader reader = null;
        try {
//            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    outs+=((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return outs;
    }


}

