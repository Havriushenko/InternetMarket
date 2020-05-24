package pro_area.test_task.havriushenko.internet_market.util;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import javafx.scene.text.TextAlignment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import pro_area.test_task.havriushenko.internet_market.dto.OrderDto;
import pro_area.test_task.havriushenko.internet_market.dto.ProductDto;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.lowagie.text.Font.BOLD;
import static pro_area.test_task.havriushenko.internet_market.util.Constans.*;

@Component(GENERATE_RECEIPT_ODF_BEAN_NAME)
public class GenerateReceiptPdf extends AbstractPdfView {

    private static final String OOO_NAME_SHOP = "OOO \"Internet Market\"";

    private static int FONT_HEADER_SIZE_SMALL = 10;
    private static int FONT_REGULAR_SIZE = 14;
    private static int FONT_SIZE_BIG = 20;
    private static int TEXT_STYLE_TIME_ROMAN = 2;

    private static Font headerFont = new Font(TEXT_STYLE_TIME_ROMAN, FONT_HEADER_SIZE_SMALL);
    private static Font boldBigSizeFont = new Font(TEXT_STYLE_TIME_ROMAN, FONT_SIZE_BIG, BOLD);
    private static Font boldRegularFont = new Font(TEXT_STYLE_TIME_ROMAN, FONT_REGULAR_SIZE, BOLD);
    private static Font regularFont = new Font(TEXT_STYLE_TIME_ROMAN, FONT_REGULAR_SIZE);

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        OrderDto order = (OrderDto) model.get(KEY_ORDER_FOR_MAP_PDF);
        document.add(addHeader(order.getId()));
        document.add(addTitle());
        document.add(addCustomerReference(order.getUser()));
        document.add(addTableProducts(order.getProducts()));
        document.add(addFooter());
    }

    private Paragraph addHeader(int orderId) {
        Paragraph header = new Paragraph();
        header.setAlignment(String.valueOf(TextAlignment.RIGHT));
        header.setMultipliedLeading(1);
        header.add(new Chunk(
                String.format("%s: %s\n %s", "Order Id",
                        orderId,
                        DateTimeFormatter.ofPattern(PATTERN_FOR_DATE).format(LocalDateTime.now())),
                headerFont));
        return header;
    }

    private Paragraph addTitle() {
        Paragraph title = new Paragraph();
        title.setAlignment(String.valueOf(TextAlignment.CENTER));
        title.add(new Chunk(String.format("\n \n %s", OOO_NAME_SHOP), boldBigSizeFont));
        return title;
    }

    private Paragraph addCustomerReference(UserDto user) {
        Paragraph customerReference = new Paragraph();
        customerReference.setAlignment(String.valueOf(TextAlignment.LEFT));
        customerReference.setMultipliedLeading(1);
        customerReference.add(new Chunk(String.format("\n%s: %s %s \n", "Name", user.getName(), user.getSurname()), regularFont));
        customerReference.add(new Chunk(String.format("%s: %s \n", "Address", "Custom Address"), regularFont));
        customerReference.add(new Chunk(String.format("%s: %s \n", "Phone", "Custom Phone"), regularFont));
        return customerReference;
    }

    private Table addTableProducts(Map<ProductDto, Integer> products) throws BadElementException {
        Table table = new Table(3);
        int total = 0;

        Cell cell1 = new Cell(new Paragraph(new Chunk("Product", boldRegularFont)));
        cell1.setBorderColor(Color.WHITE);
        Cell cell2 = new Cell(new Paragraph(new Chunk("Quantity", boldRegularFont)));
        cell1.setBorderColor(Color.WHITE);
        Cell cell3 = new Cell(new Paragraph(new Chunk("Price", boldRegularFont)));
        cell1.setBorderColor(Color.WHITE);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.setAlignment(String.valueOf(TextAlignment.LEFT));

        for (Map.Entry<ProductDto, Integer> product : products.entrySet()) {
            table.addCell(new Paragraph(new Chunk(product.getKey().getName(), regularFont)));
            table.addCell(new Paragraph(new Chunk(String.valueOf(product.getValue()), regularFont)));
            table.addCell(new Paragraph(new Chunk(String.valueOf(product.getKey().getPrice()), regularFont)));
            total += product.getKey().getPrice() * product.getValue();
        }

        table.addCell(new Paragraph(new Chunk("Total: ", boldBigSizeFont)));
        table.addCell(new Paragraph(new Chunk("")));
        table.addCell(new Paragraph(new Chunk(String.valueOf(total), boldBigSizeFont)));

        return table;
    }

    private Paragraph addFooter() {
        Paragraph footer = new Paragraph();
        footer.setAlignment(String.valueOf(TextAlignment.CENTER));
        footer.add(Chunk.NEWLINE);
        footer.add(Chunk.NEWLINE);
        footer.add(new Chunk("Thanks for your Order!", boldBigSizeFont));
        return footer;
    }
}
