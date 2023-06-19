package com.plaidmrdeer.at.commands.tabs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author PlaidMrdeer
 */
public class Tabs {
    public List<String> setTabs(String[] args, String[] subCommands) {
        if (args.length > 1) {
            return new ArrayList<>();
        }

        if (args.length == 0) {
            return Arrays.asList(subCommands);
        }

        return Arrays.stream(subCommands).filter(
                s -> s.startsWith(args[0])).collect(Collectors.toList()
        );
    }
}