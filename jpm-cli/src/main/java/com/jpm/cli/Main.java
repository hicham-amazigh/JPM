package com.jpm.cli;

import com.jpm.cli.commands.Modules;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "JPM",
    mixinStandardHelpOptions = true,
    subcommands = {
        Modules.class
    }
)
public final class Main implements Runnable {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

	@Option(names = { "-v", "--show-logs" }, description = "Show info logs.")
	boolean showLogs;

	@Option(names = { "-g", "--debug" }, description = "Enable debug mode.")
	boolean debug;
    
    public static void main(String[] args) {
        int code = new CommandLine(new Main()).execute(args);
        System.exit(code);
    }

    @Override public void run() {
        spec.commandLine().usage(System.out);
    }
}
