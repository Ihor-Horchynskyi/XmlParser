import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializeTest {
    private static final String XML = "<ReportPartitionMetadata>...</ReportPartitionMetadata>";

    @Test
    public void whenJavaSerializedToXmlFile_thenSuccess() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        //ReportPartitionMetadata person = testPerson(); // test data
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //xmlMapper.writeValue(byteArrayOutputStream, person);
        assertEquals(XML, byteArrayOutputStream.toString());
    }
}
