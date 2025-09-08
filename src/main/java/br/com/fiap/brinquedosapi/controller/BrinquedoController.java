package br.com.fiap.brinquedosapi.controller;

import br.com.fiap.brinquedosapi.model.Brinquedo;
import br.com.fiap.brinquedosapi.service.BrinquedoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    private final BrinquedoService service;

    public BrinquedoController(BrinquedoService service) {
        this.service = service;
    }

    // Helper para montar EntityModel com links HATEOAS
    private EntityModel<Brinquedo> toModel(Brinquedo b) {
        Link self = linkTo(methodOn(BrinquedoController.class).getById(b.getId())).withSelfRel();
        Link all  = linkTo(methodOn(BrinquedoController.class).listAll()).withRel("collection");
        Link create = linkTo(methodOn(BrinquedoController.class).create(new Brinquedo())).withRel("create");
        Link update = linkTo(methodOn(BrinquedoController.class).update(b.getId(), b)).withRel("update");
        Link delete = linkTo(methodOn(BrinquedoController.class).delete(b.getId())).withRel("delete");

        return EntityModel.of(b, self, all, create, update, delete);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Brinquedo>>> listAll() {
        List<Brinquedo> list = service.listarTodos();
        List<EntityModel<Brinquedo>> models = list.stream().map(this::toModel).collect(Collectors.toList());
        Link self = linkTo(methodOn(BrinquedoController.class).listAll()).withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(models, self));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Brinquedo>> getById(@PathVariable Long id) {
        Brinquedo b = service.buscarPorId(id);
        if (b == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toModel(b));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Brinquedo>> create(@RequestBody Brinquedo dto) {
        Brinquedo saved = service.criar(dto);
        EntityModel<Brinquedo> model = toModel(saved);
        URI location = URI.create(model.getRequiredLink(IanaLinkRelations.SELF).getHref());
        return ResponseEntity.created(location).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Brinquedo>> update(@PathVariable Long id, @RequestBody Brinquedo dto) {
        Brinquedo updated = service.atualizar(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toModel(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Brinquedo>> partialUpdate(@PathVariable Long id, @RequestBody Brinquedo dto) {
        Brinquedo updated = service.atualizarParcial(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toModel(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = service.excluir(id);
        if (!removed) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
