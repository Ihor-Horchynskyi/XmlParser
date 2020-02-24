import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class XmlParser {

    public static ReportPartitionMetadata deserialize(String filePath) throws IOException {
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
