import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FieldValuePair{
    public String field;
    public String value;


}