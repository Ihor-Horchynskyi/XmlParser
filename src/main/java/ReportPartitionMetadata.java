import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.SQLOutput;
import java.util.*;

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
            mtdMap = new HashMap<>();

            List<String> fieldNameList = new ArrayList<>();
            List<String> fieldValueList = new ArrayList<>();
            for (FieldValuePair pair : values) {
                if(pair.field.equals("ColumnNames__c")) {
                    fieldNameList = Arrays.asList(pair.value.split(","));
                }
                if(pair.field.equals("ReportFields__c")) {
                    fieldValueList = Arrays.asList(pair.value.split(","));
                }
            }

            for(int i = 0; i<fieldNameList.size(); i++){
                mtdMap.put(fieldNameList.get(i), fieldValueList.get(i));
                //System.out.println(fieldNameList.get(i) + " = " + fieldValueList.get(i));
            }
            System.out.println("Map size = " + mtdMap.size());
        }
        return mtdMap;
    }


}
