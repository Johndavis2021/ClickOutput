package com.ClickOutput;

import com.google.inject.Provides;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

import javax.inject.Inject;

@PluginDescriptor(
		name = "Click Output",
		description = "Logs interaction text with a customizable prefix",
		tags = {"debug", "ui", "logging"}
)
public class ClickOutputPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClickOutputConfig config;

	@Provides
	ClickOutputConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ClickOutputConfig.class);
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		String option = Text.removeTags(event.getMenuOption()).trim();
		String target = Text.removeTags(event.getMenuTarget()).trim();

		String actionText;
		if (target.isEmpty())
		{
			actionText = option;
		}
		else
		{
			actionText = option + ": " + target;
		}

		// Use the config value here instead of the hardcoded string
		String chatMessage = config.prefixText() + " \"" + actionText + "\"";

		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", chatMessage, null);
	}
}