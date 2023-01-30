package guru.qa.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import guru.qa.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesParsingTests {

    ClassLoader cl = FilesParsingTests.class.getClassLoader();

    @Test
    @DisplayName("Read information from Zip, which contains pdf, xlsx, csv files")
    void zipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("Files.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Pdf-sample.pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Adobe Acrobat PDF Files");
                } else if (entry.getName().equals("Template.xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(4).getCell(2)
                            .getStringCellValue()).contains("Get Groceries");
                } else if (entry.getName().equals("Sales.csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(1)[0]).contains("Ivan Petrov");
                }
            }
        }
    }

    @Test
    @DisplayName("Read information from json file")
    void jsonParseTest() throws IOException {
        try (
                InputStream jsonData = cl.getResourceAsStream("Employee.json");
        ) {
            ObjectMapper objectMapper = new ObjectMapper();
            Employee emp = objectMapper.readValue(jsonData, Employee.class);
            assertThat(emp.getId()).isEqualTo(43);
            assertThat(emp.getName()).isEqualTo("David");
        }
    }
}