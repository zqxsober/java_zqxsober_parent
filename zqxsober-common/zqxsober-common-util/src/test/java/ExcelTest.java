import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExcelTest {

    public static void main(String[] args) {
        // 创建导出数据（示例数据）
        List<List<Object>> data = generateSampleData();

        // 动态生成表头（一年内的月份和日期）
        List<String> dynamicHeader = generateMonthAndDateHeader();

        // 指定输出文件路径
        String outputFile = "dynamic_month_and_date_excel_export.xlsx";

        // 使用 EasyExcel 导出 Excel
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(outputFile);
        ExcelWriter excelWriter = excelWriterBuilder.build();
        WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();

        // 写入表头和数据
        excelWriter.write(dynamicHeader, writeSheet);
        excelWriter.write(data, writeSheet);

        // 关闭 ExcelWriter
        excelWriter.finish();

        System.out.println("Excel 导出完成");
    }

    // 生成示例数据
    private static List<List<Object>> generateSampleData() {
        List<List<Object>> data = new ArrayList<>();
        List<Object> rowData1 = new ArrayList<>();
        rowData1.add("张三");
        rowData1.add(25);
        rowData1.add(30);
        rowData1.add(40);
        data.add(rowData1);

        List<Object> rowData2 = new ArrayList<>();
        rowData2.add("李四");
        rowData2.add(30);
        rowData2.add(35);
        rowData2.add(45);
        data.add(rowData2);

        return data;
    }

    // 生成一年内的月份和日期作为表头
    private static List<String> generateMonthAndDateHeader() {
        List<String> header = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        for (int i = 0; i < 12; i++) {
            String formattedMonth = String.format("%tB", calendar.getTime()); // 获取月份的全名
            header.add(formattedMonth);

            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int day = 1; day <= daysInMonth; day++) {
                header.add(String.valueOf(day));
            }

            calendar.add(Calendar.MONTH, 1);
        }

        return header;
    }
}

