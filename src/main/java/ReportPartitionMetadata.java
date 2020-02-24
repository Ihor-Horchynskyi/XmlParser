import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)

public class ReportPartitionMetadata {
    public String label;
    public FieldValuePair[] values;

    @JsonProperty("protected")
    String protectedField;

    @ToString.Exclude
    @JsonIgnore
    private Map<String, String> mtdMap;

    public Map<String, String> getMtdMap(){
        System.out.println("This is " + this);
        if(mtdMap == null) {
            mtdMap = new HashMap<>();
            for(FieldValuePair pair : values){
                mtdMap.put(pair.field, pair.value);
            }
        }
        return mtdMap;
    }


}
