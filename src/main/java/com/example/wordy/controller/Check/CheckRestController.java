package com.example.wordy.controller.Check;


import com.example.wordy.dto.CheckCreateForOrder;
import com.example.wordy.model.CheckModel;
import com.example.wordy.model.OrderModel;
import com.example.wordy.service.BasketService;
import com.example.wordy.service.CheckService;
import com.example.wordy.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checks")
@Tag(name = "Checks", description = "API для управления чеками")
public class CheckRestController {

    private final CheckService checkService;
    private final OrderService orderService;
    private final BasketService basketService;

    public CheckRestController(CheckService checkService, OrderService orderService, BasketService basketService) {
        this.checkService = checkService;
        this.orderService = orderService;
        this.basketService = basketService;
    }

    @GetMapping()
    @Operation(summary = "Вывод всех чеков")
    public List<CheckModel> getAllChecks()
    {
        return checkService.findAll();
    }

    @Operation(summary = "Выввод одного чека по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<CheckModel> getCheckById(@PathVariable int id)
    {
        Optional<CheckModel> checkModel = checkService.findById(id);
        if (checkModel.isPresent())
        {
            return new ResponseEntity<>(checkModel.get(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Добавления чека")
    @PostMapping()
    public ResponseEntity<HttpStatus> addCheck(@RequestBody @Valid CheckModel checkModel,
                                               BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        checkService.save(checkModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Обновление чека")
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateCheck(@PathVariable int id,
                                                  @RequestBody @Valid CheckModel checkModel,
                                                  BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CheckModel oldCheck = checkService.findById(id).orElse(null);

        if (oldCheck == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        oldCheck.setTotalPrice(checkModel.getTotalPrice());
        oldCheck.setOrder(checkModel.getOrder());
        oldCheck.setCheckIssueDate(checkModel.getCheckIssueDate());

        checkService.save(oldCheck);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удаления чека по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCheck(@PathVariable int id)
    {
        Optional<CheckModel> checkModel = checkService.findById(id);

        if (checkModel.isPresent())
        {
            checkService.delete(checkModel.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/order")
    public ResponseEntity<?> createCheckForOrder(@RequestBody @Valid CheckCreateForOrder data,
                                                 BindingResult bindingResult)

    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        OrderModel orderUser = orderService.findById(data.getOrderUser()).orElse(null);

        CheckModel checkModel = new CheckModel(data.getTotalPrice(), orderUser);

        try {
            checkService.save(checkModel, data.getEmailUser());
        } catch (MessagingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        basketService.clearBasket(orderUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
