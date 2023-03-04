package com.example.sbertaste.service;

import com.example.sbertaste.exception.STNotFoundException;
import com.example.sbertaste.model.PizzaEntity;
import com.example.sbertaste.repository.FileSystemRepository;
import com.example.sbertaste.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PizzaService extends CommonService<PizzaEntity> {

    private final FileSystemRepository fileSystemRepository;

    @Value("${images.location}")
    private String location;

    public PizzaService(PizzaRepository repository, FileSystemRepository fileSystemRepository) {
        super(repository);
        this.fileSystemRepository = fileSystemRepository;
    }

    @Transactional
    public void saveImage(byte[] content, String imageName, int pizzaId) throws Exception {
        String imgFile = fileSystemRepository.saveImage(content, imageName, location);

        PizzaEntity pizza = getOne(pizzaId);
        pizza.setImgFile(location + imgFile);
        repository.save(pizza);
    }

    public FileSystemResource findImageByPizzaId(int pizzaId) throws STNotFoundException {
        try {
            return fileSystemRepository.findInFileSystem(getOne(pizzaId).getImgFile());
        } catch (Exception ex) {
            throw new STNotFoundException(String.format("No image for pizza %d is found", pizzaId));
        }
    }

}
