package ingress.flightms.mapper;

import ingress.flightms.model.dto.response.PlaneResponseDTO;
import ingress.flightms.model.entity.Plane;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlaneMapper {

    PlaneResponseDTO planeToPlaneResponseDTO(Plane plane);
}

