package com.ClickOutput;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("clicklogger")
public interface ClickLoggerConfig extends Config
{
	@ConfigItem(
			keyName = "prefixText",
			name = "Prefix Text",
			description = "The message sent before the action name",
			position = 1
	)
	default String prefixText()
	{
		return "Clicked:";
	}
