package com.example.sbertaste.service;

import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.repository.FileSystemRepository;
import com.example.sbertaste.repository.PizzaRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PizzaService extends CommonService<PizzaEntity> {

    private final FileSystemRepository fileSystemRepository;

    public PizzaService(PizzaRepository repository, FileSystemRepository fileSystemRepository) {
        super(repository);
        this.fileSystemRepository = fileSystemRepository;
    }

    @Transactional
    public PizzaEntity saveImage(byte[] content, String imageName, int pizzaId) throws Exception {
        String location = fileSystemRepository.saveImage(content, imageName);

        PizzaEntity pizza = getOne(pizzaId);
        pizza.setImageName(imageName);
        pizza.setImageLocation(location);
        return repository.save(pizza);
    }

    public FileSystemResource findImageByPizzaId(int pizzaId) throws STNotFoundException {
        try {
            return fileSystemRepository.findInFileSystem(getOne(pizzaId).getImageLocation());
        } catch (Exception ex) {
            throw new STNotFoundException(String.format("No image for pizza %d is found", pizzaId));
        }
    }

}
