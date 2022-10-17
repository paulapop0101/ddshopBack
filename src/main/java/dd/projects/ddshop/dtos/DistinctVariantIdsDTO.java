package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class DistinctVariantIdsDTO {
    private int id;
    private int productId;
    private String value;


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DistinctVariantIdsDTO that = (DistinctVariantIdsDTO) o;
        return productId == that.productId && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, value);
    }
}
