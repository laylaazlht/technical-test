package javaspring.technicaltest.core.api.customer;

import javaspring.technicaltest.core.api.model.TransactionDto;
import javaspring.technicaltest.core.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionApiV1 {

    @Autowired
    TransactionService service;

    @PostMapping
    public void add(@RequestBody TransactionDto passed) {
        service.placeOrder(passed);
    }
}
