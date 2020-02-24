import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class XmlParser {

    public static ReportPartitionMetadata deserialize(String filePath) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        String xml = inputStreamToString(new FileInputStream(new File(filePath)));
        ReportPartitionMetadata value
                = xmlMapper.readValue(xml, ReportPartitionMetadata.class);
        return value;
    }

    public static void serialize(ReportPartitionMetadata mtd, String fileName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(fileName), mtd);
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
