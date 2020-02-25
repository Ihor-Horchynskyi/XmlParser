import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("INIT");
        List<String> firstImportMtdList = Arrays.asList(
                "xml/Input/ReportPartition.TransactionsFees_Part1.md-meta.xml",
                "xml/Input/ReportPartition.TransactionsFees_Part2.md-meta.xml",
                "xml/Input/ReportPartition.TransactionsFees_Part3.md-meta.xml"
        );

        List<String> firstExportMtdList = Arrays.asList(
                "xml/Output/ReportPartition.TransactionsFees_Part1.md-meta.xml",
                "xml/Output/ReportPartition.TransactionsFees_Part2.md-meta.xml",
                "xml/Output/ReportPartition.TransactionsFees_Part3.md-meta.xml"
        );

        List<String> secondImportMtdList = Arrays.asList(
                "xml/Input/ReportPartition.DealerSiteInvoicesAndPayments_Part1.md-meta.xml",
                "xml/Input/ReportPartition.DealerSiteInvoicesAndPayments_Part2.md-meta.xml",
                "xml/Input/ReportPartition.DealerSiteInvoicesAndPayments_Part3.md-meta.xml",
                "xml/Input/ReportPartition.DealerSiteInvoicesAndPayments_Part4.md-meta.xml",
                "xml/Input/ReportPartition.DealerSiteInvoicesAndPayments_Part5.md-meta.xml",
                "xml/Input/ReportPartition.DealerSiteInvoicesAndPayments_Part6.md-meta.xml",
                "xml/Input/ReportPartition.DealerSiteInvoicesAndPayments_Part7.md-meta.xml"
        );

        List<String> secondExportMtdList = Arrays.asList(
                "xml/Output/ReportPartition.DealerSiteInvoicesAndPayments_Part1.md-meta.xml",
                "xml/Output/ReportPartition.DealerSiteInvoicesAndPayments_Part2.md-meta.xml",
                "xml/Output/ReportPartition.DealerSiteInvoicesAndPayments_Part3.md-meta.xml",
                "xml/Output/ReportPartition.DealerSiteInvoicesAndPayments_Part4.md-meta.xml",
                "xml/Output/ReportPartition.DealerSiteInvoicesAndPayments_Part5.md-meta.xml",
                "xml/Output/ReportPartition.DealerSiteInvoicesAndPayments_Part6.md-meta.xml",
                "xml/Output/ReportPartition.DealerSiteInvoicesAndPayments_Part7.md-meta.xml"
        );

        XmlParser.fixMetadata(secondImportMtdList, secondExportMtdList);

    }
}
