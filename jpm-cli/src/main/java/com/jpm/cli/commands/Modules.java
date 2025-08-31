package com.jpm.cli.commands;

import com.jpm.core.api.BuildTool;
import com.jpm.core.api.ProjectInspector;
import com.jpm.core.spi.InspectorFactory;
import picocli.CommandLine.*;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

@Command(
    name = "module",
    description = "Module operations",
    subcommands = {
        Modules.Find.class
        // Add future subcommands here: Modules.Add.class, Modules.Remove.class, ...
    }
)
public class Modules implements Runnable {
    @Override public void run() {
        CommandLine.usage(this, System.out);
    }

    @Command(name = "find", description = "List modules in a project")
    public static class Find implements Callable<Integer> {
        @Option(names = {"--build-tool", "-bt"}, defaultValue = "maven",
                description = "Build tool: maven|gradle (default: ${DEFAULT-VALUE})")
        String buildTool;

        @Parameters(index = "0", arity = "0..1", paramLabel = "PATH",
                description = "Project root (default: current directory)")
        String path;

        @Override public Integer call() {
            try {
                BuildTool bt = switch (buildTool.toLowerCase()) {
                    case "maven" -> BuildTool.MAVEN;
                    case "gradle" -> BuildTool.GRADLE;
                    default -> throw new IllegalArgumentException("unsupported build tool: " + buildTool);
                };

                Path root = Path.of(path == null ? "." : path).toAbsolutePath().normalize();
                ProjectInspector inspector = InspectorFactory.forTool(bt);
                List<String> modules = inspector.listModules(root);

                System.out.println("→ found:");
                if (modules.isEmpty()) System.out.println("  (none)");
                else for (int i = 0; i < modules.size(); i++)
                    System.out.println("  " + (i + 1) + ") " + modules.get(i));
                return 0;
            } catch (Exception e) {
                System.err.println("error: " + e.getMessage());
                return 2;
            }
        }
    }
}
