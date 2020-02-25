import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class XmlParser {

    private static ReportPartitionMetadata deserialize(String filePath) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        /*xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        xmlMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        xmlMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        xmlMapper.enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        xmlMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.configure(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        // Skip the Null Values
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.disableDefaultTyping();      */
        String xml = inputStreamToString(new FileInputStream(new File(filePath)));
        ReportPartitionMetadata value
                = xmlMapper.readValue(xml, ReportPartitionMetadata.class);
        return value;
    }

    private static void serialize(ReportPartitionMetadata mtd, String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(fileName), mtd);
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static void fixMetadata(List<String> importMtdList, List<String> exportToFilesList) throws IOException {
        List<ReportPartitionMetadata> metadataObjectsList = new ArrayList<>();
        for (String filePath : importMtdList) {
            ReportPartitionMetadata report = deserialize(filePath);
            metadataObjectsList.add(report);
        }
        for (int i = 1; i < metadataObjectsList.size(); i++) {
            for (String fieldName : metadataObjectsList.get(0).getMtdMap().keySet()) {
                System.out.print("Report " + metadataObjectsList.get(i).getLabel() + ": " + "Field \"" + fieldName + "\"");
                if( metadataObjectsList.get(i).getMtdMap().containsKey(fieldName)) {
                    System.out.print(" removed!");
                    metadataObjectsList.get(i).getMtdMap().remove(fieldName);
                }
                System.out.println(" ");
            }
        }

        for (int i = 0; i < metadataObjectsList.size(); i++) {
            ReportPartitionMetadata report = metadataObjectsList.get(i);
            for (FieldValuePair pair : report.getValues()) {
                if(pair.field.equals("ColumnNames__c")){
                    List<String> uniqueNames = new ArrayList<>();
                    for (String name : report.getMtdMap().keySet()){
                        if( report.getMtdMap().get(name) != null) uniqueNames.add(name);
                    }
                    System.out.println("uniqueNames " + uniqueNames.size());
                    pair.value = String.join(",", uniqueNames);
                }

                if(pair.field.equals("ReportFields__c")) pair.value = String.join(",", report.getMtdMap().values());
            }

            XmlParser.serialize(metadataObjectsList.get(i), exportToFilesList.get(i));
        }
    }
}
