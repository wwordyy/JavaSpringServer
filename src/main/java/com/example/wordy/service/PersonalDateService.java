package com.example.wordy.service;

import com.example.wordy.model.BasketModel;
import com.example.wordy.model.CustomerModel;
import com.example.wordy.model.PersonalDateModel;
import com.example.wordy.repository.CustomerRepo;
import com.example.wordy.repository.PersonalDataRepo;
import com.example.wordy.repository.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PersonalDateService {

    private final PersonalDataRepo personalDataRepo;
    private final CustomerRepo customerRepo;
    private final ShopRepo shopRepo;

    @Autowired
    public PersonalDateService(PersonalDataRepo personalDataRepo, CustomerRepo customerRepo, ShopRepo shopRepo) {
        this.personalDataRepo = personalDataRepo;
        this.customerRepo = customerRepo;
        this.shopRepo = shopRepo;
    }

    public List<PersonalDateModel> findAll() {
        return personalDataRepo.findAll();
    }

    public Optional<PersonalDateModel> findById(int id) {
        return personalDataRepo.findById(id);
    }

    public Optional<PersonalDateModel> findByLogin(String login) {
        return personalDataRepo.findByLogin(login);

    }

    @Transactional
    public void save(PersonalDateModel personalDateModel) {
        personalDataRepo.save(personalDateModel);

    }

    @Transactional
    public void createCustomer(PersonalDateModel personalDateModel )
    {
        CustomerModel customer = new CustomerModel(
                personalDateModel,
                shopRepo.findById(1).get(),
                new BasketModel()
        );

        customerRepo.save(customer);




        // 1 - const под которой находится магазин Apple (начальная версия, далее можно улучшить)
    }







    @Transactional
    public void update(PersonalDateModel personalDateModel, int idUpd) {
        personalDateModel.setId(idUpd);
        personalDataRepo.save(personalDateModel);
    }

    @Transactional
    public void delete(PersonalDateModel personalDateModel) {
        personalDataRepo.delete(personalDateModel);
    }

}
