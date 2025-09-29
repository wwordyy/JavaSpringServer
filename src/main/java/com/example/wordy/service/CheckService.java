package com.example.wordy.service;

import com.example.wordy.model.CheckModel;
import com.example.wordy.repository.CheckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CheckService {

    private final CheckRepo checkRepo;
    private final EmailService emailService;


    @Autowired
    public CheckService(CheckRepo checkRepo, EmailService emailService) {
        this.checkRepo = checkRepo;
        this.emailService = emailService;
    }

    public List<CheckModel> findAll() {
        return checkRepo.findAll();
    }

    public Optional<CheckModel> findById(int id) {
        return checkRepo.findById(id);
    }

    public Optional<CheckModel> findByTotalPrice(int totalPrice) {
        return checkRepo.findByTotalPrice(totalPrice);
    }

    @Transactional
    public void save(CheckModel checkModel, String email) throws MessagingException {
        checkRepo.save(checkModel);

        sendEmail(email, checkModel);
    }

    @Transactional
    public void save(CheckModel checkModel)
    {
        checkRepo.save(checkModel);
    }

    private void sendEmail(String emailAddress, CheckModel checkModel) throws MessagingException {
        String subject = "Thank you for your purchase by TechnoMir!";

        emailService.sendEmail(emailAddress, subject, compilationContent(checkModel));
    }

    private String compilationContent(CheckModel checkModel) {

        StringBuilder content = new StringBuilder();

        content.append("В магазине TechnoMir была совершена покупка на сумму: ");
        content.append(checkModel.getTotalPrice());
        content.append("₽");
        content.append("\n");
        content.append("Время покупки: ");
        content.append(checkModel.getCheckIssueDate());
        content.append("\n");
        content.append("Номер заказа: ");
        content.append(checkModel.getOrder().getNumberOrder());
        content.append("\n");
        content.append("Спасибо за покупку, ");
        content.append(checkModel.getOrder().getCustomer().getPersonalDate().getLogin());
        content.append(" !");

        return content.toString();
    }



    @Transactional
    public void delete(CheckModel checkModel) {
        checkRepo.delete(checkModel);
    }

    @Transactional
    public void update(CheckModel checkModel, int idUpd) {
        checkModel.setId(idUpd);
        checkRepo.save(checkModel);
    }

}
