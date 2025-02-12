package ingress.flightms.service;





import ingress.flightms.model.dto.request.PlanePlaceRequest;
import ingress.flightms.model.dto.response.PlanePlaceResponse;

import java.util.Set;

public interface PlanePlaceService {
    PlanePlaceResponse createPlanePlace(PlanePlaceRequest planePlaceRequest);

    PlanePlaceResponse getPlanePlaceById(Long id);

    Set<PlanePlaceResponse> getAllPlanePlaces();

    PlanePlaceResponse updatePlanePlace(Long id,PlanePlaceRequest planePlaceRequest);

    void deletePlanePlace(Long id);

    PlanePlaceResponse getPlanePlacesByType(String placeType);

    PlanePlaceResponse getPlanePlacesByRow(Integer row);

}
