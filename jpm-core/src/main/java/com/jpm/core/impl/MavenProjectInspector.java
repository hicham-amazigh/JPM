package com.jpm.core.impl;

import com.jpm.core.api.ProjectInspector;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public final class MavenProjectInspector implements ProjectInspector {
    private static final Pattern MODULES_BLOCK =
            Pattern.compile("<modules>(.*?)</modules>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
    private static final Pattern MODULE_TAG =
            Pattern.compile("<module>(.*?)</module>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    @Override
    public List<String> listModules(Path projectRoot) throws Exception {
        Path pom = projectRoot.resolve("pom.xml");
        if (!Files.exists(pom)) throw new IllegalStateException("pom.xml not found at: " + pom);

        String xml = Files.readString(pom, StandardCharsets.UTF_8)
                .replaceAll("(?s)<!--.*?-->", ""); // strip comments

        Matcher block = MODULES_BLOCK.matcher(xml);
        if (!block.find()) return List.of();

        Matcher each = MODULE_TAG.matcher(block.group(1));
        List<String> result = new ArrayList<>();
        while (each.find()) {
            String v = each.group(1).trim();
            if (!v.isEmpty()) result.add(v);
        }
        return result;
    }
}
