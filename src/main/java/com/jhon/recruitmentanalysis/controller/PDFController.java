package com.jhon.recruitmentanalysis.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jhon.recruitmentanalysis.pojo.Position;
import com.jhon.recruitmentanalysis.service.PositionService;
import com.jhon.recruitmentanalysis.utils.PageUtil;
import com.jhon.recruitmentanalysis.utils.PdfFUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class PDFController {

    @Resource
    PositionService positionService;

    @GetMapping("/pdf")
    public void getPosition(@RequestParam(value = "date", required = false) String date,
                            @RequestParam(value = "city", required = false) String city,
                            @RequestParam(value = "keyword", required = false) ArrayList<String> keyword,
                            @RequestParam(value = "salary", required = false) ArrayList<BigDecimal> salary,
                              HttpServletResponse response) throws IOException {

        String year;
        String month;
        String day;
        if (date == null || date == "") {
            year = null;
            month = null;
            day = null;
        } else {
            year = date.split("-")[0];
            month = date.split("-")[1];
            day = date.split("-")[2];
        }
        BigDecimal salaryMin;
        BigDecimal salaryMax;
        if (salary == null || salary.size() == 0) {
            salaryMin = null;
            salaryMax = null;
        } else {
            salaryMin = salary.get(0);
            salaryMax = salary.get(1);
        }

        List<Position> positionList;

        if (keyword == null || keyword.size()==0){
            positionList = positionService.getAll(year,month,day,city,salaryMin,salaryMax);
        } else {
            positionList = positionService.getAll(year,month,day,city,salaryMin,salaryMax,keyword);
        }

        int total = positionList.size();
        try {
            //TODO 1.新建document对象
            Document document = new Document(PageSize.A4.rotate());// 建立一个Document对象
            //TODO 2.建立一个书写器(Writer)与document对象关联
            File file = new File("D:\\pdfExport.pdf"); //修改你要生成PDF的位置路径
            file.createNewFile();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            //TODO 3.打开文档
            document.open();

            //todo 标题
            Paragraph paragraph = new Paragraph("岗位信息表", titlefont_16);
            paragraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
            paragraph.setIndentationLeft(12); //设置左缩进
            paragraph.setIndentationRight(12); //设置右缩进
            paragraph.setFirstLineIndent(24); //设置首行缩进
            paragraph.setLeading(20f); //行间距
            paragraph.setSpacingBefore(5f); //设置段落上空白
            paragraph.setSpacingAfter(10f); //设置段落下空白

            PdfPTable table_item = tbPublic();
            document.add(paragraph); // 标题

            int pn=1;
            int ps=34;
            for(int j=0;j<(total/ps)+1;j++) {
//                PageUtil pageUtil = new PageUtil();
                List<Position> listPage = PageUtil.getPageData(positionList, pn, ps);
                // 表格
                PdfPTable table = PdfFUtil.createTable(new float[]{80, 80, 80, 80, 80});
                for (int i = 0; i < listPage.size(); i++) {
                    table.addCell(PdfFUtil.createCell(listPage.get(i).getPosition(), textfont_10));
                    table.addCell(PdfFUtil.createCell(listPage.get(i).getYear()+"-"+listPage.get(i).getMonth()+"-"+listPage.get(i).getDay(), textfont_10));
                    table.addCell(PdfFUtil.createCell(listPage.get(i).getCity(), textfont_10));
                    if (listPage.get(i).getKeywords() == null || listPage.get(i).getKeywords() == ""){
                        table.addCell(PdfFUtil.createCell("无关键字", textfont_10));
                    }else {
                        table.addCell(PdfFUtil.createCell(listPage.get(i).getKeywords(), textfont_10));
                    }
                    table.addCell(PdfFUtil.createCell(listPage.get(i).getSalaryMin().intValue()/1000+"k-"+listPage.get(i).getSalaryMax().intValue()/1000+"k", textfont_10));
                }
                document.add(table_item);
                document.add(table); //表格数据
                PdfFUtil.onEndPage(writer,document);
                pn++;
                ps = 36;
            }


            //TODO 5.关闭文档
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        // path是指想要下载的文件的路径
        File file = new File("D:\\pdfExport.pdf");

        String filename = file.getName();

        // 将文件写入输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();

        // 清空response
        response.reset();
        // 设置response的Header
        response.setCharacterEncoding("UTF-8");
        //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
        //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
        // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        // 告知浏览器文件的大小
        response.addHeader("Content-Length", "" + file.length());
        response.addHeader("Access-Control-Allow-Origin", "*");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        outputStream.write(buffer);
        outputStream.flush();

        file.delete();

    }

    /* 表格头部公共的一行 */
    public PdfPTable tbPublic(){
        PdfPTable table = PdfFUtil.createTable(new float[] { 80, 80, 80, 80, 80});
        table.addCell(PdfFUtil.createCell("岗位名称", textfont_10));
        table.addCell(PdfFUtil.createCell("日期", textfont_10));
        table.addCell(PdfFUtil.createCell("所在城市", textfont_10));
        table.addCell(PdfFUtil.createCell("主要技术", textfont_10));
        table.addCell(PdfFUtil.createCell("岗位薪资", textfont_10));
        return table;
    }
    /**
     * 全局变量
     */

    // 定义全局的字体静态变量
    private static Font titlefont_16;
    private static Font titlefontnormal_16;
    private static Font headfont_14;
    private static Font headfontnormal_14;
    private static Font headfont_12;
    private static Font headfontnormal_12;
    private static Font keyfont_10;
    private static Font textfont_10;
    private static Font underlinefont_10;

    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            titlefont_16 = new Font(bfChinese, 16, Font.BOLD);
            headfont_14 = new Font(bfChinese, 14, Font.BOLD);
            headfont_12 = new Font(bfChinese, 12, Font.BOLD);
            keyfont_10 = new Font(bfChinese, 10, Font.BOLD);

            titlefontnormal_16 = new Font(bfChinese, 16, Font.NORMAL);
            headfontnormal_14 = new Font(bfChinese, 14, Font.NORMAL);
            headfontnormal_12 = new Font(bfChinese, 12, Font.NORMAL);
            textfont_10 = new Font(bfChinese, 10, Font.NORMAL);
            underlinefont_10 = new Font(bfChinese, 10, Font.UNDERLINE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
