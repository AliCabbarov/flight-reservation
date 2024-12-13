package ingress.flightms.mapper;

import ingress.flightms.model.dto.request.PlanePlaceRequest;
import ingress.flightms.model.dto.response.PlanePlaceResponse;
import ingress.flightms.model.entity.PlanePlace;
import org.springframework.stereotype.Component;

@Component
public class PlanePlaceMapper {

    public PlanePlace mapToPlanePlace(PlanePlaceRequest request) {
        PlanePlace planePlace = new PlanePlace();
        planePlace.setPlace(request.getPlace());
        planePlace.setPlaceNumber(request.getPlaceNumber());
        planePlace.setPlaceType(request.getPlaceType());
        planePlace.setRow(request.getRow());
        return planePlace;

    }


   public PlanePlaceResponse mapToPlanePlaceToResponse(PlanePlace planePlace) {
        PlanePlaceResponse planePlaceResponse = new PlanePlaceResponse();
        planePlaceResponse.setPlace(planePlace.getPlace());
        planePlaceResponse.setPlaceNumber(planePlace.getPlaceNumber());
        planePlaceResponse.setPlaceType(planePlace.getPlaceType());
        planePlaceResponse.setRow(planePlace.getRow());
        planePlaceResponse.setId(planePlace.getId());
        return planePlaceResponse;

    }

    public PlanePlace mapToPlanePlace(PlanePlaceResponse planePlaceResponse) {
        PlanePlace planePlace = new PlanePlace();
        planePlace.setPlace(planePlaceResponse.getPlace());
        planePlace.setPlaceNumber(planePlaceResponse.getPlaceNumber());
        planePlace.setPlaceType(planePlaceResponse.getPlaceType());
        planePlace.setRow(planePlaceResponse.getRow());
        return planePlace;
    }
}
