# Apache POI 快速入门

## 1. 什么是 Apache POI?

Apache POI 是一个非常流行的 Java 库，它的使命是让你能够用 Java 代码轻松地创建、修改和读取 Microsoft Office 格式的文件。最常见的用途就是操作 Excel 文件（`.xls` 和 `.xlsx`）。

简单来说：**Java + POI = 操作 Excel 文件**。

## 2. 什么时候会用到它？

- **数据导出**：将系统中的数据（如用户列表、订单报表）导出为 Excel 文件，方便运营人员分析。
- **数据导入**：允许用户通过上传 Excel 文件，批量将数据导入到系统中（批量导入商品、批量添加员工）。
- **生成报表**：根据业务数据，动态生成复杂的、格式化的 Excel 报表。

## 3. 快速安装

在你的 Maven 项目中，只需要在 `pom.xml` 文件里添加以下依赖即可：

```xml
<!-- Excel文件处理 - Apache POI -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.2.4</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.4</version>
</dependency>
```
## 4. 核心概念

操作 Excel 就像搭积木，POI 为我们提供了四种最基本的“积木”：

1.  **`Workbook` (工作簿)**: 代表整个 Excel 文件本身。
2.  **`Sheet` (工作表)**: 代表文件底部的一个个标签页 (Sheet1, Sheet2...)。
3.  **`Row` (行)**: 代表工作表中的一行。
4.  **`Cell` (单元格)**: 代表一行中的一个格子，这是我们最终写入和读取数据的地方。

**关系**: `Workbook` 包含 `Sheet`，`Sheet` 包含 `Row`，`Row` 包含 `Cell`。

**HSSFWorkbook**

- **文件格式**：用于操作 Excel 97-2003 格式的文件，也就是 `.xls` 文件。这种格式是基于 BIFF（Binary Interchange File Format）二进制格式，文件结构相对复杂，且存在一些限制，比如一个工作表最多只能有 65536 行数据。
- **内存使用**：将整个 Excel 文件加载到内存中进行处理，在创建、读取和修改文件时，所有数据都会在内存中保存。这就意味着，如果要处理的数据量较大，会占用较多的内存，甚至可能导致内存溢出。
- **适用场景**：适合处理数据量较小的 Excel 文件，或者对兼容性有较高要求，需要生成或读取 `.xls` 格式文件的场景。

**XSSFWorkbook**

- **文件格式**：用于操作 Excel 2007 及以上版本的文件，即 `.xlsx` 文件。该格式基于 Office Open XML 标准，本质上是一个压缩包，里面包含多个 XML 文件，分别描述工作表数据、样式、图表等信息。一个工作表理论上最多可以有 1048576 行数据。
- **内存使用**：同样会将整个 Excel 文件加载到内存中进行操作。虽然相比 `.xls` 文件，`.xlsx` 文件在数据存储上更加灵活和高效，但在处理超大量数据时，内存占用问题依然突出。
- **适用场景**：适用于数据量不是特别巨大，且需要生成或读取 `.xlsx` 格式文件的场景，它能提供丰富的功能，比如设置各种复杂的样式、图表等。

**SXSSFWorkbook**

- **文件格式**：也是用于生成 Excel 2007 及以上版本的 `.xlsx` 文件。

- **内存使用**：这是一个基于流式写入的类，它通过限制内存中缓存的行数来控制内存的使用。当达到指定的行缓存数量后，旧的数据行会被写入磁盘临时文件中，从而大大减少内存占用，适合处理超大规模数据。不过，由于涉及磁盘写入，在一定程度上会影响写入速度。

- **适用场景**：`SXSSFWorkbook`的核心功能就是**将程序中的数据写出到外部的 Excel 文件（.xlsx 格式）**，特别适合处理大规模数据的导出场景。

  但它**完全不支持读取功能**，也就是不能打开一个已存在的 Excel 文件，也不能将外部 Excel 文件中的数据读取到程序中进行处理。

