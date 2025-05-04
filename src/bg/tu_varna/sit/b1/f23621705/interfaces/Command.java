package bg.tu_varna.sit.b1.f23621705.interfaces;

import bg.tu_varna.sit.b1.f23621705.exceptions.CommandException;

import java.io.IOException;

public interface Command {
    void execute(String[] args) throws CommandException, IOException;
}
