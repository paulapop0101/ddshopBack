package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class ColorsDTO {
    private int id;
    private String value;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ColorsDTO colorsDTO = (ColorsDTO) o;
        return value.equals(colorsDTO.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
