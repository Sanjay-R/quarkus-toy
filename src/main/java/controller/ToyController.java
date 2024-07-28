package controller;

import dto.ToyRequestDTO;
import dto.ToyResponseDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import mapper.ToyMapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import service.ToyService;

import java.util.List;

@Slf4j
@Path("/v1/toy")
@Tag(name = "Toy", description = "This is a controller for a toy/fake application, and can be used just to get started.")
public class ToyController {

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
              responseCode = "404",
              description = "Not found",
              content = @Content(schema = @Schema(implementation = NotFoundException.class))
          ),
          @APIResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(schema = @Schema(implementation = InternalServerErrorException.class))
          )
      }
  )
  @Operation(
      summary = "Gets all the example entries for this toy application by the given ids",
      description = "When no id query params are given this returns every entry from the database"
  )
  @GET
  public Response getAll(@QueryParam("id") List<Integer> ids) {
    log.info("Getting all the entries in the database (that match the given ids)");
    var response = toyService.getAll(ids);
    return Response.ok(response).status(Response.Status.OK).build();
  }

  @APIResponses(
      value = {
          @APIResponse(
              responseCode = "201",
              description = "Created",
              content = @Content(schema = @Schema(implementation = ToyResponseDTO.class))
          ),
          @APIResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(schema = @Schema(implementation = InternalServerErrorException.class))
          )
      }
  )
  @Operation(
      summary = "Creates a new example entry for this toy application",
      description = "The name in the request does not have to be unique"
  )
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response post(@Valid @NotNull ToyRequestDTO dto) {
    log.info("Creating new toy entry to the database for name={}", dto.getName());
    var newToyEntry = toyService.create(dto);
    var response = mapper.toDto(newToyEntry);
    return Response.ok(response).status(Response.Status.CREATED).build();
  }

  @APIResponses(
      value = {
          @APIResponse(
              responseCode = "204",
              description = "No content"
          ),
          @APIResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(schema = @Schema(implementation = InternalServerErrorException.class))
          )
      }
  )
  @Operation(
      summary = "Deletes an entry for this toy application",
      description = "The id must exist in the database"
  )
  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") @NotBlank Integer id) {
    log.info("Deleting toy entry for id={}", id);
    toyService.delete(id);
    return Response.noContent().build();
  }

}
