package org.codehaus.griffon.cli.shell.command;

import org.codehaus.griffon.cli.shell.AbstractGriffonCommand;
import org.codehaus.griffon.cli.shell.Command;
import org.codehaus.griffon.cli.shell.Argument;
import org.codehaus.griffon.cli.shell.Option;

@Command(scope = "slideware",
        name = "create-slide",
        description = "Creates a new slide")
public class CreateSlideCommand extends AbstractGriffonCommand {
    @Argument(index = 0,
            name = "name",
            description = "The name of the slide to be created.",
            required = false)
    private String name;
}