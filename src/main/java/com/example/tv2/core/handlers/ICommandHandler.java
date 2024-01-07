package com.example.tv2.core.handlers;

import com.example.tv2.core.commands.ICommand;
import com.example.tv2.utils.ServiceResult;


public interface ICommandHandler <T extends ICommand> {
    ServiceResult<T> handle(T command);

}