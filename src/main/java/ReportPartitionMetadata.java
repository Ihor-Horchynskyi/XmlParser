import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ReportPartitionMetadata {
    public String label;

    @JsonProperty("values")
    public List<FieldValuePair> values;

    @JsonProperty("protected")
    String protectedField;

    public ReportPartitionMetadata(String label, List<FieldValuePair> values, String protectedField) {
        this.label = label;
        this.values = values;
        this.protectedField = protectedField;
    }

    @ToString.Exclude
    @JsonIgnore
    private Map<String, String> mtdMap;

    public Map<String, String> getMtdMap() {
        if (mtdMap == null) {
            System.out.println("This is " + this);
            mtdMap = new HashMap<>();
            for (FieldValuePair pair : values) {
                mtdMap.put(pair.field, pair.value);
            }
        }
        return mtdMap;
    }


}
