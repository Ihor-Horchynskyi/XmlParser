import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> firstMtdList = Arrays.asList(
                "C:\\Projects\\XmlParser\\xml\\ReportPartition.TransactionsFees_Part1.md-meta.xml",
                "C:\\Projects\\XmlParser\\xml\\ReportPartition.TransactionsFees_Part2.md-meta.xml",
                "C:\\Projects\\XmlParser\\xml\\ReportPartition.TransactionsFees_Part3.md-meta.xml"
        );

        List<ReportPartitionMetadata> mtdList = new ArrayList<>();
        for (String filePath : firstMtdList) {
            ReportPartitionMetadata report = XmlParser.deserialize(filePath);
            mtdList.add(report);
        }

        for (int i = 1; i < mtdList.size(); i++) {
            for (String fieldName : mtdList.get(0).getMtdMap().keySet()) {
                mtdList.get(i).getMtdMap().remove(fieldName);
            }
        }

        List<String> exportMtdList = Arrays.asList(
                "C:\\Projects\\XmlParser\\xml\\ReportPartitio1.md-meta.xml",
                "C:\\Projects\\XmlParser\\xml\\ReportPartition2.md-meta.xml",
                "C:\\Projects\\XmlParser\\xml\\ReportPartition3.md-meta.xml"
        );

        for (int i = 0; i < mtdList.size(); i++) {
            ReportPartitionMetadata report = mtdList.get(i);
            List<FieldValuePair> list = new ArrayList<>();
            for (String valMapField : report.getMtdMap().keySet()) {
                
                list.add(new FieldValuePair().setValue(report.getMtdMap().get(valMapField)).setField(valMapField));
            }
            report.setValues(list);

            XmlParser.serialize(mtdList.get(i), exportMtdList.get(i));
        }


        /*
        XmlParser.serialize(
                new ReportPartitionMetadata("Label",
                        Arrays.asList(
                                new FieldValuePair("A", "B"), (new FieldValuePair("A", "B"))
                        ),
                        "Protected"),
                "C:\\Projects\\XmlParser\\xml\\ReportPartition.TransactionsFees_Part3.md-meta.xml");
    */

    }
}
