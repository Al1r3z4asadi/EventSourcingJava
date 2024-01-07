package com.example.tv2.core.commands;

import com.example.tv2.core.Dto.ProdocutDto;

import java.util.List;
import java.util.UUID;

public interface OrderCommand extends ICommand {

    record AddProductToOrder(
            String phoneNumber,
            List<ProdocutDto> data
    ) implements OrderCommand {}
}
