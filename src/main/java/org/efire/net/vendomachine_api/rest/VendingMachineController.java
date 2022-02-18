package org.efire.net.vendomachine_api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import org.efire.net.vendomachine_api.model.VendingMachineDTO;
import org.efire.net.vendomachine_api.service.VendingMachineService;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(value = "/api/vendingMachines", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendingMachineController {

    private final VendingMachineService vendingMachineService;

    @Autowired
    public VendingMachineController(final VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping
    public List<VendingMachineDTO> getAllVendingMachines() {
        return vendingMachineService.findAll();
    }

    @GetMapping("/{id}")
    public VendingMachineDTO getVendingMachine(@PathVariable final Long id) {
        return vendingMachineService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createVendingMachine(@RequestBody @Valid final VendingMachineDTO vendingMachineDTO) {
        return vendingMachineService.create(vendingMachineDTO);
    }

    @PutMapping("/{id}")
    public void updateVendingMachine(@PathVariable final Long id, @RequestBody @Valid final VendingMachineDTO vendingMachineDTO) {
        vendingMachineService.update(id, vendingMachineDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVendingMachine(@PathVariable final Long id) {
        vendingMachineService.delete(id);
    }

}