## 5. 实战1：创建一个简单的 Excel 文件

让我们来创建一个名为 `my_first_excel.xlsx` 的文件，并在里面写入 "Hello, World!"。

```java
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelExample {

    public static void main(String[] args) {
        // 1. 创建一个新的工作簿 (Workbook)
        // 使用 try-with-resources 确保资源自动关闭
        try (Workbook workbook = new XSSFWorkbook()) { 
            
            // 2. 在工作簿中创建一个新的工作表 (Sheet)
            Sheet sheet = workbook.createSheet("第一个Sheet");

            // 3. 创建一行 (Row), 行号从 0 开始
            Row headerRow = sheet.createRow(0);

            // 4. 在行中创建一个单元格 (Cell), 单元格号从 0 开始
            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue("姓名");

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("年龄");

            // 创建数据行
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue("张三");
            dataRow.createCell(1).setCellValue(25);


            // 5. 将工作簿内容写入到一个文件中
            try (FileOutputStream fileOut = new FileOutputStream("my_first_excel.xlsx")) {
                workbook.write(fileOut);
            }

            System.out.println("Excel 文件创建成功！");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
**代码讲解**:
- `new XSSFWorkbook()`: 创建一个用于 `.xlsx` 格式的空工作簿。
- `workbook.createSheet("...")`: 创建一个名为 "第一个Sheet" 的工作表。
- `sheet.createRow(0)`: 在工作表中创建第一行（索引为0）。
- `row.createCell(0)`: 在该行中创建第一个单元格（索引为0）。
- `cell.setCellValue(...)`: 设置单元格的值。POI 会自动识别数据类型（字符串、数字等）。
- `new FileOutputStream(...)`: 创建一个文件输出流，指定要写入的文件名。
- `workbook.write(fileOut)`: 这是最关键的一步，将内存中的工作簿数据真正写入到文件中。
- `try-with-resources`: 自动为我们关闭 `workbook` 和 `fileOut`，非常方便和安全。

## 6. 实战2：读取刚才创建的 Excel 文件

现在，我们来读取 `my_first_excel.xlsx` 的内容并打印到控制台。

```java
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelExample {

    public static void main(String[] args) {
        try (FileInputStream fileIn = new FileInputStream("my_first_excel.xlsx");
             Workbook workbook = new XSSFWorkbook(fileIn)) {

            // 1. 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 2. 遍历工作表的每一行
            for (Row row : sheet) {
                // 3. 遍历该行的每一个单元格
                for (Cell cell : row) {
                    // 4. 判断单元格类型并读取数据
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue());
                            break;
                        default:
                            System.out.print("(未知类型)");
                    }
                    System.out.print("\t\t"); // 使用制表符分隔
                }
                System.out.println(); // 换行
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
**代码讲解**:

- `new FileInputStream(...)`: 创建一个文件输入流来读取文件。
- `new XSSFWorkbook(fileIn)`: 从输入流中加载现有的Excel文件并创建工作簿对象。
- `workbook.getSheetAt(0)`: 获取第一个工作表（索引为0）。
- `for (Row row : sheet)`: POI 提供了方便的 for-each 循环来遍历行和单元格。
- `cell.getCellType()`: **读取时最重要的一步**。因为单元格可以存储不同类型的数据（文本、数字、日期、布尔值等），我们必须先判断它的类型，再调用对应的方法（如 `getStringCellValue()` 或 `getNumericCellValue()`）来读取，否则会抛出异常。

## 7. 总结

恭喜你！你已经掌握了使用 Apache POI 进行最核心的读写操作。

- **写操作(导出)**: `Workbook` -> `Sheet` -> `Row` -> `Cell` -> `setCellValue` -> `FileOutputStream`
- **读操作（导入）**: `FileInputStream` -> `Workbook` -> `Sheet` -> 遍历 `Row` -> 遍历 `Cell` -> 判断 `getCellType` -> `get...CellValue`
