package user.ishmaust.SpringInAction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.ishmaust.SpringInAction.data.Order;
import user.ishmaust.SpringInAction.data.Taco;
import user.ishmaust.SpringInAction.repository.OrderRepository;
import user.ishmaust.SpringInAction.repository.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TacoController(TacoRepository tacoRepository, OrderRepository orderRepository) {
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTaco() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        return optionalTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Order putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody Order order) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public ResponseEntity<Order> patchOrder(@PathVariable("orderId") Long orderId,
                                            @RequestBody Order patch) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Order order = optionalOrder.get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }

        orderRepository.save(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Taco> deleteOrder(@PathVariable("id") Long id) {
        try {
            tacoRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
