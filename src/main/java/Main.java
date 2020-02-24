import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> firstMtdList = Arrays.asList(
                "xml/ReportPartition.TransactionsFees_Part1.md-meta.xml",
                "xml/ReportPartition.TransactionsFees_Part2.md-meta.xml",
                "xml/ReportPartition.TransactionsFees_Part3.md-meta.xml"
        );

        List<ReportPartitionMetadata> metadataObjectsList = new ArrayList<>();
        for (String filePath : firstMtdList) {
            ReportPartitionMetadata report = XmlParser.deserialize(filePath);
            metadataObjectsList.add(report);
        }

        for (int i = 1; i < metadataObjectsList.size(); i++) {
            for (String fieldName : metadataObjectsList.get(0).getMtdMap().keySet()) {
                metadataObjectsList.get(i).getMtdMap().remove(fieldName);
            }
        }

        List<String> exportMtdList = Arrays.asList(
                "xml/ReportPartitio1.md-meta.xml",
                "xml/ReportPartition2.md-meta.xml",
                "xml/ReportPartition3.md-meta.xml"
        );

        for (int i = 0; i < metadataObjectsList.size(); i++) {
            ReportPartitionMetadata report = metadataObjectsList.get(i);
            for (FieldValuePair pair : report.getValues()) {
                if(pair.field == "ColumnNames__c") pair.value = String.join(",", report.getMtdMap().keySet());
                if(pair.field == "ReportFields__c") pair.value = String.join(",", report.getMtdMap().values());
            }

            XmlParser.serialize(metadataObjectsList.get(i), exportMtdList.get(i));
        }

    }
}
