package controller;

import dto.ToyRequestDTO;
import dto.ToyResponseDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import mapper.ToyMapper;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import repository.ToyRepository;
import service.ToyService;

@Slf4j
@Path("/v1/toy")
@Tag(name = "Toy", description = "This is a controller for a toy/fake application, and can be used just to get started.")
public class ToyController {

  @Inject
  ToyRepository repository;

  @Inject
  ToyMapper mapper;

  @Inject
  ToyService toyService;

  @APIResponses(
      value = {
          @APIResponse(
              responseCode = "200",
              description = "Ok",
              content = @Content(schema = @Schema(implementation = String.class))
          ),
          @APIResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(schema = @Schema(implementation = InternalServerErrorException.class))
          )
      }
  )
  @GET
  @Path("/hello")
  public Response getHello() {
    return Response.ok("Hello world").build();
  }

  @APIResponses(
      value = {
          @APIResponse(
              responseCode = "200",
              description = "Ok",
              content = @Content(schema = @Schema(implementation = ToyResponseDTO.class))
          ),
          @APIResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(schema = @Schema(implementation = InternalServerErrorException.class))
          )
      }
  )
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(@Valid @NotNull ToyRequestDTO dto) {
    //create entry to temp db
    //todo: implement^
    //post to service
    //map it to dto
    //respond with dto
    var newToyEntry = toyService.create(dto);
    var response = mapper.toDto(newToyEntry);
    return Response.ok(response).build();
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") String id) {
    //todo: service delete
    log.info("Deleting for id {}", id);
    return Response.noContent().build();
  }
}
