package org.efire.net.vendomachine_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.efire.net.vendomachine_api.config.CustomNotFoundException;
import org.efire.net.vendomachine_api.domain.VendingMachine;
import org.efire.net.vendomachine_api.model.VendingMachineDTO;
import org.efire.net.vendomachine_api.repos.VendingMachineRepository;


@Service
public class VendingMachineService {

    private final VendingMachineRepository vendingMachineRepository;

    @Autowired
    public VendingMachineService(final VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }

    public List<VendingMachineDTO> findAll() {
        return vendingMachineRepository.findAll()
                .stream()
                .map(vendingMachine -> mapToDTO(vendingMachine, new VendingMachineDTO()))
                .collect(Collectors.toList());
    }

    public VendingMachineDTO get(final Long id) {
        return vendingMachineRepository.findById(id)
                .map(vendingMachine -> mapToDTO(vendingMachine, new VendingMachineDTO()))
                .orElseThrow(CustomNotFoundException::new);
    }

    public Long create(final VendingMachineDTO vendingMachineDTO) {
        final VendingMachine vendingMachine = new VendingMachine();
        mapToEntity(vendingMachineDTO, vendingMachine);
        return vendingMachineRepository.save(vendingMachine).getId();
    }

    public void update(final Long id, final VendingMachineDTO vendingMachineDTO) {
        final VendingMachine vendingMachine = vendingMachineRepository.findById(id)
                .orElseThrow(CustomNotFoundException::new);
        mapToEntity(vendingMachineDTO, vendingMachine);
        vendingMachineRepository.save(vendingMachine);
    }

    public void delete(final Long id) {
        vendingMachineRepository.deleteById(id);
    }

    private VendingMachineDTO mapToDTO(final VendingMachine vendingMachine, final VendingMachineDTO vendingMachineDTO) {
        vendingMachineDTO.setId(vendingMachine.getId());
        vendingMachineDTO.setName(vendingMachine.getName());
        vendingMachineDTO.setType(vendingMachine.getType());
        vendingMachineDTO.setStatus(vendingMachine.getStatus());
        return vendingMachineDTO;
    }

    private VendingMachine mapToEntity(final VendingMachineDTO vendingMachineDTO, final VendingMachine vendingMachine) {
        vendingMachine.setName(vendingMachineDTO.getName());
        vendingMachine.setType(vendingMachineDTO.getType());
        vendingMachine.setStatus(vendingMachineDTO.getStatus());
        return vendingMachine;
    }

}
