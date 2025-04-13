package com.swiftref.service;

import com.swiftref.dto.GenericResponse;
import com.swiftref.exceptionHandler.ApiExceptionHandler;
import com.swiftref.exceptionHandler.ExceptionHandler;
import com.swiftref.models.Iban;
import com.swiftref.repositories.IbanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * this service class is just to show how to implement calling tables using JPA
 * ans using stream API
 */
@Service
public class IbanRetrivalFromDatabase {

    /**
     * initiating IbanRepository to connect to Database
     */

    @Autowired
    private IbanRepository ibanRepository;


    /**
     * to show the DTO pattern and how to user streamAPi and mapper object
     *
     */
    public List<GenericResponse> getIbans(){
        return ibanRepository.findAll()
                .stream()
                .map(this::ibanDto)
                .collect(Collectors.toList());


    }

    /**
     *
     * example of retriving one item from database and how we can we throw an error
     *
     */
    public  GenericResponse getBic(long id){
        return ibanRepository.findById(id)
                .map(this::ibanDto)
                .orElseThrow(ExceptionHandler::new);
    }

    private GenericResponse ibanDto(Iban iban){
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponse(iban);
        genericResponse.setStatus(HttpStatus.OK);
        genericResponse.setMessage("Ibans returned successfully");
        return genericResponse;
    }
}
