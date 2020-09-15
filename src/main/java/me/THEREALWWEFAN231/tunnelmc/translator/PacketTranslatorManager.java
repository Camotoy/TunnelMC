package me.THEREALWWEFAN231.tunnelmc.translator;

import java.util.ArrayList;
import java.util.HashMap;

import com.nukkitx.protocol.bedrock.BedrockPacket;

import me.THEREALWWEFAN231.tunnelmc.translator.packets.AddPlayerPacketTranslator;
import me.THEREALWWEFAN231.tunnelmc.translator.packets.ChunkRadiusUpdatedPacketTranslator;
import me.THEREALWWEFAN231.tunnelmc.translator.packets.LevelChunkPacketTranslator;
import me.THEREALWWEFAN231.tunnelmc.translator.packets.PlayStatusPacketTranslator;
import me.THEREALWWEFAN231.tunnelmc.translator.packets.PlayerListPacketTranslator;
import me.THEREALWWEFAN231.tunnelmc.translator.packets.ResourcePackStackPacketTranslator;
import me.THEREALWWEFAN231.tunnelmc.translator.packets.ResourcePacksInfoPacketTranslator;
import me.THEREALWWEFAN231.tunnelmc.translator.packets.StartGamePacketTranslator;
import me.THEREALWWEFAN231.tunnelmc.translator.packets.TextPacketTranslator;

public class PacketTranslatorManager {

	private ArrayList<PacketTranslator<?>> packetTranslators = new ArrayList<PacketTranslator<?>>();
	private HashMap<Class<?>, PacketTranslator<?>> packetTranslatorsByClass = new HashMap<Class<?>, PacketTranslator<?>>();
	private HashMap<Class<?>, PacketTranslator<?>> packetTranslatorsByPacketClass = new HashMap<Class<?>, PacketTranslator<?>>();

	public PacketTranslatorManager() {
		this.addTranslator(new StartGamePacketTranslator());
		this.addTranslator(new ChunkRadiusUpdatedPacketTranslator());
		this.addTranslator(new LevelChunkPacketTranslator());
		this.addTranslator(new PlayStatusPacketTranslator());
		this.addTranslator(new ResourcePacksInfoPacketTranslator());
		this.addTranslator(new ResourcePackStackPacketTranslator());
		this.addTranslator(new AddPlayerPacketTranslator());
		this.addTranslator(new PlayerListPacketTranslator());
		this.addTranslator(new TextPacketTranslator());
	}

	private void addTranslator(PacketTranslator<?> translator) {
		this.packetTranslators.add(translator);
		this.packetTranslatorsByClass.put(translator.getClass(), translator);
		this.packetTranslatorsByPacketClass.put(translator.getPacketClass(), translator);
	}

	public void translatePacket(BedrockPacket bedrockPacket) {
		PacketTranslator packetTranslator = this.packetTranslatorsByPacketClass.get(bedrockPacket.getClass());
		if (packetTranslator != null) {
			packetTranslator.translate(bedrockPacket);
		} else {
			//System.out.println("Could not find a packet translator for the packet: " + bedrockPacket.getClass());
		}
	}

}