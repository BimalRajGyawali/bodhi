package group.bonjai.bodhi.controllers;

import group.bonjai.bodhi.exceptions.ConstraintViolation;
import group.bonjai.bodhi.exceptions.ResourceNotFound;
import group.bonjai.bodhi.models.DepartmentMember;
import group.bonjai.bodhi.requests.AppointANewHODRequest;
import group.bonjai.bodhi.responses.AppointANewHODResponse;
import group.bonjai.bodhi.usecases.IAppointANewHODUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AppointANewHODController {

    private final IAppointANewHODUseCase appointANewHODUseCase;

    public AppointANewHODController(IAppointANewHODUseCase appointANewHODUseCase) {
        this.appointANewHODUseCase = appointANewHODUseCase;
    }

    @PostMapping("/hod/appointments")
    public AppointANewHODResponse appoint(@Valid @RequestBody AppointANewHODRequest request)
    throws ResourceNotFound, ConstraintViolation {
        DepartmentMember newHOD = appointANewHODUseCase.execute(request.getNewHODId(), request.getNewRoleOfOldHOD());

        return new AppointANewHODResponse(HttpStatus.OK, newHOD);
    }
}
