package com.jpm.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import com.jpm.cli.commands.ModuleFindCommand;

@Command(
    name = "jpm",
    mixinStandardHelpOptions = true,
    subcommands = {
        ModuleFindCommand.class
    }
)
public class Main implements Runnable {
    public static void main(String[] args) {
        int code = new CommandLine(new Main()).execute(args);
        System.exit(code);
    }
    @Override public void run() {
        CommandLine.usage(this, System.out);
    }
}
